package com.allure.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.allure.dao.UserSexualOrientationsRepositoryCustomDAO;
import com.allure.entity.UserSexualOrientations;

@Repository
public class UserSexualOrientationsRepositoryCustomDAOImpl implements UserSexualOrientationsRepositoryCustomDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Integer> getSexualOrientationIdsByUsersId(int userId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);

		Root<UserSexualOrientations> fromUserSexualOrientations = criteriaQuery.from(UserSexualOrientations.class);

		criteriaQuery.select(fromUserSexualOrientations.get("sexualOrientations").get("id"))
				.where(criteriaBuilder.equal(fromUserSexualOrientations.get("users").get("id"), userId));

		TypedQuery<Integer> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
