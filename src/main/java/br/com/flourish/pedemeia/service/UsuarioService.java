package br.com.flourish.pedemeia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.UsuarioRepository;
import br.com.flourish.pedemeia.dto.UsuarioDTO;
import br.com.flourish.pedemeia.exception.BusinessException;

@Service
public class UsuarioService {
	
	private static final String ERRO_SALVAR_USUARIO = "Não foi possível salvar o usuário %s.";
	private static final String ERRO_BUSCAR_USUARIO = "Ocorreu um erro ao buscar usuário %d.";
	private static final String ERRO_USUARIO_INEXISTENTE = "Nenhum usuário com código %d encontrado.";
	
	@Autowired 
	private UsuarioRepository repository;
	
	public UsuarioDTO criar(UsuarioEntity usuario) {			
		return salvar(usuario);
	}
	
	public UsuarioDTO atualizar(UsuarioEntity usuario) {
		return salvar(usuario);
	}

	public UsuarioDTO buscarPorCodigo(Integer codigo) {
		Optional<UsuarioEntity> usuario = Optional.empty();
		
		try {
			usuario = repository.findById(codigo);
		} catch(Exception ex) {
			throw new BusinessException(ERRO_BUSCAR_USUARIO.replace("%d", codigo.toString()));
		}
		
		if(!usuario.isPresent())
			throw new BusinessException(ERRO_USUARIO_INEXISTENTE.replace("%d", codigo.toString()));
		
		return new UsuarioDTO(usuario.get());
	}
	
	private UsuarioDTO salvar(UsuarioEntity usuario) {
		
		try {
			usuario = repository.save(usuario);
		} catch(Exception ex) {
			throw new BusinessException(ERRO_SALVAR_USUARIO.replace("%s", usuario.getEmail()));
		}
				
		return new UsuarioDTO(usuario);
	}
}
