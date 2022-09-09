package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.Blocklist;
import com.allure.entity.Users;

@Repository
public interface BlockListRepository extends JpaRepository<Blocklist, Integer>, BlockListRepositoryCustomDAO {

	List<Blocklist> findAllByUserAndBlockedMobileIn(Users user, List<String> mobileNumbers);
}
