package br.com.flourish.pedemeia.dto;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.FluxoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FluxoDTO {

	private Integer codigo;	
	private Integer ordem;	
	private String descricao;
	
	public FluxoDTO(FluxoEntity fluxo) {
		this.codigo = fluxo.getCodigo();
		this.ordem = fluxo.getOrdem();
		this.descricao = fluxo.getDescricao();
	}
	
}
