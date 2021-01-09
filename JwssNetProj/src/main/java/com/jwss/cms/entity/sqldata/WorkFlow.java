package com.jwss.cms.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_workflow")
public class WorkFlow {
    int id;
    String name;
}
