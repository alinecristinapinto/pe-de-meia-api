package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name="TBL_FLUXO")
public class FluxoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_fluxo", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_ordem", nullable = false)
	private Integer ordem;
	
	@Column(name="ds_fluxo", nullable = false)
	private String descricao;
	
	@Column(name="cd_parametro_fluxo", nullable = false)
	private Integer codigoParametro;

}
