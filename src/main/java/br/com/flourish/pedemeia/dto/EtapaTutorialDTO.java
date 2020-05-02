package br.com.flourish.pedemeia.dto;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.EtapaTutorialEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtapaTutorialDTO {

	private Integer codigo;	
	private String etapa;
	
	public EtapaTutorialDTO(EtapaTutorialEntity etapa) {
		this.codigo = etapa.getCodigo();
		this.etapa = etapa.getDescricao();
	}
}
