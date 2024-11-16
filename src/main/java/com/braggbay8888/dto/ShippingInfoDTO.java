package com.braggbay8888.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ShippingInfoDTO {

	private Integer shippingInfoId;

	private String method;

	private double cost;

	private String estimatedDelivery;






}
