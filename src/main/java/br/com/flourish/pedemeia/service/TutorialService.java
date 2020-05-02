package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.response.BuscarTutoriaisResponse;
import br.com.flourish.pedemeia.controller.response.TutorialResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.TutorialRepository;
import br.com.flourish.pedemeia.dto.TutorialDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

@Service
public class TutorialService {

	@Autowired 
	private TutorialRepository repository;
	
	private static final String ERRO_BUSCAR_TUTORIAIS = "Ocorreu um erro ao buscar tutoriais.";
	private static final String ERRO_CAMPO_INVALIDO = "Ordem está nulo.";
	private static final String ERRO_BUSCAR_TUTORIAL = "Ocorreu um erro ao buscar tutorial de ordem %d.";
	private static final String ERRO_TUTORIAL_INEXISTENTE = "Nenhum tutorial de ordem %d foi encontrado.";
	
	public BuscarTutoriaisResponse buscar() {
		List<TutorialEntity> tutoriais = new ArrayList<>();
		
		try {
			tutoriais = repository.findAll();
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_TUTORIAIS);
		}
		
		return new BuscarTutoriaisResponse(tutoriais.stream().map(TutorialDTO::new).collect(Collectors.toList()));
	}
	
	public TutorialResponse buscarPorOrdem(Integer ordem) {
		Optional<TutorialEntity> tutorial = Optional.empty();

		verificarRequest(ordem);
		
		try {
			tutorial = repository.findByOrdem(ordem);
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_TUTORIAL.replace("%d", ordem.toString()));
		}
		
		if(!tutorial.isPresent())
			throw new BusinessException(ERRO_TUTORIAL_INEXISTENTE.replace("%d", ordem.toString()));
		
		return new TutorialResponse(new TutorialDTO(tutorial.get()));
	}
	
	private void verificarRequest(Integer ordem) {
		if(ordem == null)
			throw new InvalidAttributeException(ERRO_CAMPO_INVALIDO);
	}
}
