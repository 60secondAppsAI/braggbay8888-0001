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
import com.braggbay8888.dao.PromotionDAO;
import com.braggbay8888.domain.Promotion;
import com.braggbay8888.dto.PromotionDTO;
import com.braggbay8888.dto.PromotionSearchDTO;
import com.braggbay8888.dto.PromotionPageDTO;
import com.braggbay8888.dto.PromotionConvertCriteriaDTO;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import com.braggbay8888.service.PromotionService;
import com.braggbay8888.util.ControllerUtils;





@Service
public class PromotionServiceImpl extends GenericServiceImpl<Promotion, Integer> implements PromotionService {

    private final static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	PromotionDAO promotionDao;

	


	@Override
	public GenericDAO<Promotion, Integer> getDAO() {
		return (GenericDAO<Promotion, Integer>) promotionDao;
	}
	
	public List<Promotion> findAll () {
		List<Promotion> promotions = promotionDao.findAll();
		
		return promotions;	
		
	}

	public ResultDTO addPromotion(PromotionDTO promotionDTO, RequestDTO requestDTO) {

		Promotion promotion = new Promotion();

		promotion.setPromotionId(promotionDTO.getPromotionId());


		promotion.setName(promotionDTO.getName());


		promotion.setDiscountRate(promotionDTO.getDiscountRate());


		promotion.setStartDate(promotionDTO.getStartDate());


		promotion.setEndDate(promotionDTO.getEndDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		promotion = promotionDao.save(promotion);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Promotion> getAllPromotions(Pageable pageable) {
		return promotionDao.findAll(pageable);
	}

	public Page<Promotion> getAllPromotions(Specification<Promotion> spec, Pageable pageable) {
		return promotionDao.findAll(spec, pageable);
	}

	public ResponseEntity<PromotionPageDTO> getPromotions(PromotionSearchDTO promotionSearchDTO) {
	
			Integer promotionId = promotionSearchDTO.getPromotionId(); 
 			String name = promotionSearchDTO.getName(); 
      			String sortBy = promotionSearchDTO.getSortBy();
			String sortOrder = promotionSearchDTO.getSortOrder();
			String searchQuery = promotionSearchDTO.getSearchQuery();
			Integer page = promotionSearchDTO.getPage();
			Integer size = promotionSearchDTO.getSize();

	        Specification<Promotion> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, promotionId, "promotionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Promotion> promotions = this.getAllPromotions(spec, pageable);
		
		//System.out.println(String.valueOf(promotions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(promotions.getTotalPages()));
		
		List<Promotion> promotionsList = promotions.getContent();
		
		PromotionConvertCriteriaDTO convertCriteria = new PromotionConvertCriteriaDTO();
		List<PromotionDTO> promotionDTOs = this.convertPromotionsToPromotionDTOs(promotionsList,convertCriteria);
		
		PromotionPageDTO promotionPageDTO = new PromotionPageDTO();
		promotionPageDTO.setPromotions(promotionDTOs);
		promotionPageDTO.setTotalElements(promotions.getTotalElements());
		return ResponseEntity.ok(promotionPageDTO);
	}

	public List<PromotionDTO> convertPromotionsToPromotionDTOs(List<Promotion> promotions, PromotionConvertCriteriaDTO convertCriteria) {
		
		List<PromotionDTO> promotionDTOs = new ArrayList<PromotionDTO>();
		
		for (Promotion promotion : promotions) {
			promotionDTOs.add(convertPromotionToPromotionDTO(promotion,convertCriteria));
		}
		
		return promotionDTOs;

	}
	
	public PromotionDTO convertPromotionToPromotionDTO(Promotion promotion, PromotionConvertCriteriaDTO convertCriteria) {
		
		PromotionDTO promotionDTO = new PromotionDTO();
		
		promotionDTO.setPromotionId(promotion.getPromotionId());

	
		promotionDTO.setName(promotion.getName());

	
		promotionDTO.setDiscountRate(promotion.getDiscountRate());

	
		promotionDTO.setStartDate(promotion.getStartDate());

	
		promotionDTO.setEndDate(promotion.getEndDate());

	

		
		return promotionDTO;
	}

	public ResultDTO updatePromotion(PromotionDTO promotionDTO, RequestDTO requestDTO) {
		
		Promotion promotion = promotionDao.getById(promotionDTO.getPromotionId());

		promotion.setPromotionId(ControllerUtils.setValue(promotion.getPromotionId(), promotionDTO.getPromotionId()));

		promotion.setName(ControllerUtils.setValue(promotion.getName(), promotionDTO.getName()));

		promotion.setDiscountRate(ControllerUtils.setValue(promotion.getDiscountRate(), promotionDTO.getDiscountRate()));

		promotion.setStartDate(ControllerUtils.setValue(promotion.getStartDate(), promotionDTO.getStartDate()));

		promotion.setEndDate(ControllerUtils.setValue(promotion.getEndDate(), promotionDTO.getEndDate()));



        promotion = promotionDao.save(promotion);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PromotionDTO getPromotionDTOById(Integer promotionId) {
	
		Promotion promotion = promotionDao.getById(promotionId);
			
		
		PromotionConvertCriteriaDTO convertCriteria = new PromotionConvertCriteriaDTO();
		return(this.convertPromotionToPromotionDTO(promotion,convertCriteria));
	}







}
