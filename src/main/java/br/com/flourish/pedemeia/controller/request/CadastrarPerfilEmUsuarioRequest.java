package br.com.flourish.pedemeia.controller.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPerfilEmUsuarioRequest {

	@NotNull(message="O código do usuário não pode ser nulo.")
	private Integer codigoUsuario;
	
	@NotNull(message="O código do perfil não pode ser nulo.")
	private Integer codigoPerfil;
}
