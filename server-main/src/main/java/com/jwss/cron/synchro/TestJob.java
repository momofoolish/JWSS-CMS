package com.jwss.cron.synchro;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Async
public class TestJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(TestJob.class);
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("定时器执行：" + sdf.format(new Date()));
    }
}
