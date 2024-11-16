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
@Table(name="listings")
@Getter @Setter @NoArgsConstructor
public class Listing {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="listing_id")
	private Integer listingId;
    
  	@Column(name="list_date")
	private Date listDate;
    
  	@Column(name="status")
	private String status;
    
	




}
