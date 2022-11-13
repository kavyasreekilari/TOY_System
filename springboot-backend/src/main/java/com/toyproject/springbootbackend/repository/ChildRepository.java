package com.toyproject.springbootbackend.repository;

import com.toyproject.springbootbackend.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {

    Child findByEmail(String email);
    Child findByAge(Integer age);
}
