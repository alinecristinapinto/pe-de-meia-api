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
@Table(name="TBL_ETAPA_TUTORIAL")
public class EtapaTutorial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_etapa_tutorial", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="cd_tutorial", nullable = false)
	private Integer codigoTutorial;
	
	@Column(name="ds_etapa", nullable = false)
	private String descricao; 
}
