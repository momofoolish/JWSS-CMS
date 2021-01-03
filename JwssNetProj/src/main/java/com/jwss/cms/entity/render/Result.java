package com.jwss.cms.entity.render;

import lombok.Data;

import java.util.Date;

@Data
public class Result {
    public Result(int code, Object content) {
        this.code = code;
        this.content = content;
        this.date = new Date();
    }

    private int code;
    private Object content;
    private Date date;
}
