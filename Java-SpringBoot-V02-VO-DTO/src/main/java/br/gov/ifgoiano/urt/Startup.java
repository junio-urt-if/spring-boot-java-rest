package br.gov.ifgoiano.urt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.gov.ifgoiano.urt.model.Student;
import br.gov.ifgoiano.urt.repository.StudentRepository;

@SpringBootApplication
public class Startup {

	@Autowired
	StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner() {
		if (repository.findAll().isEmpty()) {
	        return args -> {
	        	Student marcelo = new Student("Marcelo","Sousa","Rua das Palmeiras","Male");
	        	Student libia = new Student("Líbia","Antunes","Rua das Flores","Female");
	        	Student joao = new Student("João","Caixeta","Av Dom Prudencio","Male");
	        	Student cleunice = new Student("Cleunice","Pires","Av Mangalo","Female");
	            
	            repository.save(marcelo);
	            repository.save(libia);
	            repository.save(joao);
	            repository.save(cleunice);
	        };//end return args
		}else { // não faz nada
			return args -> {    };
		}
    }

}
