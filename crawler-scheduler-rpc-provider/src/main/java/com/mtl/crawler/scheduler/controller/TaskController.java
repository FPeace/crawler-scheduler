package com.mtl.crawler.scheduler.controller;

import com.mtl.crawler.scheduler.manager.TaskManager;
import com.mtl.crawler.scheduler.vo.CrawlTask;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020-1-8 17:30:25
 * @author Zhangfeng
 */
@RestController(value = "/")
public class TaskController {
    @PostMapping("task")
    public Boolean addNewTask(@RequestBody CrawlTask crawlTask){
        TaskManager.getInstance().add(crawlTask);
        return true;
    }
}
