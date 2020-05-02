package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.flourish.pedemeia.controller.response.BuscarTutoriaisResponse;
import br.com.flourish.pedemeia.controller.response.TutorialResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.TutorialRepository;
import br.com.flourish.pedemeia.dto.TutorialDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

public class TutorialServiceTest {

	@InjectMocks
	private TutorialService service;
	
	@Mock
	private TutorialRepository repository;
	
	private static final Integer ORDEM = 1;

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
	
	@Test(expected = InvalidAttributeException.class)
	public void buscarPorOrdem_erroRequestNula() {
		service.buscarPorOrdem(null);
	}
	
	@Test(expected = BusinessException.class)
	public void buscarPorOrdem_erroBuscarTutorial() {
		Mockito.when(repository.findByOrdem(Mockito.anyInt())).thenThrow(BusinessException.class);
		service.buscarPorOrdem(ORDEM);
	}
	
	@Test(expected = BusinessException.class)
	public void buscarPorOrdem_erroTutorialInexistente() {
		Mockito.when(repository.findByOrdem(Mockito.anyInt())).thenReturn(Optional.empty());
		service.buscarPorOrdem(ORDEM);
	}
	
	@Test
	public void buscarPorOrdem_sucesso() {
		Mockito.when(repository.findByOrdem(Mockito.anyInt())).thenReturn(Optional.of(montarTutorialEntity()));
		
		TutorialResponse response = service.buscarPorOrdem(ORDEM);
		Assert.assertEquals(montarTutorialResponse(), response);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	private List<TutorialEntity> montarListaTutorialEntity() {
		List<TutorialEntity> tutoriais = new ArrayList<>();
		
		tutoriais.add(TutorialEntity.builder().codigo(1).ordem(1).descricao("Tutorial 1").etapas(new ArrayList<>()).build());
		tutoriais.add(TutorialEntity.builder().codigo(2).ordem(1).descricao("Tutorial 2").etapas(new ArrayList<>()).build());
		
		return tutoriais;
	}
	
	private BuscarTutoriaisResponse montarBuscarTutoriaisResponse() {
		return new BuscarTutoriaisResponse(montarListaTutorialEntity().stream().map(TutorialDTO::new).collect(Collectors.toList()));
	}
	
	private TutorialEntity montarTutorialEntity() {		
		return TutorialEntity.builder().codigo(1).ordem(1).descricao("Tutorial 1").etapas(new ArrayList<>()).build();
	}
	
	private TutorialResponse montarTutorialResponse() {		
		return new TutorialResponse(new TutorialDTO(montarTutorialEntity()));
	}
}
