package com.code.fuqinqin.spring.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * <p>计算耗时测试</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/9 15:21
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("test");
//        stopWatch.setKeepTaskList(false);

        stopWatch.start("task-1");
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();

        stopWatch.start("task-2");
        TimeUnit.MILLISECONDS.sleep(300);
        stopWatch.stop();

        log.info("total = {}, pretty log = {}", stopWatch.getTotalTimeMillis(), stopWatch.prettyPrint());
        log.info("taskCount = {}", stopWatch.getTaskCount());
    }

}
