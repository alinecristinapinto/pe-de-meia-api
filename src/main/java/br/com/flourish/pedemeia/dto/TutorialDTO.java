package br.com.flourish.pedemeia.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialDTO {
	
	private Integer codigo;
	
	private Integer ordem;

	private String tutorial; 
	
	private List<EtapaTutorialDTO> etapas;
	
	public TutorialDTO(TutorialEntity tutorial) {
		this.codigo = tutorial.getCodigo();
		this.ordem = tutorial.getOrdem();
		this.tutorial = tutorial.getDescricao();
		this.etapas = tutorial.getEtapas().stream().map(EtapaTutorialDTO::new).collect(Collectors.toList());
	}

}
