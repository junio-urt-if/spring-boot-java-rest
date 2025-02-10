package br.gov.ifgoiano.urt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.ifgoiano.urt.data.StudentDTO;
import br.gov.ifgoiano.urt.data.vo.StudentVO_OutPut;
import br.gov.ifgoiano.urt.services.StudentServices;
import br.gov.ifgoiano.urt.validation.OnCreate;
import br.gov.ifgoiano.urt.validation.OnUpdate;

/**
 * Classe responsável por definir os endpoints.
 * Esta classe contém endpoints para criar, atualizar, deletar e listar alunos.
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2025-02-10
 */
@RestController
@RequestMapping("/student")
@Validated
public class StudentController {
	
	@Autowired
	private StudentServices service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentDTO>> findAll() {
	    List<StudentDTO> students = service.findAll();

	    if (students.isEmpty()) {
	        return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content
	    }
	    
	    return ResponseEntity.ok(students); // Retorna HTTP 200 OK com a lista de estudantes
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
	    StudentDTO student = service.findById(id);
	    return ResponseEntity.ok(student); // Retorna 200 OK com o estudante encontrado
	}

	// exemplo do uso de VO com saída do Student customizado
	@GetMapping(value = "/vo/{id}",
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentVO_OutPut> findByIdStudentCustomized(
							@PathVariable(value = "id") Long id) {
		StudentVO_OutPut student = service.findByIdStudentCustomized(id);
	    return ResponseEntity.ok(student); // Retorna 200 OK com o estudante encontrado	
	}
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> create(
			       @Validated(OnCreate.class) @RequestBody StudentDTO student) {
		StudentDTO savedStudent = service.create(student);
	    return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> update(
			      @Validated(OnUpdate.class) @RequestBody StudentDTO student) {
		// Se não houver erros, continue o fluxo normal (exemplo de salvamento)
		   // Tenta atualizar o estudante
	    StudentDTO updateStudent = service.update(student);
	    
	    	    // Caso o estudante seja atualizado com sucesso
	    return ResponseEntity.ok(updateStudent);
	}
		
	// utilizado quando o service lança uma exceção caso não seja localizado o id no banco
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
	    service.delete(id);
	    return ResponseEntity.noContent().build(); // Retorna 204 se a exclusão for bem-sucedida
	}
	
	@DeleteMapping(value = "/vo/{id}")
	public ResponseEntity<Void> delete1(@PathVariable(value = "id") Long id) {
		return service.delete1(id) ? 
				ResponseEntity.noContent().build() :
			    ResponseEntity.notFound().build();
	}
	


}
