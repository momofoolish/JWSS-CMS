package com.jwss.cms.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.jwss.cms.constant.HostConfig;
import com.jwss.cms.util.SystemUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file/upload")
public class FileController {
    @Resource
    private HostConfig hostConfig;

    @PostMapping("/articleMedia")
    public JSONObject uploadArticleMedia(@RequestParam("articleMedia")MultipartFile mf){
        JSONObject result = new JSONObject();
        if(null != mf && !mf.isEmpty()){
            String saveFlag = SystemUtils.saveContentImage(mf, hostConfig);
            if(!"empty".equals(saveFlag)){
                //保存文件成功
                Map<String, String> hashMap = new HashMap<>();
                List<Map<String,String>> mapList = new ArrayList<>();
                hashMap.put("url", saveFlag);
                hashMap.put("alt", mf.getOriginalFilename());
                hashMap.put("href", "#");
                mapList.add(hashMap);
                result.put("errno", "0");
                result.put("data", mapList);
            }else {
                result.put("errno", "-1");
            }
        }
        return result;
    }
}
