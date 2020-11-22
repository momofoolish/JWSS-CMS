package com.jwss.blog.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_menu")
public class Menu {
    int id;
    String colName;
    String colType;
    String colUrl;
    int colState;
}
