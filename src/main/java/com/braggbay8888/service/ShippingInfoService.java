package com.braggbay8888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay8888.domain.ShippingInfo;
import com.braggbay8888.dto.ShippingInfoDTO;
import com.braggbay8888.dto.ShippingInfoSearchDTO;
import com.braggbay8888.dto.ShippingInfoPageDTO;
import com.braggbay8888.dto.ShippingInfoConvertCriteriaDTO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingInfoService extends GenericService<ShippingInfo, Integer> {

	List<ShippingInfo> findAll();

	ResultDTO addShippingInfo(ShippingInfoDTO shippingInfoDTO, RequestDTO requestDTO);

	ResultDTO updateShippingInfo(ShippingInfoDTO shippingInfoDTO, RequestDTO requestDTO);

    Page<ShippingInfo> getAllShippingInfos(Pageable pageable);

    Page<ShippingInfo> getAllShippingInfos(Specification<ShippingInfo> spec, Pageable pageable);

	ResponseEntity<ShippingInfoPageDTO> getShippingInfos(ShippingInfoSearchDTO shippingInfoSearchDTO);
	
	List<ShippingInfoDTO> convertShippingInfosToShippingInfoDTOs(List<ShippingInfo> shippingInfos, ShippingInfoConvertCriteriaDTO convertCriteria);

	ShippingInfoDTO getShippingInfoDTOById(Integer shippingInfoId);







}





