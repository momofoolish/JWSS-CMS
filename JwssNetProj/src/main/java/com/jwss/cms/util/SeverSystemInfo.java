package com.jwss.cms.util;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jwss
 * 系统信息
 */
@Component
public class SeverSystemInfo {
    private final Logger logger = LoggerFactory.getLogger(SeverSystemInfo.class);

    /**
     * 初始化系统使用情况
     *
     * @return 系统信息MAP
     */
    public Map<String, Object> watch() {
        Map<String, Object> hashMap = new HashMap<>();
        SystemInfo systemInfo = new SystemInfo();
        try {
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            String bootTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
            String osName = System.getProperty("os.name");// 操作系统
            float totalMemorySize = (float) ((float)osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024);// 总的物理内存
            float usedMemory = (float) ((float)(osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024);// 已使用的物理内存

            File[] roots = File.listRoots();
            float usableSpace = 0;
            float totalSpace = 0;
            for (File file : roots) {
                usableSpace = (float)file.getUsableSpace() / 1024 / 1024 / 1024;//可用空间
                totalSpace = (float)file.getTotalSpace() / 1024 / 1024 / 1024;//总空间
            }

            hashMap.put("OS", osName);//操作系统
            hashMap.put("bootTime", bootTime);//程序启动时间
            hashMap.put("cpuCount", Runtime.getRuntime().availableProcessors() + "");//cpu核数
            hashMap.put("jHome", System.getProperty("java.home"));//java根目录
            hashMap.put("jVersion", System.getProperty("java.version"));//java版本
            hashMap.put("uHome", System.getProperty("user.home"));//用户根目录
            hashMap.put("totalMemory", totalMemorySize);//总的物理内存
            hashMap.put("usedMemory", usedMemory);//已使用的物理内存
            hashMap.put("usedCpu", getCpuInfo(systemInfo));//CPU使用率
            hashMap.put("usableSpace", usableSpace);//可用空间
            hashMap.put("totalSpace", totalSpace);//总空间

            logger.info(hashMap.toString());
            return hashMap;
        } catch (Exception e) {
            logger.info("获取系统信息异常，原因是：" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取 CPU 使用率
     *
     * @param systemInfo SystemInfo
     */
    private float getCpuInfo(SystemInfo systemInfo) {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + ioWait + irq + softIrq;
        return (float) (user * 1.0 / totalCpu);
    }
}
