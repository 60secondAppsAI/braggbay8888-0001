package com.braggbay8888.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.service.impl.GenericServiceImpl;
import com.braggbay8888.dao.PaymentTransactionDAO;
import com.braggbay8888.domain.PaymentTransaction;
import com.braggbay8888.dto.PaymentTransactionDTO;
import com.braggbay8888.dto.PaymentTransactionSearchDTO;
import com.braggbay8888.dto.PaymentTransactionPageDTO;
import com.braggbay8888.dto.PaymentTransactionConvertCriteriaDTO;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import com.braggbay8888.service.PaymentTransactionService;
import com.braggbay8888.util.ControllerUtils;





@Service
public class PaymentTransactionServiceImpl extends GenericServiceImpl<PaymentTransaction, Integer> implements PaymentTransactionService {

    private final static Logger logger = LoggerFactory.getLogger(PaymentTransactionServiceImpl.class);

	@Autowired
	PaymentTransactionDAO paymentTransactionDao;

	


	@Override
	public GenericDAO<PaymentTransaction, Integer> getDAO() {
		return (GenericDAO<PaymentTransaction, Integer>) paymentTransactionDao;
	}
	
	public List<PaymentTransaction> findAll () {
		List<PaymentTransaction> paymentTransactions = paymentTransactionDao.findAll();
		
		return paymentTransactions;	
		
	}

	public ResultDTO addPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO, RequestDTO requestDTO) {

		PaymentTransaction paymentTransaction = new PaymentTransaction();

		paymentTransaction.setPaymentTransactionId(paymentTransactionDTO.getPaymentTransactionId());


		paymentTransaction.setTransactionDate(paymentTransactionDTO.getTransactionDate());


		paymentTransaction.setAmount(paymentTransactionDTO.getAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		paymentTransaction = paymentTransactionDao.save(paymentTransaction);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PaymentTransaction> getAllPaymentTransactions(Pageable pageable) {
		return paymentTransactionDao.findAll(pageable);
	}

	public Page<PaymentTransaction> getAllPaymentTransactions(Specification<PaymentTransaction> spec, Pageable pageable) {
		return paymentTransactionDao.findAll(spec, pageable);
	}

	public ResponseEntity<PaymentTransactionPageDTO> getPaymentTransactions(PaymentTransactionSearchDTO paymentTransactionSearchDTO) {
	
			Integer paymentTransactionId = paymentTransactionSearchDTO.getPaymentTransactionId(); 
    			String sortBy = paymentTransactionSearchDTO.getSortBy();
			String sortOrder = paymentTransactionSearchDTO.getSortOrder();
			String searchQuery = paymentTransactionSearchDTO.getSearchQuery();
			Integer page = paymentTransactionSearchDTO.getPage();
			Integer size = paymentTransactionSearchDTO.getSize();

	        Specification<PaymentTransaction> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, paymentTransactionId, "paymentTransactionId"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<PaymentTransaction> paymentTransactions = this.getAllPaymentTransactions(spec, pageable);
		
		//System.out.println(String.valueOf(paymentTransactions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(paymentTransactions.getTotalPages()));
		
		List<PaymentTransaction> paymentTransactionsList = paymentTransactions.getContent();
		
		PaymentTransactionConvertCriteriaDTO convertCriteria = new PaymentTransactionConvertCriteriaDTO();
		List<PaymentTransactionDTO> paymentTransactionDTOs = this.convertPaymentTransactionsToPaymentTransactionDTOs(paymentTransactionsList,convertCriteria);
		
		PaymentTransactionPageDTO paymentTransactionPageDTO = new PaymentTransactionPageDTO();
		paymentTransactionPageDTO.setPaymentTransactions(paymentTransactionDTOs);
		paymentTransactionPageDTO.setTotalElements(paymentTransactions.getTotalElements());
		return ResponseEntity.ok(paymentTransactionPageDTO);
	}

	public List<PaymentTransactionDTO> convertPaymentTransactionsToPaymentTransactionDTOs(List<PaymentTransaction> paymentTransactions, PaymentTransactionConvertCriteriaDTO convertCriteria) {
		
		List<PaymentTransactionDTO> paymentTransactionDTOs = new ArrayList<PaymentTransactionDTO>();
		
		for (PaymentTransaction paymentTransaction : paymentTransactions) {
			paymentTransactionDTOs.add(convertPaymentTransactionToPaymentTransactionDTO(paymentTransaction,convertCriteria));
		}
		
		return paymentTransactionDTOs;

	}
	
	public PaymentTransactionDTO convertPaymentTransactionToPaymentTransactionDTO(PaymentTransaction paymentTransaction, PaymentTransactionConvertCriteriaDTO convertCriteria) {
		
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		
		paymentTransactionDTO.setPaymentTransactionId(paymentTransaction.getPaymentTransactionId());

	
		paymentTransactionDTO.setTransactionDate(paymentTransaction.getTransactionDate());

	
		paymentTransactionDTO.setAmount(paymentTransaction.getAmount());

	

		
		return paymentTransactionDTO;
	}

	public ResultDTO updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO, RequestDTO requestDTO) {
		
		PaymentTransaction paymentTransaction = paymentTransactionDao.getById(paymentTransactionDTO.getPaymentTransactionId());

		paymentTransaction.setPaymentTransactionId(ControllerUtils.setValue(paymentTransaction.getPaymentTransactionId(), paymentTransactionDTO.getPaymentTransactionId()));

		paymentTransaction.setTransactionDate(ControllerUtils.setValue(paymentTransaction.getTransactionDate(), paymentTransactionDTO.getTransactionDate()));

		paymentTransaction.setAmount(ControllerUtils.setValue(paymentTransaction.getAmount(), paymentTransactionDTO.getAmount()));



        paymentTransaction = paymentTransactionDao.save(paymentTransaction);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PaymentTransactionDTO getPaymentTransactionDTOById(Integer paymentTransactionId) {
	
		PaymentTransaction paymentTransaction = paymentTransactionDao.getById(paymentTransactionId);
			
		
		PaymentTransactionConvertCriteriaDTO convertCriteria = new PaymentTransactionConvertCriteriaDTO();
		return(this.convertPaymentTransactionToPaymentTransactionDTO(paymentTransaction,convertCriteria));
	}







}
