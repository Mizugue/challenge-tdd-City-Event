package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll(Sort sort);
}
