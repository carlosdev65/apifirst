package br.com.dev.API.repository;

import br.com.dev.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

