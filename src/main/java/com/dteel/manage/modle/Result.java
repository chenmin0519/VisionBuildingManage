package com.dteel.manage.modle;

public enum Result {
    success("成功", 0),

    failure("失败", 1),

    Error("错误", 9);

    private String label;
    private int code;

    private Result(String label, int code) {
        this.code = code;
        this.label = label;
    }

    public static String getLabel(int code) {
        for (Result t : values()) {
            if (t.getCode() == code) {
                return t.label;
            }
        }

        return null;
    }

    public String toString() {
        return String.valueOf(this.code);
    }

    public boolean compare(int code) {
        return this.code == code;
    }

    public boolean compare(String _code) {
        int code;
        try {
            code = Integer.parseInt(_code);
        } catch (NumberFormatException e) {
            return false;
        }

        return this.code == code;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
