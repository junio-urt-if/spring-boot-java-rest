package br.gov.ifgoiano.urt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.ifgoiano.urt.data.vo.StudentVO_OutPut;
import br.gov.ifgoiano.urt.model.Student;

/**
 * Classe responsável por mapear os dados
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2024-02-06
 */
public class DataMapper {
	// Podemos usar qualquer Mapper
	//private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	private static ModelMapper mapper = new ModelMapper();
	
	// mapeamento genérica
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	// mapeamento genérica
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}

	// mapeamento específico
	public static StudentVO_OutPut convertPersonEntityToPersonVOOutPut(Student student) {
		StudentVO_OutPut vo = new StudentVO_OutPut();
		vo.setId(student.getId());
		vo.setAddress(student.getAddress());
		vo.setName(student.getFirstName() + " " + student.getLastName());
		vo.setGender(student.getGender());
		return vo;
	}

}

