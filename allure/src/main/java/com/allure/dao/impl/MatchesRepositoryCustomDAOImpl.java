package com.allure.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.allure.dao.MatchesRepositoryCustomDAO;
import com.allure.entity.Matches;
import com.allure.entity.Users;

public class MatchesRepositoryCustomDAOImpl implements MatchesRepositoryCustomDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Users> findMatchedUserProfiles(int userId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Users> cq = cb.createQuery(Users.class);
		Root<Matches> matchesA = cq.from(Matches.class);

		cq.select(matchesA.get("usersByProfileId"))
				.where(cb.equal(matchesA.get("usersByUserId").get("id"), cb.parameter(Integer.class, "user_id")));

		return entityManager.createQuery(cq).setParameter("user_id", userId).getResultList();
	}

}
