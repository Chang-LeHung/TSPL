package org.example.vm.object;

import java.util.Objects;

public class SPLFloatObject extends SPLObject{
    private double val;

    public SPLFloatObject(double val) {
        this.val = val;
    }
    public double getVal() {
        return val;
    }

    @Override
    public SPLObject add(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject(val + f.getVal());
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject(val + l.getVal());
        } else if (rhs instanceof SPLStringObject s) {
            return new SPLStringObject(val + s.getVal());
        }
        return null;
    }

    @Override
    public SPLObject sub(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject(val - f.getVal());
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject(val - l.getVal());
        }
        return null;
    }

    @Override
    public SPLObject mul(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject(val * f.getVal());
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject(val * l.getVal());
        }
        return null;
    }

    @Override
    public SPLObject div(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject(val / f.getVal());
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject(val / l.getVal());
        }
        return null;
    }

    @Override
    public SPLObject mod(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject((int)(val % f.getVal()));
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject((int)(val - l.getVal()));
        }
        return null;
    }

    @Override
    public SPLObject pow(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return new SPLFloatObject(Math.pow(val, f.getVal()));
        } else if (rhs instanceof SPLLongObject l) {
            return new SPLFloatObject(Math.pow(val, l.getVal()));
        }
        return null;
    }

    @Override
    public SPLObject neg() {
        return new SPLFloatObject(-val);
    }

    @Override
    public SPLObject eq(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val == f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val == l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ne(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val != f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val != l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject lt(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val < f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val < l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject gt(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val > f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val > l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject le(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val <= f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val <= l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ge(SPLObject rhs) {
        if (rhs instanceof SPLFloatObject f) {
            return val >= f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        } else if (rhs instanceof SPLLongObject l) {
            return val >= l.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SPLFloatObject that)) return false;
        return Double.compare(that.val, val) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
