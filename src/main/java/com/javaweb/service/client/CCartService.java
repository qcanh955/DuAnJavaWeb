package com.javaweb.service.client;

import java.util.HashMap;

import com.javaweb.dto.CartDto;

public interface CCartService {

	double TotalPrice(HashMap<Long, CartDto> cart);

	int TotalQuanty(HashMap<Long, CartDto> cart);

	HashMap<Long, CartDto> DeleteCart(long id, HashMap<Long, CartDto> cart);

	HashMap<Long, CartDto> EditCart(long id, int quanty, HashMap<Long, CartDto> cart);

	HashMap<Long, CartDto> AddCart(long id, HashMap<Long, CartDto> cart);

}
