package com.jwss.cms.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_flow_base")
public class FlowBase {
    int id;
    String title;
    String content;
    String opinion;
    int auditState;
    String currentOperator;
    int flowId;
    Date createDate;
}
