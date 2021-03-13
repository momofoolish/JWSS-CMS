package com.jwss.cms.model.render;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    /**
	 * 防止出现警告
	 */
	private static final long serialVersionUID = 1L;
	private int code;
    private String message;

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
