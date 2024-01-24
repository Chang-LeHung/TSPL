package org.example.vm.object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SPLReflectionCall extends SPLObject {

    private final Method method;
    private final Object o;

    public SPLReflectionCall(Method method) {
        this.method = method;
        this.o = null;
    }

    public SPLReflectionCall(Method method, Object o) {
        this.method = method;
        this.o = o;
    }

    @Override
    public SPLObject call(SPLObject... rhs) {
        try {
            return (SPLObject) method.invoke(this.o, (Object) rhs);
        } catch (IllegalAccessException | InvocationTargetException ignore) {
            throw new RuntimeException("Could not invoke method " + method.getName());
        }
    }
}
