package com.nirmalyam.collection.repository;

import com.nirmalyam.collection.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	@Transactional
	@Query(value = "delete from Registration where phone= :phone",nativeQuery = true)
	public String deleteByPhoneNumber(String phone);
}
