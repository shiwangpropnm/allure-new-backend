package com.allure.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.allure.dao.ProfileLikeDislikeRepositoryCustomDAO;
import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;

@Repository
public class ProfileLikeDislikeRepositoryCustomDAOImpl implements ProfileLikeDislikeRepositoryCustomDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Users> findUsersProfileSwipedByUser(int userId, String likeStatus) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);

		Root<ProfileLikeDislike> profileLikeDislike = criteriaQuery.from(ProfileLikeDislike.class);

		criteriaQuery.select(profileLikeDislike.get("usersByProfileId"))
				.where(criteriaBuilder.and(
						criteriaBuilder.equal(profileLikeDislike.get("usersByUserId").get("id"), userId),
						criteriaBuilder.equal(profileLikeDislike.get("likeStatus"), likeStatus)));

		TypedQuery<Users> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public List<Users> findUsersProfilesWhoSwipedUser(int userId, String likeStatus) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);

		Root<ProfileLikeDislike> profileLikeDislike = criteriaQuery.from(ProfileLikeDislike.class);

		criteriaQuery.select(profileLikeDislike.get("usersByUserId"))
				.where(criteriaBuilder.and(
						criteriaBuilder.equal(profileLikeDislike.get("usersByProfileId").get("id"), userId),
						criteriaBuilder.equal(profileLikeDislike.get("likeStatus"), likeStatus)));

		TypedQuery<Users> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
