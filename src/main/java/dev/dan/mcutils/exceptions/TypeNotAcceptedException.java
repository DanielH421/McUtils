package dev.dan.mcutils.exceptions;

import java.lang.reflect.Type;

public class TypeNotAcceptedException extends Exception {
    public TypeNotAcceptedException(Class expected, Class result){
        super("Expected generic " + expected.getName() + " but recieved " + result.getName());
    }
    public TypeNotAcceptedException(Class expected, Type recieved){
        super("Expected generic " + expected.getName() + " but recieved " + recieved.getTypeName());
    }

}