package com.gui.factory;

public interface Instanceiable<E>
{
    Class[] getParamsType();

    E constructor(Object[] param);

    default int getParamLength()
    { return getParamsType().length; }

    default E instance(Object[] params)
    {
        if(params.length != getParamLength())
            throw new IllegalArgumentException("Parameter Error");

        for(int i = 0; i < params.length; i++)
            if(params[i].getClass() != getParamsType()[i])
                throw new IllegalArgumentException("Wrong Parameter Type");
        return constructor(params);
    }
}
