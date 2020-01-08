package com.mtl.crawler.scheduler.vo;

import com.mtl.crawler.auth.vo.Auth;
import com.mtl.crawler.scheduler.enums.CrawlTaskLevel;
import com.mtl.crawler.scheduler.enums.CrawlTaskStatus;
import com.mtl.crawler.scheduler.enums.CrawlTaskType;
import lombok.Getter;
import lombok.Setter;

import java.net.Proxy;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CrawlTask {


    @Getter
    private UUID uuid = UUID.randomUUID();

    /**
     * 爬取任务类型
     */
    private CrawlTaskType taskType;
    /**
     * url
     */
    private String url;

    /**
     * 任务状态
     */
    private CrawlTaskStatus status;

    /**
     * 任务级别,不传默认为一般级别
     */
    private CrawlTaskLevel taskLevel;

    private Auth auth;

    private Proxy proxy;

    private Date startTime;

    @Override
    public boolean equals(Object object){
        if(object instanceof CrawlTask){
            return  ((CrawlTask) object).getUuid().equals(this.uuid);
        }else {
            return false;
        }
    }
}
