package org.aming.web.qq.contorller;

import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.response.CommonResponse;
import org.aming.web.qq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daming
 * @version 2017/10/8.
 */
@RestController
@RequestMapping("/webqq")
public class MessageController {

    private MessageService messageService;

    @RequestMapping( path = "/recent/message", method = RequestMethod.POST)
    public CommonResponse getRecentMessages(@RequestBody User user){
        try{
            return CommonResponse.getSuccessCommonResponse(
                    messageService.getMessage(
                            null,
                            user,
                            new Page(0)
                    )
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }
}
