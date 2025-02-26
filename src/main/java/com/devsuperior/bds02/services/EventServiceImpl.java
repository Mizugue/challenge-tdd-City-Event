package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;



    @Override
    public EventDTO update(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        event.setDate(eventDTO.getDate());
        event.setName(eventDTO.getName());
        event.setUrl(eventDTO.getUrl());

        List<City> cities = cityRepository.findAll();
        for (City city : cities){
            if (Objects.equals(city.getId(), eventDTO.getCityId())){
                event.setCity(city);
            }
        }

        eventRepository.save(event);
        return new EventDTO(event);



    }
}
