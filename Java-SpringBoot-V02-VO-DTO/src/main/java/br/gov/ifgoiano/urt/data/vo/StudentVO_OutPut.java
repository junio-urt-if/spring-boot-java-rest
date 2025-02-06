package br.gov.ifgoiano.urt.data.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe responsável por mapear os dados de Student de saída
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2024-02-06
 */
public class StudentVO_OutPut implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String address;
	private String gender;
	
	public StudentVO_OutPut() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, gender, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentVO_OutPut other = (StudentVO_OutPut) obj;
		return Objects.equals(address, other.address) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
