package se.rsv.arende.arendeinformationspring.controller;

import java.io.IOException;

interface IArendeNrGenerering {

       public String skapaArendeNr(String myndighet) throws IOException;
}

