package org.example.vm.object;

import java.util.Objects;

public class SPLBoolObject extends SPLObject{

    private boolean value;

    public SPLBoolObject(boolean value) {
        this.value = value;
    }

    public static class SelfHolder {
        public static SPLBoolObject TRUE = new SPLBoolObject(true);
        public static SPLBoolObject FALSE = new SPLBoolObject(false);
        public static SPLBoolObject True = TRUE;
        public static SPLBoolObject False = FALSE;
    }

    public static SPLBoolObject getTrue() {
        return SelfHolder.TRUE;
    }
    public static SPLBoolObject getFalse() {
        return SelfHolder.FALSE;
    }

    public boolean isTrue() {
        return value;
    }


    @Override
    public SPLObject not() {
        if (this == getTrue()) {
            return getFalse();
        }
        return getTrue();
    }

    @Override
    public SPLObject neg() {
        if (this == getTrue()) {
            return getFalse();
        }
        return getTrue();
    }

    @Override
    public SPLObject eq(SPLObject rhs) {
        return this == rhs ? getTrue() : getFalse();
    }

    @Override
    public SPLObject ne(SPLObject rhs) {
        return this == rhs ? getFalse() : getTrue();
    }

    @Override
    public SPLObject conditionalAnd(SPLObject rhs) {
        if (this == getFalse() || rhs == getFalse()) {
            return getFalse();
        }
        return getTrue();
    }

    @Override
    public SPLObject conditionalOr(SPLObject rhs) {
        if (this == getTrue() || rhs == getTrue()) {
            return getTrue();
        }
        return getFalse();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SPLBoolObject that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }


}
