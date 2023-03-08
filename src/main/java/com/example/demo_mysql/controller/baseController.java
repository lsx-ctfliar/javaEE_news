package com.example.demo_mysql.controller;

//import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo_mysql.util.JsonResult;
public class baseController {
//    操作成功的状态码，定义在基本控制器，使用别的控制器的时候，继承一下这里
//    相同的功能不用重复，直接定义在这里方便
    public static final int ok = 200;
    public static final int False = 500;



/** 定义异常处理的抛出方法*/


//
//@ExceptionHandler(ServiceException.class)
//public JsonResult<Void> handleException(Throwable e) {
//    JsonResult<Void> result = new JsonResult<Void>(e);
//
//    if (e instanceof UsernameDuplicateException) {
//        result.setState(4000);
//    } else if (e instanceof UserNotFoundException) {
//        result.setState(4001);
//    } else if (e instanceof PasswordNotMatchException) {
//        result.setState(4002);
//    } else if (e instanceof NewsRepeatException) {
//        result.setState(4003);
//    } else if (e instanceof NewsException) {
//        result.setState(4004);
//    } else if (e instanceof IncertException) {
//        result.setState(5000);
//    }
//    return result;
//}


//
//    /**
//     * 从HttpSession对象中获取uid
//     * @param session HttpSession对象
//     * @return 当前登录的用户的id
//     */
//    protected final Integer getUidFromSession(HttpSession session) {
//        return Integer.valueOf(session.getAttribute("uid").toString());
//    }
//
//
//    /**
//     * 从HttpSession对象中获取用户名
//     * @param session HttpSession对象
//     * @return 当前登录的用户名
//     */
//    protected final String getUsernameFromSession(HttpSession session) {
//        return session.getAttribute("username").toString();
//    }

}
