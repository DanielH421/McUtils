package dev.dan.mcutils.exceptions;

import java.lang.reflect.Type;

public class TypeNotAcceptedException extends Exception {
    public TypeNotAcceptedException(Class expected, Class result){
        super("Expected generic " + expected.getName() + " but received " + result.getName());
    }
    public TypeNotAcceptedException(Class expected, Type received){
        super("Expected generic " + expected.getName() + " but received " + received.getTypeName());
    }

}