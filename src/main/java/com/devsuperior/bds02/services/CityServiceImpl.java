package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;


    @Override
    @Transactional(readOnly = true)
    public List<CityDTO> findAll(Sort sort) {
        return cityRepository.findAll(sort).stream().map(CityDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CityDTO save(CityDTO cityDTO) {
        City cityToSave = new City(cityDTO.getId(), cityDTO.getName());
        City citySaved = cityRepository.save(cityToSave);
        cityDTO.setId(citySaved.getId());
        return cityDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            cityRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getLocalizedMessage());
        }

    }
}
