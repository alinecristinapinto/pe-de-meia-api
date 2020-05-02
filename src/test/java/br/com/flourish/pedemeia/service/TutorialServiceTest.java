package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.flourish.pedemeia.controller.response.BuscarTutoriaisResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.TutorialRepository;
import br.com.flourish.pedemeia.dto.TutorialDTO;
import br.com.flourish.pedemeia.exception.BusinessException;

public class TutorialServiceTest {

	@InjectMocks
	private TutorialService service;
	
	@Mock
	private TutorialRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = BusinessException.class)
	public void buscar_erroBuscarTutoriais() {
		Mockito.when(repository.findAll()).thenThrow(BusinessException.class);

		service.buscar();
	}
	
	@Test
	public void buscar_sucesso() {
		Mockito.when(repository.findAll()).thenReturn(montarListaTutorialEntity());

		BuscarTutoriaisResponse response = service.buscar();
		Assert.assertEquals(montarBuscarTutoriaisResponse(), response);
	}
	
	private List<TutorialEntity> montarListaTutorialEntity() {
		List<TutorialEntity> tutoriais = new ArrayList<>();
		
		tutoriais.add(TutorialEntity.builder().codigo(1).descricao("Tutorial 1").etapas(new ArrayList<>()).build());
		tutoriais.add(TutorialEntity.builder().codigo(2).descricao("Tutorial 2").etapas(new ArrayList<>()).build());
		
		return tutoriais;
	}
	
	private BuscarTutoriaisResponse montarBuscarTutoriaisResponse() {
		return new BuscarTutoriaisResponse(montarListaTutorialEntity().stream().map(TutorialDTO::new).collect(Collectors.toList()));
	}
}
