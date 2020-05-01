package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.flourish.pedemeia.controller.response.TextoFluxoResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.ParametroFluxoEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.ParametroFluxoRepository;
import br.com.flourish.pedemeia.dto.ParametroFluxoDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

public class TextoFluxoServiceTest {

	@InjectMocks
	private TextoFluxoService service;

	private static final String PARAMETRO = "PARAMETRO_TESTE";

	@Mock
	private ParametroFluxoRepository parametroFluxoRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = InvalidAttributeException.class)
	public void buscar_erroParametroNulo() {
		service.buscar(null);
	}

	@Test(expected = InvalidAttributeException.class)
	public void buscar_erroParametroVazio() {
		service.buscar(StringUtils.EMPTY);
	}

	@Test(expected = BusinessException.class)
	public void buscar_erroBuscarTexto() {
		Mockito.when(parametroFluxoRepository.findByDescricao(Mockito.anyString())).thenThrow(BusinessException.class);

		service.buscar(PARAMETRO);
	}

	@Test
	public void buscar_sucessoSemTexto() {
		Mockito.when(parametroFluxoRepository.findByDescricao(Mockito.anyString())).thenReturn(Optional.empty());

		TextoFluxoResponse response = service.buscar(PARAMETRO);

		Assert.assertEquals(new TextoFluxoResponse(), response);
	}

	@Test
	public void buscar_sucesso() {
		Mockito.when(parametroFluxoRepository.findByDescricao(Mockito.anyString()))
				.thenReturn(Optional.of(montarParametroFluxoEntity()));

		TextoFluxoResponse response = service.buscar(PARAMETRO);

		Assert.assertEquals(montarTextoFluxoResponse(), response);
	}

	private ParametroFluxoEntity montarParametroFluxoEntity() {
		return ParametroFluxoEntity.builder().codigo(1).descricao(PARAMETRO).fluxo(new ArrayList<>()).build();
	}
	
	private TextoFluxoResponse montarTextoFluxoResponse() {
		return new TextoFluxoResponse(new ParametroFluxoDTO(montarParametroFluxoEntity()));
	}
}
