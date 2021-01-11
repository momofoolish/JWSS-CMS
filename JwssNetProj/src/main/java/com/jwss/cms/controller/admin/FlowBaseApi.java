package com.jwss.cms.controller.admin;

import com.jwss.cms.service.flowbase.FlowBaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/flowBase")
public class FlowBaseApi {
    @Resource
    FlowBaseService flowBaseService;

    //分页查询
    @GetMapping("/queryByPage")
    public Object articlePage(@RequestParam Map<String, Object> map) {
        String keyWork = map.get("title") == null ? "" : map.get("title").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return flowBaseService.selectByPage(p, l, keyWork);
    }
}
