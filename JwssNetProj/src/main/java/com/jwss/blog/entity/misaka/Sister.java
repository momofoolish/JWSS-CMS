package com.jwss.blog.entity.misaka;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Sister {
    public Sister(Integer mid, String name, String face, String sign, Integer level, Integer fans) {
        this.mid = mid;
        this.name = name;
        this.face = face;
        this.sign = sign;
        this.level = level;
        this.fans = fans;
    }
    Integer mid;
    String name;
    String face;
    String sign;
    Integer level;
    Integer fans;
}
