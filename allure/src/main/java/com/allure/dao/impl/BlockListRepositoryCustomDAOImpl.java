package com.allure.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.allure.dao.BlockListRepositoryCustomDAO;
import com.allure.entity.Blocklist;
import com.allure.entity.Users;

@Repository
public class BlockListRepositoryCustomDAOImpl implements BlockListRepositoryCustomDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<String> findAllBlockedMobileByUserIdAndBlockedMobileIn(int userId, List<String> mobileNumbers) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);

		Root<Blocklist> fromBlocklist = cq.from(Blocklist.class);

		cq.select(fromBlocklist.get("blockedMobile"))
				.where(cb.and(cb.equal(fromBlocklist.get("user").get("id"), userId),
						cb.in(fromBlocklist.get("blockedMobile")).value(mobileNumbers)));
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<String> findAllBlockedMobileByUserAndBlockedMobileIn(Users user, List<String> mobileNumbers) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);

		Root<Blocklist> fromBlocklist = cq.from(Blocklist.class);

		cq.select(fromBlocklist.get("blockedMobile")).where(cb.and(cb.equal(fromBlocklist.get("user"), user),
				cb.in(fromBlocklist.get("blockedMobile")).value(mobileNumbers)));
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<String> findBlockedMobileByUser(Users user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);

		Root<Blocklist> fromBlocklist = cq.from(Blocklist.class);

		cq.select(fromBlocklist.get("blockedMobile")).where(cb.equal(fromBlocklist.get("user"), user));
		return entityManager.createQuery(cq).getResultList();
	}

}
