package se.rsv.arende.arendeinformationspring.exception;

import java.io.IOException;

/*
 * Egen exception dock fungerar den inte just nu. har inte varit en prio.
 */
public class FelMyndighetException extends Exception{

    public FelMyndighetException(Exception felaktigtVal){
        super(felaktigtVal);
    }

}
