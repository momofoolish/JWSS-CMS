package com.jwss.cms.config;

import com.jwss.cms.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(Scheduled.class);
    public static int misakaNumber = 1;
    private final boolean isStart = false;
    @Resource
    CountService countService;
    @Resource
    BiliSpider biliSpider;

    //每天4:00 将redis访问量数据存进mysql
    @Scheduled(cron = "0 0 04 ? * *")
    public void countReadsAndLikesTasks() {
        //存进mysql
        countService.insertCountData("reads");
        countService.insertCountData("likes");
    }

    //每天5:00 将redis访问量数据存进mysql
    @Scheduled(cron = "0 0 05 ? * *")
    public void countReadsAndLikesByUserTasks() {
        if (!isStart) {
            return;
        }
        countService.insertCountDataByUser();
    }

    @Scheduled(cron = "*/25 * * * * ?")
    public void runSpider() throws InterruptedException {
        if (!isStart) {
            return;
        }
        if (misakaNumber > 20002) {
            return;
        }
        logger.info(new Date() + "网站抓取中...，编号为:" + misakaNumber);
        Thread thread = new Thread(biliSpider);
        Thread.sleep(1000 * (long) (Math.random() * 10 + 3));
        thread.start();
    }
}
