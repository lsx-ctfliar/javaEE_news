package com.example.demo_mysql.controller;

import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.pojo.userinfo;
import com.example.demo_mysql.service.adminService;
import com.example.demo_mysql.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@Api("后台接口")
@RequestMapping(value = "/newsBack",method = {RequestMethod.GET,RequestMethod.POST})
public class NewsBackController extends baseController{

    //    初始化设置管理员id是-1表示没有登陆，，，之后登陆后保存，可以全局使用
    int id=-1;
    //    数据库操作对象，这里要记得装填对象，实例化
    @Autowired

    JdbcTemplate jdbcTemplate;
    @Autowired
    private adminService adminService;

    @RequestMapping("/adminLogin")
    public JsonResult adminLogin(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpServletRequest request,
                                 Model model
    ) {
        //        查看数据库里面所有的用户信息
//        String sql = "select * from admin";
//        System.out.println("admin login sql:"+sql);
//        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
//
//        System.out.println(list_maps.toString());
        /**
         * @Description 代码变更
         * @Date 2023/2/17 21:03
         * @Created by 余
         */

        String Msg = "登录失败,请检查账号或密码！";
        if (username == "") {
            Msg = "账号不能为空！";
        } else if (password == "") {
            Msg = "密码不能为空！";
        } else {
            List<userinfo> list = null;
            list=adminService.selectUser(username, password);
            System.out.println(list + " 47" + "password" + password);

//        for (int i = 0; i < list_maps.size(); i++) {
//            String name = list_maps.get(i).get("name").toString();
//            String pw = list_maps.get(i).get("password").toString();
//
//            System.out.println("userData" + name + "--" + pw);
//
////           用户校验
//            if (name.equals(username) && pw.equals(password)) {
//
//                id = Integer.parseInt(list_maps.get(i).get("id").toString());
//                model.addAttribute("userId", id);
//
//                String[] data= new String[3];
//
////根据管理员的表，管理员id，，，管理员名字，用户密码，直接返回三个数据，，考虑到后面表添加数据的话，
//                data[0] = list_maps.get(i).get("id").toString();
//                data[1] = list_maps.get(i).get("name").toString();
//                data[2] = list_maps.get(i).get("password").toString();
//
//                JsonResult jr = new JsonResult();
//                jr.setCode(ok);
//                jr.setMsg("管理员登录成功!");
//                jr.setObj(list);
//                return jr;
//            }
//
//        }

            if (list.size() != 0) {


                //保存管理员登陆状态到session里面
                //存储的东西检查非空即可
                model.addAttribute("userInfo",list);
                request.getSession().setAttribute("userInfo",list);
                JsonResult jr = new JsonResult();
                jr.setCode(ok);
                jr.setMsg("管理员登录成功!");
                jr.setObj(list);
                return jr;
            }
        }

//        如果登录没有成功的话，代码没有在那里返回，就会运行下面的代码，，，直接返回登录失败的对象
        JsonResult jr_error = new JsonResult();
        jr_error.setCode(500);
        jr_error.setMsg(Msg);
        jr_error.setObj(null);
        return jr_error;
    }





    /**
     *
     * 接口描述：
     * 返回已审核通过的文章集合。
     * 接口类型 get
     * Query参数 size、current
     * 返回数据格式
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[
     * records：[] //返回数据 （文章信息集合）
     * ]
     * total://文章总数
     * }
     * */

    /**
     * @Description 修改传入参数为 state（文章类型）
     *
     * @Date 2023/2/17 21:13
     * @Created by 余
     */

    @RequestMapping("/backGetAllNews")
    public JsonResult backGetAllNewsByState(@RequestParam("state") String state){
        String Msg="获取新闻失败！";
//        String sql = "select * from news where newsType='0'";
//        List<Map<String,Object>> list_map = jdbcTemplate.queryForList(sql);
//        List<news> allNews = new ArrayList<>();
////        循环实例每一个新闻对象，，然后添加到队列里面
//        for(int i=0;i<list_map.size();i++) {
//            news oneNews = new news();
//            oneNews.setNewsId(Integer.parseInt(list_map.get(i).get("newsId").toString()));
//            oneNews.setUserId(Integer.parseInt(list_map.get(i).get("userId").toString()));
//            oneNews.setNewsTitle(list_map.get(i).get("newsTitle").toString());
//            oneNews.setNewsArticle(list_map.get(i).get("newsArticle").toString());
//            oneNews.setNewsImage(list_map.get(i).get("newsImage").toString());
//            oneNews.setNewsYesCount(Integer.parseInt(list_map.get(i).get("newsYesCount").toString()));
//            oneNews.setNewsStarCount(Integer.parseInt(list_map.get(i).get("newsStarCount").toString()));
//            oneNews.setNewsType(list_map.get(i).get("newsYesCount").toString());
//            allNews.add(oneNews);
//        }
        List<news> allNews=null;
        if(state=="")
        {
            Msg="state不能为空！";
        } else {


            /**
             * @Description 更换查询方法，采用MyBatis代替Jdbc
             * @Date 2023/2/17 22:21
             * @Created by 余
             */
            allNews = adminService.selectAllNewsByState(Integer.parseInt(state));
            if(allNews.size()!=0) {

//        构建符合的数据格式，，，，使用map
                Map<String, Object> data = new HashMap<>(2);
                data.put("records", allNews);
                data.put("total",allNews.size());

                JsonResult jr = new JsonResult();
                jr.setCode(ok);
                jr.setMsg("获取所有发布的新闻");
                jr.setObj(data);
                return jr;
            }
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("records", allNews);
        data.put("total", allNews.size());
        JsonResult jr = new JsonResult();
        jr.setCode(False);
        jr.setMsg(Msg);
        jr.setObj(data);
        return jr;
    }



    /**
     * 接口描述：
     * 功能是管理员用来审核文章时，，，看的未审核的新闻
     * 接收文章状态字符 返回通过的文章。
     * 接口类型 post
     * Query参数 id
     * 返回数据格式
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[
     * records：[]//返回数据 （文章信息）
     * ]
     * }
     *
     * */
    @PostMapping("/backGetOneNews")
    public JsonResult backGetAllNews(@RequestParam("id") String id){
//        String sql = "select * from news where newsType='"+state+"'";
//        List<Map<String,Object>> list_map = jdbcTemplate.queryForList(sql);
//        List<news> allNews = new ArrayList<>();
////        循环实例每一个新闻对象，，然后添加到队列里面
//        for(int i=0;i<list_map.size();i++) {
//            news oneNews = new news();
//            oneNews.setNewsId(Integer.parseInt(list_map.get(i).get("newsId").toString()));
//            oneNews.setUserId(Integer.parseInt(list_map.get(i).get("userId").toString()));
//            oneNews.setNewsTitle(list_map.get(i).get("newsTitle").toString());
//            oneNews.setNewsArticle(list_map.get(i).get("newsArticle").toString());
//            oneNews.setNewsImage(list_map.get(i).get("newsImage").toString());
//            oneNews.setNewsYesCount(Integer.parseInt(list_map.get(i).get("newsYesCount").toString()));
//            oneNews.setNewsStarCount(Integer.parseInt(list_map.get(i).get("newsStarCount").toString()));
//            oneNews.setNewsType(list_map.get(i).get("newsYesCount").toString());
//            allNews.add(oneNews);
//        }

        /**
         * @Description 修改方法，采用MyBatis
         * @Date 2023/2/17 23:03
         * @Created by 余
         */
        List<news> allNews=null;
        String Msg="新闻获取失败！";
        int code = False;
        if(id=="")
        {
            Msg="id为空！";
        }else {
//        构建符合的数据格式，，，，使用map
            allNews = adminService.selectOneNewsById(Integer.parseInt(id));
            if (allNews.size()==0)
            {
                Msg="请输入正确的id！";
            } else {
                Msg ="获取新闻成功！";
                code = ok;
            }
        }
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(allNews);
        return jr;

//


    }



    /**
     * 接口描述：
     * 接收文章表文章指定id状态，修改指定id文章状态 返回接口提示信息
     * 接口类型 post
     * Query参数 id、state（文章状态）
     * 返回数据格式：
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[] //返回数据 （管理员所有信息集合）
     * }*/

    /**
     * @Description 更新与完善backUpdateNews接口方法 与 引入MyBatis
     * @Date 2023/2/17 23:54
     * @Created by 余
     */
    @PostMapping("/backUpdateNews")
    public JsonResult backUpdateNews(@RequestParam("id") String newsId,
                                     @RequestParam("state") String state){

//        String sql = "update news set newsType='"+state+"' where newsId="+newsId;
//        System.out.println("backUpdateNews sql:"+sql);
//        jdbcTemplate.execute(sql);
//        String sql2 = "select * from admin wehere id="+id;
//        System.out.println("backUpdateNews sql:"+sql2);
//        Map<String,Object> map_user = jdbcTemplate.queryForMap(sql2);
//        String[] userData= new String[3];
//
////根据管理员的表，管理员id，，，管理员名字，用户密码，直接返回三个数据，，考虑到后面表添加数据的话，
//        userData[0] = map_user.get("id").toString();
//        userData[1] = map_user.get("name").toString();
//        userData[2] = map_user.get("password").toString();
        String Msg="新闻获取失败！";
        int code = False;
        if(newsId=="")
        {
            Msg="id为空！";
        } else if(state=="")
        {
            Msg="state为空！";
        }else {
            int result = adminService.updateNewsState(Integer.parseInt(newsId),Integer.parseInt(state));
            if (result==1)
            {
                Msg="修改状态成功";
                code = ok;
            }
            else
            {
                Msg="修改失败,请检查id是否正确！";
            }
        }
//        Map<String,Object> result = new HashMap<>(1);
//        result.put("data",userData);
//
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(null);
        return jr;
    }



    /**
     * 接口描述：
     * 返回所有用用户信息集合。
     * 接口类型 get
     * 返回数据格式
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[
     * records：[] //返回数据 （用户信息集合）
     * ]
     * total://用户总数
     * }*/
    @RequestMapping("/backGetAllUser")
    public JsonResult backGetAllUser(){

//        String sql = "select * from user";
//
//        List<Map<String,Object>> list_map = jdbcTemplate.queryForList(sql);
//        List<user> allUsers = new ArrayList<>();
////        循环实例每一个新闻对象，，然后添加到队列里面
//        for(int i=0;i<list_map.size();i++) {
//            user oneuser = new user();
//            oneuser.setUserId(Integer.parseInt(list_map.get(i).get("userId").toString()));
//            oneuser.setUserName(list_map.get(i).get("userName").toString());
//            oneuser.setPasssword(list_map.get(i).get("password").toString());
//            oneuser.setSex(Integer.parseInt(list_map.get(i).get("sex").toString()));
//            oneuser.setAge(Integer.parseInt(list_map.get(i).get("age").toString()));
//            oneuser.setPhone(list_map.get(i).get("phone").toString());
//            oneuser.setAvatar(list_map.get(i).get("avatar").toString());
//
//            allUsers.add(oneuser);
//        }
//        Map<String,Object> re = new HashMap<>(2);
//        re.put("records",allUsers);
//        re.put("total",list_map.size());
        String Msg="查询失败！";
        int code = False;
        List<user> users = adminService.selectAllUser();
        int total =adminService.countUsers();
        if(users.size()==0)
        {
            Msg="数据库没有用户";
        } else
        {
            Msg="获取所有用户成功！";
            code = ok;
        }
        Map<String,Object> result = new HashMap<>(1);
//
        result.put("data",users);
        result.put("total" ,users.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(result);
        return jr;
    }

    /**
     * 接口描述：
     * 接收用户id  获取用户信息
     * 接口类型 get
     * Query参数 id
     * 返回数据格式：
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[] //返回数据 （用户所有信息）
     * }*/

    @RequestMapping("/backSelectUser")
    public JsonResult backSelectUserById(@RequestParam("id") String userId)
    {
        Map<String,Object> result = new HashMap<>(1);
        List<user> allUsers=null;
        String Msg="新闻获取失败！";
        int code = False;
        if(userId=="")
        {
            Msg="id为空！";
        } else {
//        构建符合的数据格式，，，，使用map
            allUsers = adminService.selectUserById(Integer.parseInt(userId));
            if (allUsers.size()==0)
            {
                Msg="请输入正确的id！";
            } else {
                Msg ="获取用户信息成功！";
                code = ok;

            }
        }

//
        result.put("data",allUsers);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(allUsers);
        return jr;
    }


    /**
     * 接口描述：
     * 接收文章id 删除指定文章 返回接提示信息
     * 接口类型 post
     * Query参数 id
     * 返回数据格式：
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[] //返回数据 （管理员所有信息）
     * }*/
    @PostMapping("/backDeleteOneNews")
    public JsonResult backDeleteOneNews(@RequestParam("id") String newsId){

////
//        String sql = "delete from news where newsId="+newsId;
//        System.out.println("backDeleteOneNews sql:"+sql);
//        jdbcTemplate.execute(sql);
//
////        管理员已经登陆了，，id是全局变量
//        String sql2 = "select * from admin wehere id="+id;
//        System.out.println("backUpdateNews sql:"+sql2);
//        Map<String,Object> map_user = jdbcTemplate.queryForMap(sql2);
//        String[] userData= new String[3];
//
////根据管理员的表，管理员id，，，管理员名字，用户密码，直接返回三个数据，，考虑到后面表添加数据的话，
//        userData[0] = map_user.get("id").toString();
//        userData[1] = map_user.get("name").toString();
//        userData[2] = map_user.get("password").toString();
//        Map<String,Object> result = new HashMap<>(1);
//        result.put("data",userData);
        String Msg="新闻删除失败！";
        int code = False;
        if(newsId=="")
        {
            Msg="newsId为空！";
        } else {
            int result = adminService.deleteNews(Integer.parseInt(newsId));
            if (result==1)
            {
                Msg="删除新闻成功！";
                code = ok;
            }
            else
            {
                Msg="删除失败,请检查newsId是否正确！";
            }
        }

        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(null);
        return jr;
    }



    /**
     *
     * 接口描述：
     * 接收用户id 删除指定用户以及该用户所有发布的文章 无论状态如
     * 何 返回接提示信息
     * 接口类型 post
     * Query参数 id（用户id）
     * 返回数据格式：
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[] //返回数据 （管理员所有信息）
     * }*/
    @PostMapping("/backDeleteUserAndNews")
    public JsonResult backDeleteUserAndNews(@RequestParam("userId") String userId){

////
//        String sql = "delete from user where userId="+userId;
//        String sql1="delete from news where userId="+userId;
//        System.out.println("backDeleteUserAndNews sql"+sql);
//        System.out.println("backDeleteUserAndNews sql"+sql1);
//        jdbcTemplate.execute(sql);
//        jdbcTemplate.execute(sql1);
//
//
//        //        管理员已经登陆了，，id是全局变量
//        String sql2 = "select * from admin wehere id="+id;
//        System.out.println("backUpdateNews sql:"+sql2);
//        Map<String,Object> map_user = jdbcTemplate.queryForMap(sql2);
//        String[] userData= new String[3];
//
////根据管理员的表，管理员id，，，管理员名字，用户密码，直接返回三个数据，，考虑到后面表添加数据的话，
//        userData[0] = map_user.get("id").toString();
//        userData[1] = map_user.get("name").toString();
//        userData[2] = map_user.get("password").toString();
//        Map<String,Object> result = new HashMap<>(1);
//        result.put("data",userData);
//
        String Msg="用户删除失败！";
        int code = False;
        if(userId=="")
        {
            Msg="userId为空！";
        } else {
            int result = adminService.deleteUser(Integer.parseInt(userId));
            if (result==1)
            {
                Msg="删除用户成功！";
                code = ok;
            }
            else
            {
                Msg="删除失败,请检查userId是否正确！";
            }
        }
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(null);
        return jr;
    }




    /**
     * 接口描述：
     * 与文章状态字符 与用户id 返回通过的文章。
     * 接口类型 post
     * Query参数 userId、state（文章状态）
     * 返回数据格式
     * {
     * code://返回状态码
     * msg://返回信息描述
     * data:[
     * records：[]//返回数据 （文章信息集）
     * ]
     * }*/
    @RequestMapping("/backGetNewsByUS")
    public JsonResult backGetNewsByUserId(@RequestParam("id") String userId,
                                          @RequestParam("state") String state){
        List<news> allNews=null;
        String Msg="新闻获取失败！";
        int code = False;
        if(userId=="")
        {
            Msg="id为空！";
        } else if(state=="")
        {
            Msg="state为空！";
        }else {
//        构建符合的数据格式，，，，使用map
            allNews = adminService.selectNewsByUserId(Integer.parseInt(userId), Integer.parseInt(state));
            if (allNews.size()==0)
            {
                Msg="请输入正确的id或state！";
            } else {
                Msg ="获取新闻成功！";
                code = ok;
            }
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("records", allNews);
        data.put("total", allNews.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(data);
        return jr;
    }
}
