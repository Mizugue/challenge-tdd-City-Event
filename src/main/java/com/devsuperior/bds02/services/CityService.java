package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll(Sort sort);
    CityDTO save(CityDTO cityDTO);
}
