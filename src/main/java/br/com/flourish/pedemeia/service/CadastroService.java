package br.com.flourish.pedemeia.service;

import br.com.flourish.pedemeia.exception.InvalidAttributeException;
import org.apache.commons.lang3.StringUtils;
import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;
import br.com.flourish.pedemeia.db.sql.pedemeia.repository.UsuarioRepository;
import br.com.flourish.pedemeia.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final String ERRO_CAMPO_INVALIDO = "Parâmetro recebido está nulo ou vazio.";
    private static final String ERRO_EMAIL_INVALIDO = "O e-mail está com formato inválido.";

    public void cadastrarUsuario(UsuarioDTO usuarioDTO){

        verificaRequest(usuarioDTO);

        UsuarioEntity usuarioASalvar = UsuarioEntity.builder().email(usuarioDTO.getEmail()).nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha()).build();

        usuarioRepository.save(usuarioASalvar);

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
        if(validarEmail(email)){
            throw new InvalidAttributeException(ERRO_EMAIL_INVALIDO);
        }
    }

    private static boolean validarEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
