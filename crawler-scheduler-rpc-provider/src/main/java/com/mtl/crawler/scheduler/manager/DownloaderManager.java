package com.mtl.crawler.scheduler.manager;

import com.mtl.crawler.scheduler.vo.CrawlDownloader;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 下载器管理器
 *
 * @author Zhangfeng
 * @date 2020-1-6 20:52:05
 */
public class DownloaderManager{

    private Queue<CrawlDownloader> crawlDownloaderQueue = new LinkedBlockingQueue<>();

    private static volatile DownloaderManager downloaderManager;

    private DownloaderManager(){}

    public static DownloaderManager getInstance() {
        if(downloaderManager == null){
            synchronized (DownloaderManager.class){
                if (downloaderManager == null) {
                    downloaderManager = new DownloaderManager();

                    CrawlDownloader downloader = new CrawlDownloader();
                    downloader.setCapacity(200);
                    downloader.setName("A");
                    downloaderManager.add(downloader);

                    CrawlDownloader downloaderB = new CrawlDownloader();
                    downloaderB.setCapacity(200);
                    downloaderB.setName("B");
                    downloaderManager.add(downloaderB);
                }
            }
        }
        return downloaderManager;
    }

    public CrawlDownloader next() {
        if(crawlDownloaderQueue.isEmpty()){
            return null;
        }
        CrawlDownloader crawlDownloader = null;
        for (CrawlDownloader downloader : crawlDownloaderQueue) {
            Integer capacity = downloader.getCapacity();
            if(capacity == null || capacity == 0){
                continue;
            }
            if(crawlDownloader == null ||(crawlDownloader != null && crawlDownloader.getCapacity() < capacity && capacity != 0)){
                crawlDownloader = downloader;
            }
        }
        return crawlDownloader;
    }

    public void add(CrawlDownloader downloader) {
        if(downloader == null){
            return;
        }
        crawlDownloaderQueue.add(downloader);
    }

    public void remove(CrawlDownloader downloader) {
        if(downloader == null){
            return;
        }
        crawlDownloaderQueue.remove(downloader);
    }

    public void addOneCapacity(CrawlDownloader downloader){
        if(downloader == null){
            return;
        }
        downloader.setCapacity(downloader.getCapacity() + 1);
    }

    public void subOneCapacity(CrawlDownloader downloader){
        if(downloader == null){
            return;
        }
        Integer capacity = downloader.getCapacity();
        if(capacity == 0){
            return;
        }
        downloader.setCapacity(capacity - 1);
    }

}
