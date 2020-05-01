package br.com.flourish.pedemeia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.response.TextoFluxoResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.ParametroFluxoRepository;

@Service
public class TextoFluxoService {

	@Autowired 
	private ParametroFluxoRepository parametroFluxoRepository;
	
	private TextoFluxoResponse buscar(String nomeParametro) {
		
		
		
		return null;
	}
	
}
