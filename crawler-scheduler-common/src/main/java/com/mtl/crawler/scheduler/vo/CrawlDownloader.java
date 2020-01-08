package com.mtl.crawler.scheduler.vo;

import lombok.Data;

@Data
public class CrawlDownloader {

    private String name;
    private Integer capacity;
    private Integer availableCapacity;
}
