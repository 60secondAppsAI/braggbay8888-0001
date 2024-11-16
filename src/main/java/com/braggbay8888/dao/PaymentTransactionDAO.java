package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.PaymentTransaction;





public interface PaymentTransactionDAO extends GenericDAO<PaymentTransaction, Integer> {
  
	List<PaymentTransaction> findAll();
	






}


