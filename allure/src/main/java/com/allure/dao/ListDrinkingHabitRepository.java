package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListDrinkingHabit;

@Repository
public interface ListDrinkingHabitRepository extends JpaRepository<ListDrinkingHabit, Integer> {

}
