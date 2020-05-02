package br.com.flourish.pedemeia.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.UsuarioRepository;
import br.com.flourish.pedemeia.dto.UsuarioDTO;
import br.com.flourish.pedemeia.exception.BusinessException;

public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService service;

	@Mock
	private UsuarioRepository repository;

	private static final Integer CODIGO_USUARIO = 1;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = BusinessException.class)
	public void criar_erroCriarUsuario() {
		Mockito.when(repository.save(Mockito.any(UsuarioEntity.class))).thenThrow(BusinessException.class);

		UsuarioDTO response = service.criar(montarUsuarioEntity());
		
		Assert.assertEquals(montarUsuarioDTO(), response);
	}

	@Test
	public void criar_sucesso() {
		Mockito.when(repository.save(Mockito.any(UsuarioEntity.class))).thenReturn(montarUsuarioEntity());

		service.criar(montarUsuarioEntity());
	}
	
	@Test(expected = BusinessException.class)
	public void buscarPorCodigo_erroBuscarUsuario() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(BusinessException.class);

		service.buscarPorCodigo(CODIGO_USUARIO);
	}

	@Test(expected = BusinessException.class)
	public void buscarPorCodigo_sucessoSemTexto() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		service.buscarPorCodigo(CODIGO_USUARIO);
	}

	@Test
	public void buscarPorCodigo_sucesso() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(montarUsuarioEntity()));

		UsuarioDTO response = service.buscarPorCodigo(CODIGO_USUARIO);

		Assert.assertEquals(montarUsuarioDTO(), response);
	}
	
	//--------------------------------------------------------------------------------------------------------------------

	private UsuarioEntity montarUsuarioEntity() {
		return UsuarioEntity.builder().codigo(1).email("email@email.com").nome("nome usuário").senha("hash senha")
				.codigoNivel(1).codigoPerfil(1).build();
	}

	private UsuarioDTO montarUsuarioDTO() {
		return new UsuarioDTO(montarUsuarioEntity());
	}
}
