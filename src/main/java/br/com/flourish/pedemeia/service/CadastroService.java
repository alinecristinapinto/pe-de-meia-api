package br.com.flourish.pedemeia.service;

import br.com.flourish.pedemeia.exception.InvalidAttributeException;
import br.com.flourish.pedemeia.utils.ValidadoresUtils;
import org.apache.commons.lang3.StringUtils;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import br.com.flourish.pedemeia.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private UsuarioService usuarioService;
    private static final String ERRO_CAMPO_INVALIDO = "Parâmetro recebido está nulo ou vazio.";
    private static final String ERRO_EMAIL_INVALIDO = "O e-mail está com formato inválido.";

    public void cadastrarUsuario(UsuarioDTO usuarioDTO){

        verificaRequest(usuarioDTO);

        usuarioService.criar(new UsuarioEntity(usuarioDTO));
    }

    private void verificaRequest(UsuarioDTO usuarioDTO) {
        verificaCamposVazios(usuarioDTO.getEmail(), usuarioDTO.getNome(), usuarioDTO.getSenha());
        verificaEmailUsuario(usuarioDTO.getEmail());
    }

    private void verificaCamposVazios(String email, String nome, String senha) {
        if(StringUtils.isAnyBlank(email, nome, senha)){
            throw new InvalidAttributeException(ERRO_CAMPO_INVALIDO);
        }
    }

    private void verificaEmailUsuario(String email) {
        if(!ValidadoresUtils.validarEmail(email)){
            throw new InvalidAttributeException(ERRO_EMAIL_INVALIDO);
        }
    }
}
