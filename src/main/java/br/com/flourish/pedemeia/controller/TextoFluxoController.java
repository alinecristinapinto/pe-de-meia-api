package br.com.flourish.pedemeia.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flourish.pedemeia.controller.response.ResponseDTO;
import br.com.flourish.pedemeia.controller.response.TextoFluxoResponse;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.exception.InvalidAttributeException;
import br.com.flourish.pedemeia.service.TextoFluxoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Fluxos" })
@RequestMapping("/fluxos")
public class TextoFluxoController {
	
	@Autowired 
	private TextoFluxoService service;
	
	private static final String SUCESSO = "Sucesso";
	private static final String ERRO = "Erro";
	
	@GetMapping(value="/buscar/{nomeParametro}")
	@ApiOperation(value="Busca os textos relacionados a um fluxo determinado pelo parâmetro")
    public ResponseEntity<ResponseDTO<Object>> buscar(@PathVariable @NotNull String nomeParametro) {
		ResponseDTO<Object> response = new ResponseDTO<>();
		TextoFluxoResponse texto = new TextoFluxoResponse();
		
		try {
			texto = service.buscar(nomeParametro);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage(SUCESSO);
			response.setResult(texto);			
		} catch(InvalidAttributeException ex) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage(ERRO);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch(BusinessException ex) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(ERRO);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
