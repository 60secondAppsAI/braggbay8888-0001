package com.braggbay8888.dao;

import java.util.List;

import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.domain.SupportTicket;





public interface SupportTicketDAO extends GenericDAO<SupportTicket, Integer> {
  
	List<SupportTicket> findAll();
	






}


