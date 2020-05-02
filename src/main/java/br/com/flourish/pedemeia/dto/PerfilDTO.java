package br.com.flourish.pedemeia.dto;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.PerfilEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

	private Integer codigo;
	private String perfil;
	private String imagem;
	
	public PerfilDTO(PerfilEntity perfil) {
		this.codigo = perfil.getCodigo();
		this.perfil = perfil.getDescricao();
		this.imagem = perfil.getImagem();
	}
}
