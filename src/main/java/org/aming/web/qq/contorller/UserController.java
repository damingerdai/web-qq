package org.aming.web.qq.contorller;

import org.aming.web.qq.domain.User;
import org.aming.web.qq.logger.Logger;
import org.aming.web.qq.logger.LoggerManager;
import org.aming.web.qq.response.CommonResponse;
import org.aming.web.qq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author daming
 * @version 2017/10/2.
 */
@RestController
@RequestMapping("/webqq")
public class UserController {

    private static final Logger logger = LoggerManager.getLogger(UserController.class);

    private UserService userService;

    @RequestMapping(path = "/myFriends", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public CommonResponse getFriendsForCurrentUser(){
        try{
            return CommonResponse.getSuccessCommonResponse(userService.getFriendsForCurrentUser());
        } catch (Exception ex){
            logger.error("异常信息：{}", ex.getLocalizedMessage());
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @RequestMapping(path = "/findMoreUser", method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,})
    public CommonResponse findMoreUser(@RequestBody  String condition){
        try{
            return CommonResponse.getSuccessCommonResponse(userService.findMoreUser(condition));
        }catch (Exception ex){
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/addUser",method = RequestMethod.POST)
    public CommonResponse addUser(@RequestBody User user){
        try{
            return CommonResponse.getSuccessCommonResponse(userService.addUser(user));
        }catch (Exception ex){
            ex.printStackTrace();
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @RequestMapping(path = "/addRelationship",method = RequestMethod.POST)
    public CommonResponse addRelationship(@RequestBody User friend){
        try{
            userService.addRelationship(friend);
            return CommonResponse.getSuccessCommonResponse(true);
        }catch (Exception ex){
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @RequestMapping(path = "/myInfo", method = RequestMethod.POST)
    public CommonResponse getUserInfo(){
        try{
            return CommonResponse.getSuccessCommonResponse(userService.getUserInfo(null));
        } catch (Exception ex){
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
