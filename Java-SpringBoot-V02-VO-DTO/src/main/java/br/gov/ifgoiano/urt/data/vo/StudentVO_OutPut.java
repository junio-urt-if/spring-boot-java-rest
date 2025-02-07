package br.gov.ifgoiano.urt.data.vo;


/**
 * Classe responsável por mapear os dados de Student de saída
 * com juncao dos campos firstName e lastName
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2024-02-07
 */
public record StudentVO_OutPut(
		    Long id,
            String name,
            String address,
            String gender)
{

}
