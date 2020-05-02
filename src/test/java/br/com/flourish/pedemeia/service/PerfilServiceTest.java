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
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.PerfilRepository;
import br.com.flourish.pedemeia.dto.PerfilDTO;
import br.com.flourish.pedemeia.dto.UsuarioDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

public class PerfilServiceTest {
	
	@InjectMocks
	private PerfilService service;
	
	@Mock
	private PerfilRepository repository;
	
	@Mock
	private UsuarioService usuarioService;
	
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
	
	@Test(expected = BusinessException.class)
	public void cadastrarPerfilEmUsuario_erroBuscarUsuario() {
		Mockito.when(usuarioService.buscarPorCodigo(Mockito.anyInt())).thenThrow(BusinessException.class);
		Mockito.when(usuarioService.atualizar(Mockito.any(UsuarioEntity.class))).thenReturn(montarUsuarioDTO());
		
		service.cadastrarPerfilEmUsuario(montarCadastrarPerfilEmUsuarioRequest());
	}
	
	@Test(expected = BusinessException.class)
	public void cadastrarPerfilEmUsuario_erroAtualizarUsuario() {
		Mockito.when(usuarioService.buscarPorCodigo(Mockito.anyInt())).thenReturn(montarUsuarioDTO());
		Mockito.when(usuarioService.atualizar(Mockito.any(UsuarioEntity.class))).thenThrow(BusinessException.class);
		
		service.cadastrarPerfilEmUsuario(montarCadastrarPerfilEmUsuarioRequest());
	}
	
	@Test
	public void cadastrarPerfilEmUsuario_sucesso() {
		Mockito.when(usuarioService.buscarPorCodigo(Mockito.anyInt())).thenReturn(montarUsuarioDTO());
		Mockito.when(usuarioService.atualizar(Mockito.any(UsuarioEntity.class))).thenReturn(montarUsuarioDTO());
		
		service.cadastrarPerfilEmUsuario(montarCadastrarPerfilEmUsuarioRequest());
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
	
	private CadastrarPerfilEmUsuarioRequest montarCadastrarPerfilEmUsuarioRequest() {
		return CadastrarPerfilEmUsuarioRequest.builder().codigoUsuario(1).codigoPerfil(1).build();
	}

	private UsuarioEntity montarUsuarioEntity() {
		return UsuarioEntity.builder().codigo(1).email("email@email.com").nome("nome usuário").senha("hash senha")
				.codigoNivel(1).codigoPerfil(1).build();
	}

	private UsuarioDTO montarUsuarioDTO() {
		return new UsuarioDTO(montarUsuarioEntity());
	}
}
