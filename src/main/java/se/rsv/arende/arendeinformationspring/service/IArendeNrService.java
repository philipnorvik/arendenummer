package se.rsv.arende.arendeinformationspring.service;

import java.io.IOException;

import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;

/*
 * Interface för den abstrakta klassen ArendeNrService 
 */
interface IArendeNrService {
       public String valAvMyndighet(String myndighet) throws IOException, FelMyndighetException;
       void createArende(String inparameterMyndighet);
       void getArende();
}

