package org.example.vm.interpreter;

import org.example.vm.object.SPLNoneObject;
import org.example.vm.object.SPLObject;
import org.example.vm.object.SPLReflectionCall;
import org.example.vm.object.SPLStringObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Builtin {

    public static Map<SPLObject, SPLObject> builtin = new HashMap<>();

    static {
        try {
            Method method = Builtin.class.getMethod("print", SPLObject[].class);
            builtin.put(new SPLStringObject("print"),
                new SPLReflectionCall(method));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static SPLObject print(SPLObject ... args) {
        for (SPLObject arg : args) {
            System.out.println(arg.str());
        }
        return SPLNoneObject.NONE;
    }
}
