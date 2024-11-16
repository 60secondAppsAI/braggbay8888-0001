package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.ShoppingCart;





public interface ShoppingCartDAO extends GenericDAO<ShoppingCart, Integer> {
  
	List<ShoppingCart> findAll();
	






}


