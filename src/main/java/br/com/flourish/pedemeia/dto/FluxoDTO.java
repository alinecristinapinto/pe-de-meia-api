package br.com.flourish.pedemeia.dto;

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
	private String descricao;
	
}
