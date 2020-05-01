package br.com.flourish.pedemeia.service;

import java.util.Optional;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.response.TextoFluxoResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.ParametroFluxoEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.ParametroFluxoRepository;
import br.com.flourish.pedemeia.dto.ParametroFluxoDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;

@Service
public class TextoFluxoService {

	@Autowired 
	private ParametroFluxoRepository parametroFluxoRepository;
	
	private static final String ERRO_CAMPO_INVALIDO = "Parâmetro recebido está nulo ou vazio.";
	private static final String ERRO_BUSCAR_TEXTO = "Ocorreu um erro ao buscar textos por parametro %s.";
	
	public TextoFluxoResponse buscar(String nomeParametro) {
		Optional<ParametroFluxoEntity> texto = Optional.empty();
		
		verificarRequest(nomeParametro);
		
		try {
			texto = parametroFluxoRepository.findByDescricao(nomeParametro);
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_TEXTO.replace("%s", nomeParametro));
		}
		
		if(!texto.isPresent())
			return new TextoFluxoResponse();
	
		return new TextoFluxoResponse(new ParametroFluxoDTO(texto.get()));
	}
	
	public void verificarRequest(String parametro) {
		if(StringUtils.isBlank(parametro))
			throw new InvalidAttributeException(ERRO_CAMPO_INVALIDO);
	}
	
}
