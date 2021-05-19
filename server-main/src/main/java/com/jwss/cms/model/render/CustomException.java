package com.jwss.cms.model.render;

/**
 * @author jwss
 */
public class CustomException extends RuntimeException {
    /**
	 * 防止出现警告
	 */
	private static final long serialVersionUID = 1L;
	private int code;
    private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
