package org.example.vm.object;

import java.util.Objects;

public class SPLStringObject extends SPLObject{
    private final String val;

    public String getVal() {
        return val;
    }

    public SPLStringObject(String val) {
        this.val = val;
    }

    @Override
    public SPLObject add(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return new SPLStringObject(val + s.getVal());
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLStringObject(val + l.getVal());
        } else if (rhs instanceof SPLFloatObject f) {
            return new SPLStringObject(val + f.getVal());
        }
        return null;
    }

    @Override
    public SPLObject mul(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return new SPLStringObject(val.repeat((int) l.getVal()));
        }
        return null;
    }

    @Override
    public SPLObject eq(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return val.equals(s.getVal()) ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ne(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return !(val.equals(s.getVal())) ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }


    @Override
    public SPLObject lt(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return val.compareTo(s.getVal()) < 0 ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject gt(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return val.compareTo(s.getVal()) > 0 ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject le(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return val.compareTo(s.getVal()) <= 0 ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ge(SPLObject rhs) {
        if (rhs instanceof SPLStringObject s) {
            return val.compareTo(s.getVal()) >= 0 ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SPLStringObject that)) return false;
        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }

    @Override
    public String toString() {
        return val;
    }
}
