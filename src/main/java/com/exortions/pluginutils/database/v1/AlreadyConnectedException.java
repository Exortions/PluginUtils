package com.exortions.pluginutils.database.v1;

public class AlreadyConnectedException extends Exception {

    public AlreadyConnectedException(String message) {
        super(message);
    }

    public AlreadyConnectedException(String message, Throwable cause) {
        super(message, cause);
    }

}
