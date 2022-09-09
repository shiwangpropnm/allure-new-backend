package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.Matches;
import com.allure.entity.Users;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer>, MatchesRepositoryCustomDAO {

	Matches findByUsersByUserIdAndUsersByProfileId(Users user, Users profile);

}
