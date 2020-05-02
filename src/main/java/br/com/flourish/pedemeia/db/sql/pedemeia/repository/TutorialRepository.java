package br.com.flourish.pedemeia.db.sql.pedemeia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.flourish.pedemeia.db.sql.pedemeia.entity.TutorialEntity;

@Repository
public interface TutorialRepository extends JpaRepository< TutorialEntity, Integer >{

}
