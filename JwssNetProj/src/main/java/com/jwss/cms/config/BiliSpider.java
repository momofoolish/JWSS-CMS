package com.jwss.cms.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwss.cms.entity.misaka.Sister;
import com.jwss.cms.service.misaka.SisterService;
import com.jwss.cms.util.JwssSpider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BiliSpider extends JwssSpider {
    @Resource
    private SisterService sisterService;

    /**
     * 逻辑处理是否有御坂网络的妹妹们,有则存进数据库
     *
     * @param page Page类
     */
    @Override
    public void getData(Page page) throws JsonProcessingException {
        String html = page.getHtml();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(html.substring(6, html.length() - 1));

        //        判断是否相应成功
        if (!jsonNode.findValue("code").toString().equals("0")) {
            Scheduler.misakaNumber++;
            return;
        }
//        判断是否存在数据
        if (Integer.parseInt(jsonNode.findValue("data").findValue("numResults").toString()) == 0) {
            Scheduler.misakaNumber++;
            return;
        }
//        解析json数据
        List<JsonNode> jsonNodeList = objectMapper.readValue(jsonNode.findValue("data").findValue("result").toString(),
                new TypeReference<List<JsonNode>>() {
                });
//        遍历列表并存储数据
        for (JsonNode jn : jsonNodeList) {
            Sister sister = new Sister(Integer.parseInt(jn.findValue("mid").toString()),
                    jn.findValue("uname").toString().replace("\"", ""),
                    jn.findValue("upic").toString().replace("\"", ""),
                    jn.findValue("usign").toString().replace("\"", ""),
                    Integer.parseInt(jn.findValue("level").toString()), Integer.parseInt(jn.findValue("fans").toString()));
//                System.out.println(sister.toString());
            String numberFormat = String.format("%05d", Scheduler.misakaNumber);
            String regexStr = "御坂" + numberFormat + "号";
//            System.out.println(numberFormat + "\t" + sister.getName() + "\t" + regexStr);
            if (sister.getName().equals(regexStr)) {
                System.out.println(sister.getName());
                sisterService.insert(sister);
            }
        }
        Scheduler.misakaNumber++;
    }

    @Override
    public void configSpider(SpiderConfig config) {
//            String url="https://api.bilibili.com/x/space/acc/info?mid="+mid+"&jsonp=jsonp";
//            %E5%BE%A1%E5%9D%82%E5%8F%B7
        String numberFormat = String.format("%05d", Scheduler.misakaNumber);
        String keyWord = "御坂" + numberFormat + "号";
        String url = "https://api.bilibili.com/x/web-interface/search/type?context=&keyword=" + keyWord +
                "&page=1&order=&category_id=&duration=&user_type=&order_sort=&tids_1=&tids_2" +
                "=&search_type=bili_user&changing=mid&__refresh__=true&__reload__=false&_extra=&highlight=" +
                "1&single_column=0&jsonp=jsonp&callback=__jp2";
        config.addUrl(url);
        config.addHeader("referer", "https://search.bilibili.com/upuser?keyword=%E5%BE%A1%E5%9D%82%E5%8F%B7");
    }
}
