package br.com.flourish.pedemeia.controller;

import br.com.flourish.pedemeia.controller.request.CadastroUsuarioRequest;
import br.com.flourish.pedemeia.service.CadastroService;
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
    public ResponseEntity<Object> cadastro(@RequestBody CadastroUsuarioRequest request){
        try {
            return new ResponseEntity<>(
                    cadastroService.cadastrarUsuario(request.usuarioDTO),
                    HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(
                    "",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
