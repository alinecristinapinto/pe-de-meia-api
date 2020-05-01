package br.com.flourish.pedemeia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TesteController {
	
	@RequestMapping(value = "/teste", method = RequestMethod.POST)
    public String teste(@RequestParam String nome) {
		
		return nome;
    }

}
