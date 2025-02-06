package br.gov.ifgoiano.urt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ifgoiano.urt.data.vo.StudentVO;
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

	public List<StudentVO> findAll() {
		logger.info("Finding all students!");
		
		var student = repository.findAll();
		var studentVO = DataMapper.parseListObjects(student, StudentVO.class);
		
		return studentVO; 
		// Pode usar unica linha
		//return DataMapper.parseListObjects(repository.findAll(), StudentVO.class);
	}

	public StudentVO findById(Long id) {
		logger.info("Finding one student!");
		
		var studentEntity = repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		// fazer o mapeamento em única linha
		return DataMapper.parseObject(studentEntity, StudentVO.class);

	}
	
	public StudentVO create(StudentVO studentVO) {
		logger.info("Creating one student!");
		
		// converto de VO para entidade - salvar no BD
		var studentEntity = DataMapper.parseObject(studentVO, Student.class);
		  
		//inserir no BD
		var student = repository.save(studentEntity);	
		// converto de entidade para VO - mostrar ao usuário
		var vo =  DataMapper.parseObject(student, StudentVO.class);
		
		// usar um linha
		//var vo =  DataMapper.parseObject(repository.save(studentEntity), StudentVO.class);
		
		return vo;
	}
	
	@Transactional
	public StudentVO update(StudentVO studentVO) {
		logger.info("Updating one student!");
		
		// student entity
		var entity = repository.findById(studentVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(studentVO.getFirstName());
		entity.setLastName(studentVO.getLastName());
		entity.setAddress(studentVO.getAddress());
		entity.setGender(studentVO.getGender());

		var studentEntity = repository.save(entity);

		var vo =  DataMapper.parseObject(studentEntity, StudentVO.class);
		return vo;
	}
	
	public void delete(Long id) {
				
		var entityStudent = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entityStudent);
	}
	
}

