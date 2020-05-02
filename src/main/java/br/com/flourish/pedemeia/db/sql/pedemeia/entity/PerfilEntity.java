package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TBL_PERFIL")
public class PerfilEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_perfil", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_perfil", nullable = false)
	private String descricao;
	
	@Column(name="ds_imagem", nullable = false)
	private String imagem;
	
}
