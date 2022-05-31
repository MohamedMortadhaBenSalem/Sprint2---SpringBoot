package com.mortadha.vetements.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mortadha.vetements.entities.Marque;
import com.mortadha.vetements.repos.MarRepository;


@Service
public class MarServiceImpl implements MarService {

    @Autowired
    private MarRepository marRepository;

    @Override
    public List <Marque> findAll() {
        return marRepository.findAll();
    }

	@Override
	public Marque saveMarque(Marque c) {
		return marRepository.save(c);
	}

	@Override
	public Marque updateMarque(Marque c) {
		return marRepository.save(c);
	}

	@Override
	public void deleteMarque(Marque c) {
		marRepository.delete(c);
	}

	@Override
	public void deleteMarqueById(Long idMar) {
		marRepository.deleteById(idMar);
	}

	@Override
	public Marque getMarque(Long idMar) {
		return marRepository.findById(idMar).get();
	}

   
}