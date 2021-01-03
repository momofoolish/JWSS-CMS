package com.jwss.cms.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_feedback")
public class Feedback {
    private int id;
    private String userId;
    private String content;
    private Date createDate;
}
