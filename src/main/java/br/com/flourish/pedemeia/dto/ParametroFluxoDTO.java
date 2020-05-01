package br.com.flourish.pedemeia.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.ParametroFluxoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametroFluxoDTO {
	
	private Integer codigo;

	private String descricao; 
	
	private List<FluxoDTO> fluxo;
	
	public ParametroFluxoDTO(ParametroFluxoEntity parametroFluxo) {
		this.codigo = parametroFluxo.getCodigo();
		this.descricao = parametroFluxo.getDescricao();
		this.fluxo = parametroFluxo.getFluxo().stream().map(FluxoDTO::new).collect(Collectors.toList());
	}

}
