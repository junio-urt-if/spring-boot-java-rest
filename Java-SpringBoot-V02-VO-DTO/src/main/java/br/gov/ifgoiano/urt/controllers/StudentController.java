package br.gov.ifgoiano.urt.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

/**
 * Classe responsável por definir os endpoints.
 * Esta classe contém endpoints para criar, atualizar, deletar e listar alunos.
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2025-02-07
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentServices service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StudentDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	// exemplo do uso de VO com saída do Student customizado
	@GetMapping(value = "/vo/{id}",
	produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentVO_OutPut findByIdStudentCustomized(
							@PathVariable(value = "id") Long id) {
		return service.findByIdStudentCustomized(id);
	}

	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO create(@RequestBody StudentDTO student) {
		return service.create(student);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO update(@RequestBody StudentDTO student) {
		return service.update(student);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
