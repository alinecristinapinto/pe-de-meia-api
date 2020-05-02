package br.com.flourish.pedemeia.controller.request;

import br.com.flourish.pedemeia.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroUsuarioRequest {
   private String email;
   private String nome;
   private String senha;
}
