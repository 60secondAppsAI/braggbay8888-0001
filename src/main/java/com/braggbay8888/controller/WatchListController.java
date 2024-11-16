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

import com.braggbay8888.domain.WatchList;
import com.braggbay8888.dto.WatchListDTO;
import com.braggbay8888.dto.WatchListSearchDTO;
import com.braggbay8888.dto.WatchListPageDTO;
import com.braggbay8888.service.WatchListService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/watchList")
@RestController
public class WatchListController {

	private final static Logger logger = LoggerFactory.getLogger(WatchListController.class);

	@Autowired
	WatchListService watchListService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WatchList> getAll() {

		List<WatchList> watchLists = watchListService.findAll();
		
		return watchLists;	
	}

	@GetMapping(value = "/{watchListId}")
	@ResponseBody
	public WatchListDTO getWatchList(@PathVariable Integer watchListId) {
		
		return (watchListService.getWatchListDTOById(watchListId));
	}

 	@RequestMapping(value = "/addWatchList", method = RequestMethod.POST)
	public ResponseEntity<?> addWatchList(@RequestBody WatchListDTO watchListDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchListService.addWatchList(watchListDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/watchLists")
	public ResponseEntity<WatchListPageDTO> getWatchLists(WatchListSearchDTO watchListSearchDTO) {
 
		return watchListService.getWatchLists(watchListSearchDTO);
	}	

	@RequestMapping(value = "/updateWatchList", method = RequestMethod.POST)
	public ResponseEntity<?> updateWatchList(@RequestBody WatchListDTO watchListDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchListService.updateWatchList(watchListDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
