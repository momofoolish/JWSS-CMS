package com.jwss.cms.model.render;

import java.util.Date;

/**
 * @author jwss
 */
public class Result {
    public Result(){}

    public Result(int code, Object content) {
        this.code = code;
        this.content = content;
        this.date = new Date();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int code;
    private Object content;
    private Date date;
}
