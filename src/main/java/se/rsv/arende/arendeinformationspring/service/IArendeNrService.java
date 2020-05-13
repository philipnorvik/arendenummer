package se.rsv.arende.arendeinformationspring.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.model.Arende;

/*
 * Interface för den abstrakta klassen ArendeNrService 
 */
interface IArendeNrService {
       String valAvMyndighet(String myndighet) throws IOException, FelMyndighetException;
       Arende createArende(String inparameterMyndighet,Date datum,String orgnummer) throws IOException, FelMyndighetException;
       List<Arende> getArenden();
       Arende getArende(String arendenummer);
}

