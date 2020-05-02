package br.com.flourish.pedemeia.dto;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	private Integer codigo;
	
	private String email;
	
	private String nome;
	
	private String senha;
	
	private Integer codigoPerfil;
	
	private Integer codigoNivel;

	public UsuarioDTO(UsuarioEntity usuario) {
		this.codigo = usuario.getCodigo();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.codigoPerfil = usuario.getCodigoPerfil();
		this.codigoNivel = usuario.getCodigoNivel();
	}
}
