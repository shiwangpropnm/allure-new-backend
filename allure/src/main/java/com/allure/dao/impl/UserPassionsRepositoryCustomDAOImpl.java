package com.allure.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.allure.dao.UserPassionsRepositoryCustomDAO;
import com.allure.entity.UserPassions;

@Repository
public class UserPassionsRepositoryCustomDAOImpl implements UserPassionsRepositoryCustomDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Integer> getPassionsIdByUsersId(int userId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);

		Root<UserPassions> fromUserPassions = criteriaQuery.from(UserPassions.class);

		criteriaQuery.select(fromUserPassions.get("passions").get("id"))
				.where(criteriaBuilder.equal(fromUserPassions.get("users").get("id"), userId));

		TypedQuery<Integer> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
