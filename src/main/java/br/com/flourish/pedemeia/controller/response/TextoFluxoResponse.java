package br.com.flourish.pedemeia.controller.response;

import br.com.flourish.pedemeia.dto.ParametroFluxoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextoFluxoResponse {

	private ParametroFluxoDTO textoFluxo;
}
