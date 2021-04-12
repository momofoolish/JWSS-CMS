package com.jwss.cms.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class HostConfig {
    /**
     * 封面真实路径
     */
    @Value("${jwss.file.save-path.cover}")
    private String coverSavePath;

    /**
     * 封面映射路径
     */
    @Value("${jwss.file.browser-path.cover}")
    private String coverBrowserPath;

    /**
     * 内容图片真实路径
     */
    @Value("${jwss.file.save-path.content}")
    private String contentSavePath;

    /**
     * 内容图片真实路径
     */
    @Value("${jwss.file.browser-path.content}")
    private String contentBrowserPath;

    public String getCoverSavePath() {
        return coverSavePath;
    }

    public void setCoverSavePath(String coverSavePath) {
        this.coverSavePath = coverSavePath;
    }

    public String getCoverBrowserPath() {
        return coverBrowserPath;
    }

    public void setCoverBrowserPath(String coverBrowserPath) {
        this.coverBrowserPath = coverBrowserPath;
    }

    public String getContentSavePath() {
        return contentSavePath;
    }

    public void setContentSavePath(String contentSavePath) {
        this.contentSavePath = contentSavePath;
    }

    public String getContentBrowserPath() {
        return contentBrowserPath;
    }

    public void setContentBrowserPath(String contentBrowserPath) {
        this.contentBrowserPath = contentBrowserPath;
    }
}
