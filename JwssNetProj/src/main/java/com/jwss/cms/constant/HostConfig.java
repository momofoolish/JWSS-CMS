package com.jwss.cms.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application-dev.properties")
public class HostConfig {
    @Value("${jwss.file.cover}")
    private String filePath;     //图片真实路径
    @Value("${jwss.file.mapping}")
    private String coverMapping;
}
