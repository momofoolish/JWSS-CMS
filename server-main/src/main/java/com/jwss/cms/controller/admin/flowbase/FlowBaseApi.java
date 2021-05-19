package com.jwss.cms.controller.admin.flowbase;

import com.jwss.cms.model.render.Result;
import com.jwss.cms.service.flow.impl.FlowBaseServiceImpl;
import com.jwss.cms.util.SqlUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/flowBase")
public class FlowBaseApi {
    @Resource
    FlowBaseServiceImpl flowBaseService;

    //分页查询
    @GetMapping("/queryByPage")
    public Object flowBasePage(@RequestParam Map<String, Object> map) {
        String keyWork = map.get("title") == null ? "" : map.get("title").toString();
        String examineType = map.get("examineType") == null ? "" : map.get("examineType").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        List<Map<String, String>> mapList;
        //默认显示待审批流程
        if ("".equals(examineType)) {
            mapList = flowBaseService.selectByAuditState(p, l, keyWork, 0);
        } else {
            int t = Integer.parseInt(examineType);
            if (t > 0) {
                mapList = flowBaseService.selectByAuditState(p, l, keyWork, t);
            } else {
                mapList = flowBaseService.selectByTable(p, l, keyWork);
            }
        }
        return SqlUtils.pageResultGenerator(mapList);
    }

    //更新流程
    @PostMapping("/update")
    public Object updateFlowBase(@RequestParam Map<String, Object> map) {
        return new Result(1, flowBaseService.update(map));
    }
}
