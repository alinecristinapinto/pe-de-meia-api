package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_FLUXO", schema="dbo", catalog="ddleh83ugnm8mc")
public class FluxoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_fluxo", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_fluxo", nullable = false)
	private String descricao;
}
