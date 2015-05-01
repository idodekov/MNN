package com.nbu.cscb822.exception;

import java.io.Serializable;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class NeuralNetworkException extends Exception implements Serializable {
    private static final long serialVersionUID = 7491940368923237485L;

    public NeuralNetworkException() {
        super();
    }

    public NeuralNetworkException(String arg0, Throwable arg1, boolean arg2,
            boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public NeuralNetworkException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public NeuralNetworkException(String arg0) {
        super(arg0);
    }

    public NeuralNetworkException(Throwable arg0) {
        super(arg0);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    
}
