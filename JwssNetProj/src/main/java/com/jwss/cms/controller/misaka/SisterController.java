package com.jwss.cms.controller.misaka;

import com.jwss.cms.entity.misaka.Sister;
import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.misaka.SisterService;
import com.jwss.cms.util.MyEncrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SisterController {
    @Resource
    SisterService sisterService;
    @Resource
    MyEncrypt myEncrypt;

    //分页查询
    @GetMapping("/sisters")
    public Result sisterList(@RequestParam int page, @RequestParam String key) {
        //校验传输密码是否正确
        if (myEncrypt.encrypt(String.valueOf(page)).equals(key)) {
            return new Result(1, sisterService.select(page,50));
        } else {
            return new Result(0, "error");
        }
    }

    //搜索
    @GetMapping("/search/sisters")
    public Result sisterListByKey(@RequestParam String searchKey, @RequestParam String key) {
        if (myEncrypt.encrypt(String.valueOf(1)).equals(key)) {
            List<Sister> sisterList = sisterService.sisterByKey(searchKey);
            if (sisterList == null || !sisterList.isEmpty()) {
                return new Result(1, sisterList);
            } else {
                return new Result(0, "empty");
            }
        } else {
            return new Result(0, "empty");
        }
    }

    //统计总条数
    @GetMapping("/sisters/count")
    public Result sisterCount() {
        return new Result(1, sisterService.countSister());
    }
}
