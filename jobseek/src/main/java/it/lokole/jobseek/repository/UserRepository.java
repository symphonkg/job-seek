package it.lokole.jobseek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.lokole.jobseek.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
}
