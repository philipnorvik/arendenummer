package se.rsv.arende.arendeinformationspring.exception;

import java.io.IOException;

public class FelMyndighetException extends Exception{

    public FelMyndighetException(Exception felaktigtVal){
        super(felaktigtVal);
    }

}
