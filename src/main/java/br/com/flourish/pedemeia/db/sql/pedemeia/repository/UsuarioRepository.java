package br.com.flourish.pedemeia.db.sql.pedemeia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository< UsuarioEntity, Integer >{

}
