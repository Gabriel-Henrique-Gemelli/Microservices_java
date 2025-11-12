package br.edu.atitus.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.atitus.user_service.entity.userEntity;

@Repository
public interface UserRepository extends JpaRepository<userEntity, Long> {

}
