package br.gov.ifgoiano.urt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.ifgoiano.urt.model.Person;

/**
 * Interface responsável por definir o repository.
 *  
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2023-10-10
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	
}
