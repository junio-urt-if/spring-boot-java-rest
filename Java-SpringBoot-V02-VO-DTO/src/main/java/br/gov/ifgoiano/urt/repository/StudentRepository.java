package br.gov.ifgoiano.urt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ifgoiano.urt.model.Student;

/**
 * Interface responsável por definir o repository.
 *  
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2025-02-06
 */
//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
}
