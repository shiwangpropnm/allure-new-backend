package com.allure.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.Users;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Integer>, UserCustomDAO {

	Users findByEmail(String email);

	Users findByMobile(String phoneNumber);

	Users findByEmailAndAccStatus(String username, boolean accStatus);

	Users findByMobileAndIsRegistered(String phoneNumber, boolean isRegistered);

	Users findByEmailAndIsRegistered(String email, boolean isRegistered);

	Users findByIdAndIsRegistered(Integer userId, boolean isRegistered);

	List<Users> findAllByIsRegistered(boolean isRegistered, Pageable pageable);

	Users findByIdAndIsRegisteredAndAccStatus(Integer userId, boolean isRegistered, boolean accStatus);

	Users findByUsername(String username);

	Users findByEmailAndIsRegisteredAndAccStatus(String email, boolean isRegistered, boolean accStatus);
}
