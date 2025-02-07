package br.gov.ifgoiano.urt.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ifgoiano.urt.data.StudentDTO;
import br.gov.ifgoiano.urt.data.vo.StudentVO_OutPut;
import br.gov.ifgoiano.urt.exceptions.ResourceNotFoundException;
import br.gov.ifgoiano.urt.mapper.DataMapper;
import br.gov.ifgoiano.urt.model.Student;
import br.gov.ifgoiano.urt.repository.StudentRepository;

/**
 * Classe responsável por definir os serviços.
 * Esta classe contém endpoints para criar, atualizar, deletar e listar alunos.
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2025-02-06
 */
@Service
public class StudentServices {
	
	@Autowired
	StudentRepository repository;
	
	DataMapper modelmapper = new DataMapper();
	
	private Logger logger = Logger.getLogger(StudentServices.class.getName());

	public List<StudentDTO> findAll() {
		logger.info("Finding all students!");
		
		var student = repository.findAll();
		var studentDTO = DataMapper.parseListObjects(student, StudentDTO.class);
		
		return studentDTO; 
		// Pode usar unica linha
		//return DataMapper.parseListObjects(repository.findAll(), StudentDTO.class);
	}

	public StudentDTO findById(Long id) {
		logger.info("Finding one student!");
		
		var studentEntity = repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		// fazer o mapeamento em única linha
		return DataMapper.parseObject(studentEntity, StudentDTO.class);

	}
	
	// Exemplo de um VO com mapeamento customizado
	public StudentVO_OutPut findByIdStudentCustomized(Long id) {
	  logger.info("Finding one student!");

	  var studentEntity = repository.findById(id)
	     .orElseThrow(() -> new ResourceNotFoundException("No records found for 									this ID!"));

	  // fazer o mapeamento em única linha
	  return DataMapper.convertStudentEntityToStudentVO_OutPut(studentEntity);
	}

	
	public StudentDTO create(StudentDTO studentDTO) {
		logger.info("Creating one student!");
		
		// converto de DTO para entidade - salvar no BD
		var studentEntity = DataMapper.parseObject(studentDTO, Student.class);
		  
		//inserir no BD
		var student = repository.save(studentEntity);	
		// converto de entidade para VO - mostrar ao usuário
		var vo =  DataMapper.parseObject(student, StudentDTO.class);
		
		// usar um linha
		//var vo =  DataMapper.parseObject(repository.save(studentEntity), StudentDTO.class);
		
		return vo;
	}
	
	@Transactional
	public StudentDTO update(StudentDTO studentDTO) {
		logger.info("Updating one student!");
		
		// student entity
		var entity = repository.findById(studentDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(studentDTO.getFirstName());
		entity.setLastName(studentDTO.getLastName());
		entity.setAddress(studentDTO.getAddress());
		entity.setGender(studentDTO.getGender());

		var studentEntity = repository.save(entity);

		var vo =  DataMapper.parseObject(studentEntity, StudentDTO.class);
		return vo;
	}
	
	public void delete(Long id) {
				
		var entityStudent = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entityStudent);
	}
	
}

