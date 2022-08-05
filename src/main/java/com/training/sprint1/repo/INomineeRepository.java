package com.training.sprint1.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Nominee;
@Repository
public interface INomineeRepository extends JpaRepository <Nominee, Long> {


}
