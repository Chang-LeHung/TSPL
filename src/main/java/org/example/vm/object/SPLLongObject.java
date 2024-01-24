package org.example.vm.object;

public class SPLLongObject extends SPLObject {
    public static SPLLongObject[] pool;
    private long val;

    public long getVal() {
        return val;
    }

    static {
        pool = new SPLLongObject[301];
        for (int i = -5; i < 256; i++) {
            pool[i + 5] = new SPLLongObject(i);
        }
    }

    private SPLLongObject(long val) {
        this.val = val;
    }

    public static SPLLongObject create(long val) {
        if (val >= -5 && val < 256) {
            return pool[(int) (val + 5)];
        } else {
            return new SPLLongObject(val);
        }
    }

    @Override
    public SPLObject add(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return create(val + l.getVal());
        } else if (rhs instanceof SPLFloatObject f) {
            double res = val + f.getVal();
            return new SPLFloatObject(res);
        } else if (rhs instanceof SPLStringObject s) {
            return new SPLStringObject(val + s.getVal());
        }
        return null;
    }

    @Override
    public SPLObject sub(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return create(val - l.getVal());
        } else if (rhs instanceof SPLFloatObject f) {
            double res = val - f.getVal();
            return new SPLFloatObject(res);
        }
        return null;
    }

    @Override
    public SPLObject mul(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return create(val * l.getVal());
        } else if (rhs instanceof SPLFloatObject f) {
            double res = val * f.getVal();
            return new SPLFloatObject(res);
        }
        return null;
    }

    @Override
    public SPLObject div(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return create(val / l.val);
        } else if (rhs instanceof SPLFloatObject f) {
            double res = val / f.getVal();
            return new SPLFloatObject(res);
        }
        return null;
    }

    @Override
    public SPLObject trueDiv(SPLObject rsh) {
        return null;
    }

    @Override
    public SPLObject pow(SPLObject rhs) {
        if (rhs instanceof SPLLongObject l) {
            return create((long) Math.pow(val, l.getVal()));
        } else if (rhs instanceof SPLFloatObject f) {
            double res = Math.pow(val, f.getVal());
            return new SPLFloatObject(res);
        }
        return null;
    }

    @Override
    public SPLObject lShift(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val << l.val);
        }
        return null;
    }

    @Override
    public SPLObject rShift(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val >> l.val);
        }
        return null;
    }

    @Override
    public SPLObject URShift(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val >>> l.val);
        }
        return null;
    }

    @Override
    public SPLObject and(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val & l.val);
        }
        return null;
    }

    @Override
    public SPLObject or(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val | l.val);
        }
        return null;
    }

    @Override
    public SPLObject xor(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val ^ l.val);
        }
        return null;
    }

    @Override
    public SPLObject mod(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            return create(val % l.val);
        }
        return null;
    }

    @Override
    public SPLObject not() {
        return null;
    }

    @Override
    public SPLObject neg() {
        return create(-val);
    }

    @Override
    public SPLObject invert() {
        return create(~getVal());
    }

    @Override
    public SPLObject eq(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val == l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return f.getVal() == val ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ne(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val != l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return f.getVal() != val ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject lt(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val < l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return val < f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject gt(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val > l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return val > f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject le(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val <= l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return val <= f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public SPLObject ge(SPLObject rsh) {
        if (rsh instanceof SPLLongObject l) {
            if (val >= l.getVal()) {
                return SPLBoolObject.getTrue();
            } else {
                return SPLBoolObject.getFalse();
            }
        } else if (rsh instanceof SPLFloatObject f) {
            return val >= f.getVal() ? SPLBoolObject.getTrue() : SPLBoolObject.getFalse();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SPLLongObject that)) return false;
        return val == that.val;
    }

    @Override
    public SPLObject str() {
        return new SPLStringObject(String.valueOf(val));
    }

    @Override
    public int hashCode() {
        return Long.hashCode(val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
