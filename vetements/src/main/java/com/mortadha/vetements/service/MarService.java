package com.mortadha.vetements.service;

import java.util.List;

import com.mortadha.vetements.entities.Marque;

public interface MarService {

    List <Marque> findAll();
    Marque saveMarque(Marque c);
    Marque updateMarque(Marque c);
    void deleteMarque(Marque c);
     void deleteMarqueById(Long id);
     Marque getMarque (Long idMar);

}