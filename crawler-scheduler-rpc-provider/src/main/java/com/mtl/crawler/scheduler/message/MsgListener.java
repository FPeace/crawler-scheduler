//package com.mtl.crawler.scheduler.message;
//
//import com.aliyun.openservices.ons.api.Action;
//import com.aliyun.openservices.ons.api.Message;
//import com.mtl.crawler.scheduler.message.handler.BaseMsgHandler;
//import com.mtl.crawler.scheduler.message.handler.context.MsgHandlerContext;
//import com.mtl.mq.util.base.BaseMessageListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MsgListener extends BaseMessageListener {
//
//    private final static Logger logger = LoggerFactory.getLogger(MsgListener.class);
//
//    /**
//     * 消息消费逻辑,包括顺序消息消费
//     *
//     * @param message
//     */
//    @Override
//    public Action doConsume(Message message) {
//        Action action;
//        String tag = decodeMessageTag(message);
//        String msg = getStringBody(message);
//
//        logger.info("CrawlerShowMQListener received msg's tag {}",tag);
//        BaseMsgHandler handler = MsgHandlerContext.getInstance().getHandler(tag);
//        if(handler == null){
//            logger.info("tag {} has no handler",tag);
//            return Action.ReconsumeLater;
//        }
//        try {
//            action = handler.dealMsg(msg);
//        }catch (Exception e){
//            logger.error("handler {} deal msg error,tag: {},msg: {}",handler,tag,msg);
//            action = Action.ReconsumeLater;
//        }
//        if(action == null){
//            action = Action.ReconsumeLater;
//        }
//        return action;
//    }
//
//    /**
//     * 不进行重复性消费验证
//     * @return
//     */
//    @Override
//    protected boolean isExecuteDuplicateCheck() {
//        return false;
//    }
//}
