//package com.mtl.crawler.scheduler.message.handler.impl;
//
//import com.aliyun.openservices.ons.api.Action;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mtl.crawler.scheduler.message.handler.BaseMsgHandler;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 用于处理第三方发来的爬取任务，比如爬取抖音、微博等信息
// * @date 2019-12-5 20:28:21
// * @author Zhangfeng
// */
//@Component
//public class CrawlTaskResultHandler extends BaseMsgHandler {
//
//    private final static Logger logger = LoggerFactory.getLogger(CrawlTaskResultHandler.class);
//
//    /**
//     * 目前这个Handler所要处理的消息类型
//     */
//    private final static String tag = "";
//
//    /**
//     * 每个handler自己需要实现具体的消息处理逻辑
//     *
//     * @param msg
//     * @return
//     */
//    @Override
//    public Action dealMsg(String msg) {
//        if(StringUtils.isEmpty(msg)){
//            return null;
//        }
//        CrawlTaskMsg msgBean = null;
//        try {
//            msgBean = new ObjectMapper().readValue(msg, CrawlTaskMsg.class);
//        }catch (Exception e) {
//            logger.error("CrawlTaskHandler parse Msg error", e);
//        }
//        if(msgBean == null){
//            return Action.CommitMessage;
//        }
//        CrawlTypeEnum crawlType = msgBean.getCrawlType();
//        if(crawlType == null){
//            logger.warn("CrawlTaskHandler task type is empty");
//            return Action.CommitMessage;
//        }
//        try {
//            MsgManager.getInstance().addMsg(crawlType.getName(),msgBean);
//            return Action.CommitMessage;
//        } catch (InterruptedException e) {
//            logger.error("CrawlTaskHandler add msg error",e);
//            return Action.ReconsumeLater;
//        }
//    }
//
//    /**
//     * 每个handler需要重写此方法实现，从而将自己注册进入handler上下文
//     *
//     * @return
//     */
//    @Override
//    protected String getTag() {
//        return tag;
//    }
//}
