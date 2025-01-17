package com.shopworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopworld.entities.UserDtls;


@Repository
public interface UserRepository extends JpaRepository<UserDtls, Integer>{

	public UserDtls findByEmail(String email);

	public List<UserDtls> findByRole(String role);
	
	public UserDtls findByResetToken(String token);
	
	public Boolean existsByEmail(String email);
	
}
