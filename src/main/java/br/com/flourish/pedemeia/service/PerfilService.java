package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.response.PerfilResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.PerfilEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.PerfilRepository;
import br.com.flourish.pedemeia.dto.PerfilDTO;
import br.com.flourish.pedemeia.exception.BusinessException;

@Service
public class PerfilService {
	
	@Autowired 
	private PerfilRepository repository;
	
	private static final String ERRO_BUSCAR_PERFIS = "Ocorreu um erro ao buscar perfis.";

	public PerfilResponse buscar() {
		List<PerfilEntity> perfis = new ArrayList<>();
		
		try {
			perfis = repository.findAll();
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_PERFIS);
		}
		
		return new PerfilResponse(perfis.stream().map(PerfilDTO::new).collect(Collectors.toList()));
	}
}
