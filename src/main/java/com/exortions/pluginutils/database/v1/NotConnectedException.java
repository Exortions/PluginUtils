package com.exortions.pluginutils.database.v1;

public class NotConnectedException extends Exception {

    public NotConnectedException(String msg) {
        super(msg);
    }

    public NotConnectedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
