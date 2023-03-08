package com.example.demo_mysql.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.配置到拦截器要拦截哪些请求
 * 2.把这些配置放在容器中
 *
 * 实现HandlerInterceptor接口
 */

public class LoginInterceptor implements HandlerInterceptor {



    /**
     * 目标方法执行之前
     * 登录检查写在这里，如果没有登录，就不执行目标方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 拦截请求输出
        String requestURI = request.getRequestURI();
        System.out.println("拦截了请求{}"+requestURI);
        //      登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("userInfo");
        System.out.println("session拿到用户的数据"+loginUser);
        if(loginUser !=null){
//          放行
            System.out.println("放行了");
            return true;
        }
//      拦截   就是未登录,自动跳转到登录页面，然后写拦截住的逻辑
        else{
            // 提示错误信息
            request.setAttribute("msg","请先登录！");
            System.out.println("请先登录");
            // 请求转发  就是重定向
//            request.getRequestDispatcher("/login").forward(request,response);
            // 未登录拦截资源
            return false;
        }

    }




    /**
     * 目标方法执行完成以后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }








    /**
     * 页面渲染以后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


}
