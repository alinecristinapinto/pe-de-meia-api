package br.com.flourish.pedemeia.controller.response;

import br.com.flourish.pedemeia.dto.TutorialDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialResponse {

	private TutorialDTO tutorial;
}
