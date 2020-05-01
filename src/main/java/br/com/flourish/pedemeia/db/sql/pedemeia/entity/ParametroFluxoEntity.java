package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_PARAMETRO_FLUXO", schema="dbo", catalog="ddleh83ugnm8mc")
public class ParametroFluxoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_parametro_fluxo", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_parametro", unique=true, nullable = false)
	private String descricao; 
	
	@OneToMany
	private List<FluxoEntity> fluxo;
}
