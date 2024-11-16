package com.braggbay8888.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PaymentTransactionDTO {

	private Integer paymentTransactionId;

	private Date transactionDate;

	private double amount;






}
