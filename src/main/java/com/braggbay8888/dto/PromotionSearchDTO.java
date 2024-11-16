package com.braggbay8888.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PromotionSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer promotionId;
	
	private String name;
	
	private double discountRate;
	
	private Date startDate;
	
	private Date endDate;
	
}
