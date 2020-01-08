package com.mtl.crawler.scheduler.manager;

import com.mtl.crawler.scheduler.enums.CrawlTaskLevel;
import com.mtl.crawler.scheduler.enums.CrawlTaskStatus;
import com.mtl.crawler.scheduler.enums.CrawlTaskType;
import com.mtl.crawler.scheduler.vo.CrawlTask;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 爬取任务管理器
 *
 * @author Zhangfeng
 * @date 2020-1-6 20:51:44
 */
public class TaskManager{

    private Queue<CrawlTask> highLevelTaskQueue = new LinkedBlockingQueue<>();
    private Queue<CrawlTask> generalLevelTaskQueue = new LinkedBlockingQueue<>();
    private Queue<CrawlTask> runningTaskQueue = new LinkedBlockingQueue<>();
    private Queue<CrawlTask> deadTaskQueue = new LinkedBlockingQueue<>();

    private Map<CrawlTaskType, Date> taskTimeRecord = new HashMap<>();

    private Map<CrawlTaskType,Long> taskInterval = new HashMap<>();

    private static volatile TaskManager taskManager;

    private TaskManager(){};

    public static TaskManager getInstance() {
        if(taskManager == null){
            synchronized (TaskManager.class){
                if (taskManager == null) {
                    taskManager = new TaskManager();
                }
            }
        }
        return taskManager;
    }

    public CrawlTask next() {
        CrawlTask task = null;
        if(!highLevelTaskQueue.isEmpty()){
            for (CrawlTask crawlTask : highLevelTaskQueue) {
                if(checkTaskInterval(crawlTask)){
                    task = crawlTask;
                    break;
                }
            }
        }
        if(task != null){
            return task;
        }

        if(!generalLevelTaskQueue.isEmpty()){
            for (CrawlTask crawlTask : generalLevelTaskQueue) {
                if(checkTaskInterval(crawlTask)){
                    task = crawlTask;
                    break;
                }
            }
        }
        return task;
    }

    public void add(CrawlTask task) {
        if(task != null){
            CrawlTaskLevel level =  task.getTaskLevel();
            task.setStatus(CrawlTaskStatus.IDLE);
            if(CrawlTaskLevel.HIGH_LEVEL == level){
                highLevelTaskQueue.add(task);
            }else {
                task.setTaskLevel(CrawlTaskLevel.GENERAL_LEVEL);
                generalLevelTaskQueue.add(task);
            }
        }
    }

    public void done(CrawlTask task) {
        if(task == null){
            return;
        }
        task.setStatus(CrawlTaskStatus.DONE);
        runningTaskQueue.remove(task);
    }

    public void dead(CrawlTask task){
        if(task == null){
            return;
        }
        task.setStatus(CrawlTaskStatus.DEAD);
        deadTaskQueue.add(task);
        runningTaskQueue.remove(task);
    }

    public void scheduled(CrawlTask task){
        if(task == null){
            return;
        }
        remove(task);
        task.setStatus(CrawlTaskStatus.RUNNING);
        runningTaskQueue.add(task);
        task.setStartTime(new Date());
    }

    public void remove(CrawlTask task){
        if(task == null){
            return;
        }
        if(CrawlTaskStatus.IDLE.equals(task.getStatus())){
            CrawlTaskLevel taskLevel = task.getTaskLevel();
            if(CrawlTaskLevel.HIGH_LEVEL.equals(taskLevel)){
                highLevelTaskQueue.remove(task);
            }else{
                generalLevelTaskQueue.remove(task);
            }
        }
    }

    /**
     *
     * @param task
     * @return
     */
    private boolean checkTaskInterval(CrawlTask task){
        if(task == null){
            return false;
        }
        CrawlTaskType type = task.getTaskType();
        Date lastTime = taskTimeRecord.get(type);
        if(lastTime == null){
            return true;
        }
        Long interval = taskInterval.get(type);
        if(interval == null){
            return true;
        }

        Date now = new Date();
        /**
         * 相差秒
         */
        Long diff = (now.getTime() - lastTime.getTime())/(1000 * 60);

        if(diff >= interval){
            return true;
        }else {
            return false;
        }
    }

    public Queue getRunningQueue(){
        return runningTaskQueue;
    }

}
