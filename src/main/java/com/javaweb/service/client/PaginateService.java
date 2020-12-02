package com.javaweb.service.client;

import org.springframework.stereotype.Service;

import com.javaweb.dto.PaginateDto;

@Service
public interface PaginateService {
	public PaginateDto GetInfoPaginates(int totalData, int limit, int currentPage);
	
}
