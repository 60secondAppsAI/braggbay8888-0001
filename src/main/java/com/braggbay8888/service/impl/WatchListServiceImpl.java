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
import com.braggbay8888.dao.WatchListDAO;
import com.braggbay8888.domain.WatchList;
import com.braggbay8888.dto.WatchListDTO;
import com.braggbay8888.dto.WatchListSearchDTO;
import com.braggbay8888.dto.WatchListPageDTO;
import com.braggbay8888.dto.WatchListConvertCriteriaDTO;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import com.braggbay8888.service.WatchListService;
import com.braggbay8888.util.ControllerUtils;





@Service
public class WatchListServiceImpl extends GenericServiceImpl<WatchList, Integer> implements WatchListService {

    private final static Logger logger = LoggerFactory.getLogger(WatchListServiceImpl.class);

	@Autowired
	WatchListDAO watchListDao;

	


	@Override
	public GenericDAO<WatchList, Integer> getDAO() {
		return (GenericDAO<WatchList, Integer>) watchListDao;
	}
	
	public List<WatchList> findAll () {
		List<WatchList> watchLists = watchListDao.findAll();
		
		return watchLists;	
		
	}

	public ResultDTO addWatchList(WatchListDTO watchListDTO, RequestDTO requestDTO) {

		WatchList watchList = new WatchList();

		watchList.setWatchListId(watchListDTO.getWatchListId());


		watchList.setAddedDate(watchListDTO.getAddedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		watchList = watchListDao.save(watchList);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WatchList> getAllWatchLists(Pageable pageable) {
		return watchListDao.findAll(pageable);
	}

	public Page<WatchList> getAllWatchLists(Specification<WatchList> spec, Pageable pageable) {
		return watchListDao.findAll(spec, pageable);
	}

	public ResponseEntity<WatchListPageDTO> getWatchLists(WatchListSearchDTO watchListSearchDTO) {
	
			Integer watchListId = watchListSearchDTO.getWatchListId(); 
   			String sortBy = watchListSearchDTO.getSortBy();
			String sortOrder = watchListSearchDTO.getSortOrder();
			String searchQuery = watchListSearchDTO.getSearchQuery();
			Integer page = watchListSearchDTO.getPage();
			Integer size = watchListSearchDTO.getSize();

	        Specification<WatchList> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, watchListId, "watchListId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<WatchList> watchLists = this.getAllWatchLists(spec, pageable);
		
		//System.out.println(String.valueOf(watchLists.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(watchLists.getTotalPages()));
		
		List<WatchList> watchListsList = watchLists.getContent();
		
		WatchListConvertCriteriaDTO convertCriteria = new WatchListConvertCriteriaDTO();
		List<WatchListDTO> watchListDTOs = this.convertWatchListsToWatchListDTOs(watchListsList,convertCriteria);
		
		WatchListPageDTO watchListPageDTO = new WatchListPageDTO();
		watchListPageDTO.setWatchLists(watchListDTOs);
		watchListPageDTO.setTotalElements(watchLists.getTotalElements());
		return ResponseEntity.ok(watchListPageDTO);
	}

	public List<WatchListDTO> convertWatchListsToWatchListDTOs(List<WatchList> watchLists, WatchListConvertCriteriaDTO convertCriteria) {
		
		List<WatchListDTO> watchListDTOs = new ArrayList<WatchListDTO>();
		
		for (WatchList watchList : watchLists) {
			watchListDTOs.add(convertWatchListToWatchListDTO(watchList,convertCriteria));
		}
		
		return watchListDTOs;

	}
	
	public WatchListDTO convertWatchListToWatchListDTO(WatchList watchList, WatchListConvertCriteriaDTO convertCriteria) {
		
		WatchListDTO watchListDTO = new WatchListDTO();
		
		watchListDTO.setWatchListId(watchList.getWatchListId());

	
		watchListDTO.setAddedDate(watchList.getAddedDate());

	

		
		return watchListDTO;
	}

	public ResultDTO updateWatchList(WatchListDTO watchListDTO, RequestDTO requestDTO) {
		
		WatchList watchList = watchListDao.getById(watchListDTO.getWatchListId());

		watchList.setWatchListId(ControllerUtils.setValue(watchList.getWatchListId(), watchListDTO.getWatchListId()));

		watchList.setAddedDate(ControllerUtils.setValue(watchList.getAddedDate(), watchListDTO.getAddedDate()));



        watchList = watchListDao.save(watchList);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WatchListDTO getWatchListDTOById(Integer watchListId) {
	
		WatchList watchList = watchListDao.getById(watchListId);
			
		
		WatchListConvertCriteriaDTO convertCriteria = new WatchListConvertCriteriaDTO();
		return(this.convertWatchListToWatchListDTO(watchList,convertCriteria));
	}







}
