package com.braggbay8888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay8888.domain.WatchList;
import com.braggbay8888.dto.WatchListDTO;
import com.braggbay8888.dto.WatchListSearchDTO;
import com.braggbay8888.dto.WatchListPageDTO;
import com.braggbay8888.dto.WatchListConvertCriteriaDTO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WatchListService extends GenericService<WatchList, Integer> {

	List<WatchList> findAll();

	ResultDTO addWatchList(WatchListDTO watchListDTO, RequestDTO requestDTO);

	ResultDTO updateWatchList(WatchListDTO watchListDTO, RequestDTO requestDTO);

    Page<WatchList> getAllWatchLists(Pageable pageable);

    Page<WatchList> getAllWatchLists(Specification<WatchList> spec, Pageable pageable);

	ResponseEntity<WatchListPageDTO> getWatchLists(WatchListSearchDTO watchListSearchDTO);
	
	List<WatchListDTO> convertWatchListsToWatchListDTOs(List<WatchList> watchLists, WatchListConvertCriteriaDTO convertCriteria);

	WatchListDTO getWatchListDTOById(Integer watchListId);







}





