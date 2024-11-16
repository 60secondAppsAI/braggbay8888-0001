package com.braggbay8888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay8888.domain.PaymentTransaction;
import com.braggbay8888.dto.PaymentTransactionDTO;
import com.braggbay8888.dto.PaymentTransactionSearchDTO;
import com.braggbay8888.dto.PaymentTransactionPageDTO;
import com.braggbay8888.dto.PaymentTransactionConvertCriteriaDTO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PaymentTransactionService extends GenericService<PaymentTransaction, Integer> {

	List<PaymentTransaction> findAll();

	ResultDTO addPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO, RequestDTO requestDTO);

	ResultDTO updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO, RequestDTO requestDTO);

    Page<PaymentTransaction> getAllPaymentTransactions(Pageable pageable);

    Page<PaymentTransaction> getAllPaymentTransactions(Specification<PaymentTransaction> spec, Pageable pageable);

	ResponseEntity<PaymentTransactionPageDTO> getPaymentTransactions(PaymentTransactionSearchDTO paymentTransactionSearchDTO);
	
	List<PaymentTransactionDTO> convertPaymentTransactionsToPaymentTransactionDTOs(List<PaymentTransaction> paymentTransactions, PaymentTransactionConvertCriteriaDTO convertCriteria);

	PaymentTransactionDTO getPaymentTransactionDTOById(Integer paymentTransactionId);







}





