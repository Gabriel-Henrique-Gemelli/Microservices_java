package br.edu.atitus.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.user_service.entity.userEntity;

public interface UserRepository extends JpaRepository<userEntity, Long> {

}
