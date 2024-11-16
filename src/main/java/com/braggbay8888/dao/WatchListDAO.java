package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.WatchList;





public interface WatchListDAO extends GenericDAO<WatchList, Integer> {
  
	List<WatchList> findAll();
	






}


