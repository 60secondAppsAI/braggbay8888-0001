package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.ShippingInfo;





public interface ShippingInfoDAO extends GenericDAO<ShippingInfo, Integer> {
  
	List<ShippingInfo> findAll();
	






}


