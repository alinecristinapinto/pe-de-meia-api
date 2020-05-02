package br.com.flourish.pedemeia.controller;

import br.com.flourish.pedemeia.controller.request.CadastroUsuarioRequest;
import br.com.flourish.pedemeia.controller.response.PerfilResponse;
import br.com.flourish.pedemeia.controller.response.ResponseDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.service.CadastroService;
import br.com.flourish.pedemeia.utils.ApiMessagesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = { "Cadastro" })
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/usuario")
    @ApiOperation(value="Cadastra um usuário no banco de dados")
    public ResponseEntity<ResponseDTO<Object>> cadastro(@RequestBody CadastroUsuarioRequest request){
        ResponseDTO<Object> response = new ResponseDTO<>();

        try {
            cadastroService.cadastrarUsuario(request.usuarioDTO);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(ApiMessagesUtils.RETORNO_SUCESSO);
        } catch(BusinessException ex) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
