package ru.bellintegrator.practice.exception;

import javax.xml.ws.http.HTTPException;

public class NoSuchId extends HTTPException {
    /**
     * Constructor for the HTTPException
     *
     * @param statusCode <code>int</code> for the HTTP status code
     **/
    public NoSuchId(int statusCode) {
        super(statusCode);
    }
}
