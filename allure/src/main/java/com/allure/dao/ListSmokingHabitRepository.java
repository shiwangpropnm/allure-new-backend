package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListSmokingHabit;

@Repository
public interface ListSmokingHabitRepository extends JpaRepository<ListSmokingHabit, Integer> {

}
