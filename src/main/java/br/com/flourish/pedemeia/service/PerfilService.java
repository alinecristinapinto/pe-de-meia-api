package br.com.flourish.pedemeia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.controller.request.CadastrarPerfilEmUsuarioRequest;
import br.com.flourish.pedemeia.controller.response.PerfilResponse;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.PerfilEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.PerfilRepository;
import br.com.flourish.pedemeia.dto.PerfilDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;
import br.com.flourish.pedemeia.utils.ConstraintViolationUtils;

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
	
	public void cadastrarPerfilEmUsuario(CadastrarPerfilEmUsuarioRequest request) {
		verificarRequest(request);
	}
	
	private void verificarRequest(CadastrarPerfilEmUsuarioRequest request) {
		String inconsistencias = ConstraintViolationUtils.verificar(request);
		
		if(StringUtils.isNotBlank(inconsistencias))
			throw new InvalidAttributeException(inconsistencias);
	}
}
