package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.Item;





public interface ItemDAO extends GenericDAO<Item, Integer> {
  
	List<Item> findAll();
	






}


