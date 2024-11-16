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

import com.braggbay8888.domain.ShoppingCart;
import com.braggbay8888.dto.ShoppingCartDTO;
import com.braggbay8888.dto.ShoppingCartSearchDTO;
import com.braggbay8888.dto.ShoppingCartPageDTO;
import com.braggbay8888.service.ShoppingCartService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController {

	private final static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	ShoppingCartService shoppingCartService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ShoppingCart> getAll() {

		List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
		
		return shoppingCarts;	
	}

	@GetMapping(value = "/{shoppingCartId}")
	@ResponseBody
	public ShoppingCartDTO getShoppingCart(@PathVariable Integer shoppingCartId) {
		
		return (shoppingCartService.getShoppingCartDTOById(shoppingCartId));
	}

 	@RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
	public ResponseEntity<?> addShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shoppingCartService.addShoppingCart(shoppingCartDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/shoppingCarts")
	public ResponseEntity<ShoppingCartPageDTO> getShoppingCarts(ShoppingCartSearchDTO shoppingCartSearchDTO) {
 
		return shoppingCartService.getShoppingCarts(shoppingCartSearchDTO);
	}	

	@RequestMapping(value = "/updateShoppingCart", method = RequestMethod.POST)
	public ResponseEntity<?> updateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shoppingCartService.updateShoppingCart(shoppingCartDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
