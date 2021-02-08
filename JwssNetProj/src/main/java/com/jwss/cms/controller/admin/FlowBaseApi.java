package com.jwss.cms.controller.admin;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.flowbase.FlowBaseService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/flowBase")
public class FlowBaseApi {
    @Resource
    FlowBaseService flowBaseService;

    //分页查询
    @GetMapping("/queryByPage")
    public Object flowBasePage(@RequestParam Map<String, Object> map) {
        String keyWork = map.get("title") == null ? "" : map.get("title").toString();
        String examineType = map.get("examineType") == null ? "" : map.get("examineType").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        //默认显示待审批流程
        if ("".equals(examineType)) {
            return flowBaseService.selectByAuditState(p, l, keyWork, 0);
        } else {
            int t = Integer.parseInt(examineType);
            if (t > 0) {
                return flowBaseService.selectByAuditState(p, l, keyWork, t);
            } else {
                return flowBaseService.selectByPage(p, l, keyWork);
            }
        }
    }

    //更新流程
    @PostMapping("/update")
    public Object updateFlowBase(@RequestParam Map<String, Object> map) {
        return new Result(1, flowBaseService.update(map));
    }
}
