package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


