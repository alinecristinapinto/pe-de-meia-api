package br.com.flourish.pedemeia.controller.response;

import java.util.List;

import br.com.flourish.pedemeia.dto.TutorialDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTutoriaisResponse {

	private List<TutorialDTO> tutorial;
}
