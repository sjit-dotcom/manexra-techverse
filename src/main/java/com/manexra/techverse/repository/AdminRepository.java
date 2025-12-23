package com.manexra.techverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manexra.techverse.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String username);
}
