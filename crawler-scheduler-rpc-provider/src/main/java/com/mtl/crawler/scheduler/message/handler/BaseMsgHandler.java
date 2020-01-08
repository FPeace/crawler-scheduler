//package com.mtl.crawler.scheduler.message.handler;
//
//import com.aliyun.openservices.ons.api.Action;
//import com.mtl.crawler.scheduler.message.handler.context.MsgHandlerContext;
//
//import javax.annotation.PostConstruct;
//
///**
// * 消息处理函数基类,当需要扩展消息处理handler时需要继承此类。
// * @date 2019-8-28 00:29:45
// * @author ZhangFeng
// */
//public abstract class BaseMsgHandler {
//
//    /**
//     *
//     */
//    @PostConstruct
//    final public void register() throws Exception {
//        MsgHandlerContext.getInstance().register(getTag(),this);
//    }
//
//    /**
//     * 每个handler自己需要实现具体的消息处理逻辑
//     * @param msg
//     * @return
//     */
//    public abstract Action dealMsg(String msg);
//
//    /**
//     *每个handler需要重写此方法实现，从而将自己注册进入handler上下文
//     * @return
//     */
//    protected abstract String getTag();
//
//}
