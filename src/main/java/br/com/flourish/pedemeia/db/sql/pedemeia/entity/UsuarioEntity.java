package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.flourish.pedemeia.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TBL_USUARIO")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_usuario", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_email", unique=true, nullable = false)
	private String email;
	
	@Column(name="nm_usuario", nullable = false)
	private String nome;
	
	@Column(name="ds_senha", nullable = false)
	private String senha;
	
	@Column(name="cd_perfil", nullable = true)
	private Integer codigoPerfil;
	
	@Column(name="cd_nivel", nullable = true)
	private Integer codigoNivel;

	public UsuarioEntity(UsuarioDTO usuario) {
		this.codigo = usuario.getCodigo();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.codigoPerfil = usuario.getCodigoPerfil();
		this.codigoNivel = usuario.getCodigoNivel();
	}
}
