package br.gov.ifgoiano.urt.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @lastModified 2025-02-10
 */
@Service
public class StudentServices {
	
	@Autowired
	StudentRepository repository;
	
	DataMapper modelmapper = new DataMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServices.class.getName());

	public List<StudentDTO> findAll() {
	    logger.info("Finding all students!");

	    List<Student> students = repository.findAll();
	    return DataMapper.parseListObjects(students, StudentDTO.class);
	}

	public StudentDTO findById(Long id) {
	    logger.info("Finding one student!");

	    return repository.findById(id)
	            .map(student -> DataMapper.parseObject(student, StudentDTO.class))
	            .orElseThrow(() -> new ResourceNotFoundException("No records found for the ID: " + id));
	}
	
	public StudentVO_OutPut findByIdStudentCustomized(Long id) {
	    logger.info("Finding one student!");
	    
	    return repository.findById(id)
	            .map(student -> DataMapper.convertStudentEntityToStudentVO_OutPut(student))
	            .orElseThrow(() -> new ResourceNotFoundException("No records found for the ID: " + id));
	}
	
	@Transactional // O método create faz mais do que apenas chamar repository.save(). 
				  // Ele também converte DTOs para entidades e vice-versa, e 
	              // pode conter lógica adicional no futuro.
	public StudentDTO create(StudentDTO studentDTO) {
	    logger.info("Creating one student!");

	    // Converte de DTO para entidade - salvar no BD
	    Student studentEntity = DataMapper.parseObject(studentDTO, Student.class);

	    try {
	        // Salva no banco de dados
	        Student savedStudent = repository.save(studentEntity);
	        
	        // Converte a entidade salva para DTO e retorna
	        return DataMapper.parseObject(savedStudent, StudentDTO.class);
	    } catch (Exception ex) {
	        // Lança uma exceção  se algo der errado
	    	logger.error("Error saving student: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Error saving student record", ex);
	    }
	}
	
	@Transactional //O método envolve leitura e escrita no banco de dados (findById() e save())
	public StudentDTO update(StudentDTO studentDTO) {
		logger.info("Updating one student!");
		
		// student entity
		var entity = repository.findById(studentDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + studentDTO.getId()));
		
		entity.setFirstName(studentDTO.getFirstName());
		entity.setLastName(studentDTO.getLastName());
		entity.setAddress(studentDTO.getAddress());
		entity.setGender(studentDTO.getGender());

		var studentEntity = repository.save(entity);

		return DataMapper.parseObject(studentEntity, StudentDTO.class);
	}
	
	// Se o ID não existir, será lançada a exceção ResourceNotFoundException
	// Se o ID existir, o objeto será deletado normalmente
	// Forma mais limpa
	@Transactional
	public void delete(Long id) {
	    Student student = repository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));

	    repository.delete(student);
	}
	
	// outra forma, mas não gera exceção se não não achar o id 
	@Transactional
	public boolean delete1(Long id) {
	    Optional<Student> entityStudent = repository.findById(id);
	    
	    if (entityStudent.isPresent()) {
	        repository.delete(entityStudent.get());
	        return true; // Recurso encontrado e deletado
	    } else {
	        return false; // Recurso não encontrado
	    }
	}	

}

