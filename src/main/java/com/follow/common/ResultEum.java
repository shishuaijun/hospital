package com.follow.common;

public enum ResultEum {

    SUCCESS(0,"success"),
    ERROR(1,"ERROR");

    private Integer code ;

    private String content ;

    ResultEum(Integer code, String content) {
        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
