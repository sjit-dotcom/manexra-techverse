package com.manexra.techverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manexra.techverse.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
