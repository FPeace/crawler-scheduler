//package com.mtl.crawler.scheduler.message.handler.context;
//
//import com.mtl.crawler.scheduler.message.handler.BaseMsgHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//
//import java.security.InvalidParameterException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 消息处理器上下文，用于保存系统内用于处理消息的Handler实例(单例，一个tag对应一个实例)
// * @date 2019-8-27 23:10:26
// * @author ZhangFeng
// */
//public class MsgHandlerContext {
//
//    private static volatile MsgHandlerContext context;
//
//    private static final Logger logger = LoggerFactory.getLogger(MsgHandlerContext.class);
//
//    private Map<String, BaseMsgHandler> contextHashMap = new ConcurrentHashMap<>();
//
//    private MsgHandlerContext(){
//    }
//
//    public static MsgHandlerContext getInstance(){
//        if(context == null){
//            synchronized (MsgHandlerContext.class){
//                if(context == null){
//                    context = new MsgHandlerContext();
//                }
//            }
//        }
//        return context;
//    }
//
//    /**
//     * 将消息处理器注册进入上下文，一个handler对应一种tag。
//     * @param tag
//     * @param msgHandler
//     */
//    public void register(String tag,BaseMsgHandler msgHandler){
//        if(StringUtils.isEmpty(tag)){
//            throw new InvalidParameterException("find empty tag");
//        }
//        if(contextHashMap.containsKey(tag)){
//            throw new InvalidParameterException("find duplicated tag");
//        }
//        contextHashMap.put(tag,msgHandler);
//    }
//
//    /**
//     * 根据tag获取对应的handler
//     * @param tag
//     * @return
//     */
//    public BaseMsgHandler getHandler(String tag){
//        if(StringUtils.isEmpty(tag)){
//            logger.warn("tag is null!");
//            return null;
//        }
//        return contextHashMap.get(tag);
//    }
//}
