package br.com.flourish.pedemeia.db.sql.pedemeia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.ParametroFluxoEntity;

@Repository
public interface ParametroFluxoRepository extends JpaRepository< ParametroFluxoEntity, Integer>{

}
