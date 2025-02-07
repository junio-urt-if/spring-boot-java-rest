package br.gov.ifgoiano.urt.data.vo;

/**
 * Classe responsável por mapear os dados de Student de saída
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2024-02-07
 */
public record StudentVO(Long id,
		                String firstName,
		                String lastName,
		                String address,
		                String gender)
{

}
