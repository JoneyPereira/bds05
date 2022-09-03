package com.devsuperior.movieflix.services.exceptions;

public class UnauthorizeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnauthorizeException(String msg){
        super(msg);
    }
}
