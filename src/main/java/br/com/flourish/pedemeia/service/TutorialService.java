package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.response.BuscarTutoriaisResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.TutorialRepository;
import br.com.flourish.pedemeia.dto.TutorialDTO;
import br.com.flourish.pedemeia.exception.BusinessException;

@Service
public class TutorialService {

	@Autowired 
	private TutorialRepository repository;
	
	private static final String ERRO_BUSCAR_TUTORIAIS = "Ocorreu um erro ao buscar tutoriais.";
	
	public BuscarTutoriaisResponse buscar() {
		List<TutorialEntity> tutoriais = new ArrayList<>();
		
		try {
			tutoriais = repository.findAll();
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_TUTORIAIS);
		}
		
		return new BuscarTutoriaisResponse(tutoriais.stream().map(TutorialDTO::new).collect(Collectors.toList()));
	}
}
