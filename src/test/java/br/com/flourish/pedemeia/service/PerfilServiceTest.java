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

import br.com.flourish.pedemeia.controller.request.CadastrarPerfilEmUsuarioRequest;
import br.com.flourish.pedemeia.controller.response.PerfilResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.PerfilEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.PerfilRepository;
import br.com.flourish.pedemeia.dto.PerfilDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

public class PerfilServiceTest {
	
	@InjectMocks
	private PerfilService service;
	
	@Mock
	private PerfilRepository repository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = BusinessException.class)
	public void buscar_erroBuscarTexto() {
		Mockito.when(repository.findAll()).thenThrow(BusinessException.class);

		service.buscar();
	}
	
	@Test
	public void buscar_sucesso() {
		Mockito.when(repository.findAll()).thenReturn(montarPerfilEntity());

		PerfilResponse response = service.buscar();
		
		Assert.assertEquals(montarPerfilResponse(), response);
	}
	
	@Test(expected = InvalidAttributeException.class)
	public void cadastrarPerfilEmUsuario_erroRequestNula() {
		service.cadastrarPerfilEmUsuario(null);
	}
	
	@Test(expected = InvalidAttributeException.class)
	public void cadastrarPerfilEmUsuario_erroRequestInvalida() {
		service.cadastrarPerfilEmUsuario(new CadastrarPerfilEmUsuarioRequest());
	}
	
	//----------------------------------------------------------------------------------------------------------------
	
	private List<PerfilEntity> montarPerfilEntity() {
		List<PerfilEntity> perfis = new ArrayList<>();
		
		perfis.add(PerfilEntity.builder().codigo(1).descricao("Economizar").imagem("hash icone").build());
		perfis.add(PerfilEntity.builder().codigo(2).descricao("Investir").imagem("hash icone").build());
		perfis.add(PerfilEntity.builder().codigo(3).descricao("Sair do vermelho").imagem("hash icone").build());
		
		return perfis;
	}
	
	private PerfilResponse montarPerfilResponse() {
		List<PerfilDTO> perfis = montarPerfilEntity().stream().map(PerfilDTO::new).collect(Collectors.toList());
		return new PerfilResponse(perfis);
	}

}
