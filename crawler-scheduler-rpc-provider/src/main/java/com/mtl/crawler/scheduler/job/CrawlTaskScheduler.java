package com.mtl.crawler.scheduler.job;

import com.juqitech.request.GenericRequest;
import com.mtl.crawler.proxy.client.ProxyClient;
import com.mtl.crawler.proxy.info.ProxyInfo;
import com.mtl.crawler.proxy.param.ProxyQueryParam;
import com.mtl.crawler.scheduler.manager.DownloaderManager;
import com.mtl.crawler.scheduler.manager.TaskManager;
import com.mtl.crawler.scheduler.vo.CrawlDownloader;
import com.mtl.crawler.scheduler.vo.CrawlTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Date;
import java.util.Queue;


/**
 * 爬虫任务调度器
 * @date 2020-1-6 21:33:21
 * @author Zhangfeng
 */
@Component
@EnableScheduling
public class CrawlTaskScheduler {

    @Autowired
    private ProxyClient proxyClient;

    @Scheduled(fixedDelay = 1000 * 10)
    public void taskDispatch() {
        System.out.println("开始扫描任务");
        CrawlDownloader downloader = DownloaderManager.getInstance().next();
        if(downloader != null){
            CrawlTask task = TaskManager.getInstance().next();
            if(task != null){
                setAuth(task);
                setProxy(task);
                DownloaderManager.getInstance().subOneCapacity(downloader);
                TaskManager.getInstance().scheduled(task);
                System.out.println("发布了一个任务");
                taskDispatch();
            }else {
                return;
            }
        }else {
            System.out.println("无可用下载器");
            return;
        }
    }

    private void setAuth(CrawlTask task){

    }

    private void setProxy(CrawlTask task){
        ProxyQueryParam proxyQueryParam = new ProxyQueryParam();
        proxyQueryParam.setUrl(task.getUrl());
        GenericRequest request = new GenericRequest();
        request.setParam(proxyQueryParam);
        ProxyInfo proxyInfo = (ProxyInfo) proxyClient.getProxy(request).getData();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyInfo.getIp(),proxyInfo.getPort()));
        task.setProxy(proxy);
    }

    @Scheduled(fixedDelay = 1000 * 30)
    private void scanDeadTask(){
        Queue<CrawlTask> runningQueue = TaskManager.getInstance().getRunningQueue();
        if(!runningQueue.isEmpty()){
            for (CrawlTask task : runningQueue) {
                Date starTime = task.getStartTime();
                if(starTime != null){
                    Long usedTime = (System.currentTimeMillis() - starTime.getTime()) / (1000);
                    if(usedTime >= 30){
                        System.out.println("发现一个死亡任务");
                        TaskManager.getInstance().dead(task);
                    }
                }
            }
        }
    }
}
