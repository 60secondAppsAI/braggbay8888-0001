package com.braggbay8888.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="shipping_infos")
@Getter @Setter @NoArgsConstructor
public class ShippingInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="shipping_info_id")
	private Integer shippingInfoId;
    
  	@Column(name="method")
	private String method;
    
  	@Column(name="cost")
	private double cost;
    
  	@Column(name="estimated_delivery")
	private String estimatedDelivery;
    
	




}
