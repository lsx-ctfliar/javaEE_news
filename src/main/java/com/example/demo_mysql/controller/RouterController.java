package com.example.demo_mysql.controller;


import com.example.demo_mysql.pojo.classinfo;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.pojo.userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class RouterController {


//    使用测试成功的数据库的代码，直接使用

    @Autowired
    JdbcTemplate MyjdbcTemplate;



//    跳转到首页登陆界面，，，。。。，，，，只设置一个路径的话直接，，，用双引号扩一个路径就行了
//    可以设置多个路径跳转，，，，直接在括号里面加上别的路径，中间使用逗号隔开,外面使用大括号 扩上
    @RequestMapping({"/login","/"})
    public String login(){
        return "login";
}



    @RequestMapping("/main_page")
    public String goMainPage(userinfo userinfo, Model model){

//        查查数据库的数据
        String sql = "select * from userinfo";
        List<Map<String,Object>> list_maps =  MyjdbcTemplate.queryForList(sql);

//        控制台输出查到的数据
        System.out.println(list_maps.toString());


        for(int i=0;i<list_maps.size();i++) {
//        连接数据库进行查数据，，，比对登录是否正确在这里进行判断
//            map<键，对象>,,不知道返回值是什么东西，直接点进函数里面看看，一般都是看不懂的
//            然后就是直接，，，在对象后面  .一下，看看它提供的方法，推测这个东西
            String username = list_maps.get(i).get("username").toString();
            String userpassword = list_maps.get(i).get("userpassword").toString();

            System.out.println("查到的数据："+username+"----"+userpassword);

            if (username.equals(userinfo.getUsername()) && userpassword.equals(userinfo.getUserpassword())) {
//        登陆成功，，跳转到主页面
                //在跳转到主页面之前，，读取课程数据表的数据，，参数是用户名
                String sql2 = "select * from classinfo where username = \'"+username+"\'";
                System.out.println("the sql2 :"+sql2);
                List<Map<String,Object>> list_maps2 =  MyjdbcTemplate.queryForList(sql2);

                System.out.println("查到的数据"+list_maps2.toString());


                List<classinfo> list = new LinkedList<classinfo>();
                //没查到一个课表的记录就新建一个 课对象实体类用来传数据，，，每一个对象用list保存
                for(int n=0;n<list_maps2.size();n++) {
                    classinfo classinfo = new classinfo();
                    classinfo.setUsername(list_maps2.get(n).get("username").toString());
                    classinfo.setCourseName(list_maps2.get(n).get("courseName").toString());
                    classinfo.setCourseDescription(list_maps2.get(n).get("courseDescription").toString());
                    classinfo.setTeachTime(list_maps2.get(n).get("teachTime").toString());
//                    人数是 int类型
                    classinfo.setOptionalPersonNum(Integer.parseInt(list_maps2.get(n).get("optionalPersonNum").toString()));
                    classinfo.setClassroom(list_maps2.get(n).get("classroom").toString());
                    list.add(classinfo);
                }



                String key = "key";

                for(int j=0;j<list_maps2.size();j++)
                {
                    model.addAttribute(key+String.valueOf(j),list.get(j));

//                    model.addAttribute("re",list.get(j));
                }

                return "view/main_page/main_page1";
//                String result = list_maps2.toString();

                }
        }

//        登陆失败，重新跳转回登陆界面
        return "login";

    }



//测试跳转是否正常访问到模板引擎下面的页面代码
    @RequestMapping("/test")
    public String test(){
        return "view/test_page/test01";
    }

//    跳转到登陆界面，因为a标签使用不是很顺利的原因直接使用这个controller跳转
    @RequestMapping("/register")
    public String register() {

        return "register";
    }




//    新闻登录接口测试  因为都是post请求要设置一个页面表单提交过去

    @RequestMapping("/updatepage")
    public String updatepage()
    {
        return "update";
    }


//    路由控制跳转到  页面提交用户id的测试页面，，通过id直接访问所有的用户数据
    @RequestMapping("/selectOne")
    public String selectAll(){
        return "selectOneUser";
    }


    @RequestMapping("/backLogin")
    public String backLogin()
    {
        return "/newsBackLogin";
    }

}
