package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


