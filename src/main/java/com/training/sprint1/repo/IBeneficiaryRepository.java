package com.training.sprint1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Beneficiary;
@Repository

public interface IBeneficiaryRepository extends JpaRepository<Beneficiary, Long>{	

	Beneficiary findByBeneficiaryId(Long beneficiaryId);

}
