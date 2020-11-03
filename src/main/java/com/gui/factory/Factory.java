package com.gui.factory;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Factory
{
    private Queue<Object> params;
    private Class creationClass;

    private Factory(Class creationClass)
    {
        params = new LinkedList<>();
        this.creationClass = creationClass;
    }

    private Factory(Class creationClass, Object ... args)
    {
        params = new LinkedList<>(Arrays.asList(args));
        this.creationClass = creationClass;
    }

    public Object addParam(Object o)
    {

        return null;
    }
}
