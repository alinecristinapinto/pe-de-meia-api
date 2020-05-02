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

public class CadastroServiceTest {

    @InjectMocks
    private CadastroService cadastroService;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = BusinessException.class)
    public void cadastrar_erroSalvarUsuario() {
        Mockito.when(usuarioService.criar(Mockito.any(UsuarioEntity.class))).thenThrow(BusinessException.class);

        cadastroService.cadastrarUsuario(usuarioDTOBuilder());
    }

    @Test(expected = InvalidAttributeException.class)
    public void cadastrar_ErroCamposVazios(){
        cadastroService.cadastrarUsuario(new UsuarioDTO());
    }

    @Test(expected = InvalidAttributeException.class)
    public void cadastrar_ErroCampoEmail(){
        cadastroService.cadastrarUsuario(usuarioDTOBuilderEmailInvalido());
    }

    @Test
    public void cadastrar_sucesso() {
        Mockito.when(usuarioService.criar(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioDTOBuilder());
        cadastroService.cadastrarUsuario(usuarioDTOBuilder());
    }

    //----------------------------------------------------------------------------------------------------------------
    private UsuarioDTO usuarioDTOBuilder(){
        return UsuarioDTO.builder().email("teste@gmail.com").nome("Eduardo Cotta").senha("12345").build();
    }

    private UsuarioDTO usuarioDTOBuilderEmailInvalido(){
        return UsuarioDTO.builder().email("teste").nome("Eduardo Cotta").senha("12345").build();
    }
}
