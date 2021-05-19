package com.jwss.cms.controller.admin.menu;

import com.jwss.cms.model.menu.TbMenu;
import com.jwss.cms.model.render.Result;
import com.jwss.cms.service.menu.MenuService;
import com.jwss.cms.util.SqlUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/menu/portal")
public class PortalMenuController {
    @Resource
    private MenuService menuService;

    //新增
    @PostMapping("/add")
    public Result addMenu(@RequestBody TbMenu menu) {
        Result result = new Result();
        int i = menuService.insert(menu);
        if (i >= 1) {
            result.setContent("增加成功");
            result.setCode(1);
        } else {
            result.setContent("失败");
            result.setCode(0);
        }
        return result;
    }

    //分页查询
    @GetMapping("/queryByPage")
    public Object queryByPage(@RequestParam Map<String, Object> map) {
        String keyWord = map.get("col_name") == null ? "" : map.get("col_name").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return SqlUtils.pageResultGenerator(menuService.selectByTable(p, l, -1, keyWord));
    }

    //删除
    @PostMapping("delete")
    public Result deleteMenu(@RequestParam("mid") String mid) {
        Result result = new Result();
        int i = menuService.delete(mid);
        if (i >= 1) {
            result.setContent("删除成功");
            result.setCode(1);
        } else {
            result.setContent("删除失败");
            result.setCode(0);
        }
        return result;
    }

    //更新
    @PostMapping("/update")
    public Result updateMenu(@RequestBody TbMenu menu) {
        Result result = new Result();
        try {
            result.setContent(menuService.update(menu));
            result.setCode(1);
        } catch (Exception e) {
            result.setContent(e.getMessage());
            result.setCode(0);
        }
        return result;
    }
}
