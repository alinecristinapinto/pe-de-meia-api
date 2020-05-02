package br.com.flourish.pedemeia.db.sql.pedemeia.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="TBL_TUTORIAL")
public class TutorialEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_tutorial", unique=true, nullable = false)
	private Integer codigo;
	
	@Column(name="ds_tutorial", nullable = false)
	private String descricao; 
	
	@OneToMany
	@JoinColumn(name = "cd_tutorial", referencedColumnName = "cd_tutorial")
	private List<EtapaTutorial> etapas;

}
