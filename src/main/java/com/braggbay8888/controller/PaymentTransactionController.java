package com.braggbay8888.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbay8888.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbay8888.domain.PaymentTransaction;
import com.braggbay8888.dto.PaymentTransactionDTO;
import com.braggbay8888.dto.PaymentTransactionSearchDTO;
import com.braggbay8888.dto.PaymentTransactionPageDTO;
import com.braggbay8888.service.PaymentTransactionService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/paymentTransaction")
@RestController
public class PaymentTransactionController {

	private final static Logger logger = LoggerFactory.getLogger(PaymentTransactionController.class);

	@Autowired
	PaymentTransactionService paymentTransactionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PaymentTransaction> getAll() {

		List<PaymentTransaction> paymentTransactions = paymentTransactionService.findAll();
		
		return paymentTransactions;	
	}

	@GetMapping(value = "/{paymentTransactionId}")
	@ResponseBody
	public PaymentTransactionDTO getPaymentTransaction(@PathVariable Integer paymentTransactionId) {
		
		return (paymentTransactionService.getPaymentTransactionDTOById(paymentTransactionId));
	}

 	@RequestMapping(value = "/addPaymentTransaction", method = RequestMethod.POST)
	public ResponseEntity<?> addPaymentTransaction(@RequestBody PaymentTransactionDTO paymentTransactionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentTransactionService.addPaymentTransaction(paymentTransactionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/paymentTransactions")
	public ResponseEntity<PaymentTransactionPageDTO> getPaymentTransactions(PaymentTransactionSearchDTO paymentTransactionSearchDTO) {
 
		return paymentTransactionService.getPaymentTransactions(paymentTransactionSearchDTO);
	}	

	@RequestMapping(value = "/updatePaymentTransaction", method = RequestMethod.POST)
	public ResponseEntity<?> updatePaymentTransaction(@RequestBody PaymentTransactionDTO paymentTransactionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentTransactionService.updatePaymentTransaction(paymentTransactionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
