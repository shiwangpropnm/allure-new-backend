package com.allure.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PassionCategories;

@Repository
public interface PassionCategoriesRepository extends CrudRepository<PassionCategories, Integer>{

	List<PassionCategories> findAllByIsDeleted(boolean isDeleted);

}
