package com.training.sprint1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Admin;
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long>{

}
