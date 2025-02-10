package br.gov.ifgoiano.urt.data.vo;

import br.gov.ifgoiano.urt.validation.OnCreate;
import br.gov.ifgoiano.urt.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * Classe responsável por mapear os dados de Student de saída
 * 
 * @author Júnio Lima
 * @version 1.0
 * @since 2022-08-01
 * @lastModified 2024-02-08
 */
public record StudentVO(
	@NotNull(groups = OnUpdate.class, message = "O ID é obrigatório na atualização")	
    Long id,

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, 
                        message = "O primeiro nome é obrigatório")
    @Size(min = 3, max = 50, message = "O primeiro nome deve ter entre 3 e 50 caracteres")
    String firstName,

    @NotNull(groups = {OnCreate.class, OnUpdate.class},
                      message = "O último nome não pode ser nulo")
    String lastName,

    @NotEmpty(groups = {OnCreate.class, OnUpdate.class}, 
                        message = "O endereço não pode estar em vazio")
    String address,

    @NotNull(message = "O gênero é obrigatório")
    String gender
) {}

