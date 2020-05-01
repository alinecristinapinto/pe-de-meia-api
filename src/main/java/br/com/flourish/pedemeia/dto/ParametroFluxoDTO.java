package br.com.flourish.pedemeia.dto;

import java.util.List;

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

}
