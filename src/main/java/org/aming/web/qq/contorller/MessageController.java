package org.aming.web.qq.contorller;

import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.TimeInterval;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.logger.Logger;
import org.aming.web.qq.logger.LoggerManager;
import org.aming.web.qq.response.CommonResponse;
import org.aming.web.qq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author daming
 * @version 2017/10/8.
 */
@RestController
@RequestMapping("/webqq")
public class MessageController {

    private static final Logger logger = LoggerManager.getLogger(MessageController.class);

    private MessageService messageService;

    @RequestMapping( path = "/recent/message/{friend}", method = RequestMethod.GET)
    public CommonResponse getRecentMessages(@PathVariable String friend){
        try{
            return CommonResponse.getSuccessCommonResponse(
                    messageService.getMessage(
                            null,
                            new User().setUsername(friend),
                            new Page(0)
                    )
            );
        }catch (Exception ex){
            logger.error("异常信息 : {} - 异常原因 : {} ", ex.getMessage(), ex.getCause());
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @RequestMapping( path = "/newest/message/{friend}", method = RequestMethod.GET)
    public CommonResponse getNewestMessage(@PathVariable String friend,@RequestParam("beginDateTime") Timestamp beginDateTime, @RequestParam("endDateTime") Timestamp endDateTime){
        try{
            return  CommonResponse.getSuccessCommonResponse(
                    messageService.getMessage(
                            null,
                            new User().setUsername(friend),
                            new TimeInterval(
                                    beginDateTime,
                                    endDateTime
                            )
                    )
            );
        }catch (Exception ex){
            logger.error("异常信息 : {} - 异常原因 : {} ", ex.getMessage(), ex.getCause());
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }
}
