package com.example.demo_mysql.controller;


import com.example.demo_mysql.pojo.*;
import com.example.demo_mysql.service.*;
import com.example.demo_mysql.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo_mysql.controller.baseController.False;
import static com.example.demo_mysql.controller.baseController.ok;


//使用@RestController返回的都是字符串形势
@CrossOrigin(origins = "*")
@RestController
@Api("前台接口")
@RequestMapping(value = "/news",method = {RequestMethod.GET,RequestMethod.POST})
public class NewsController {

    @Autowired
    JdbcTemplate MyjdbcTemplate;
    @Autowired
    public adminService adminService;
    @Autowired
    public newsService newsService;
    @Autowired
    public userService userService;
    @Autowired
    public starService starService;
    @Autowired
    public contentService contentService;
    @Autowired
    public historyService historyService;

    int userId = -1;




    /**
     * 用户登录接口参数：
     * 用户名
     * 密码
     * 性别  可选零或者是一 字符串类型
     * 年龄
     * */
    @ApiOperation("用户注册接口")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResult register(@RequestParam("userName") String userName,
                               @RequestParam("password") String password,
                               @RequestParam("sex") String sex,
                               @RequestParam("age") int age,
                               @RequestParam("phone") String phone
    ) {

//        System.out.println(userName + password + sex + age);
////      拼接前端接收到的参数
//
////      添加用户的时候，要避免用户名重复
//        String sql = "select * from user";
//        List<Map<String, Object>> list_maps = MyjdbcTemplate.queryForList(sql);
//        for (int i = 0; i < list_maps.size(); i++) {
//            String name = list_maps.get(i).get("userName").toString();
//            if(name.equals(userName)){
//                //该用户名字已经被注册
//                return "用户名已被注册";
//            }
//        }
//
//        System.out.println(list_maps.toString());
//
//        String sql2 = "insert into user(userName, password, sex, age) VALUES ('" +
//                userName + "','" + password + "','" + sex + "','" + age + "');";
//
//        System.out.println(sql2);
//        MyjdbcTemplate.execute(sql2);
        /**
         * @Description 修改完善方法
         * @Date 2023/2/18 9:52
         * @Created by 余
         */
        String Msg="接口错误！";
        int code = False;
        if(userName=="")
        {
            Msg="userName不能为空！";
        } else if(password=="")
        {
            Msg="password不能为空！";
        }else if (sex=="") {
            Msg="sex不能为空！";
        } else {
//        构建符合的数据格式，，，，使用map
            int sig = userService.registerUser(userName,password,age,sex,phone);
            if (sig==0)
            {
                Msg="注册失败！";
            } else
            if(sig == 1)
            {
                Msg ="注册成功！";
                code = ok;
            }
            else
            {
                Msg="该用户名已被注册！";
            }
        }

        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(null);
        return jr;

    }


    /**登录接口
     * 用户名
     * 密码
     *
     * 返回值是用户id
     * */
    @ApiOperation("用户登录接口")
    @RequestMapping("/login")
    public JsonResult login(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            HttpServletRequest request,
                            Model model
    ) {


//        查看数据库里面所有的用户信息
//        String sql = "select * from user";
//        List<Map<String, Object>> list_maps = MyjdbcTemplate.queryForList(sql);
//
//        System.out.println(list_maps.toString());
//
//        for (int i = 0; i < list_maps.size(); i++) {
//            String name = list_maps.get(i).get("userName").toString();
//            String pw = list_maps.get(i).get("password").toString();
//
//            System.out.println("userData" + name + "--" + pw);
//
////           用户校验
//            if (name.equals(userName) && pw.equals(password)) {
//
//                userId = Integer.parseInt(list_maps.get(i).get("userId").toString());
//                model.addAttribute("userId", userId);
//
//            }
//
//
//        }

        /**
         * @Description 修改完善方法
         * @Date 2023/2/18 9:52
         * @Created by 余
         */
        List<user> userInfo = null;
        String Msg="接口错误！";
        int code = False;
        if(userName=="")
        {
            Msg="userName不能为空！";
        } else if(password=="")
        {
            Msg="password不能为空！";
        } else {
//        构建符合的数据格式，，，，使用map
            userInfo = userService.userLogin(userName,password);
            if (userInfo.size()==0)
            {
                Msg="请检查账号或密码是否错误！";
            } else
            {
                Msg ="登录成功！";
                code = ok;
//                登陆成功保存登陆状态
                System.out.println("用户登录成功");
                model.addAttribute("userInfo", userInfo);
                request.getSession().setAttribute("userInfo", userInfo);


            }

        }
        Map<String,Object> result = new HashMap<>(1);
        result.put("date",userInfo);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(result);
        return jr;

//        返回的id数值是-1的时候表示  用户名或者是密码错误
//        return userId;

    }






    /**
     * 用户信息查询接口
     * 用户id
     * 返回值是用户类
     *
     * */
    @ApiOperation("个人用户信息查询接口")
    @RequestMapping("/selectOneUser")
    public JsonResult selectAllUser(@RequestParam("userId") int userId,
                              Model model){
//        String sql = "select * from user where userId='"+userId+"'";
//
//        Map<String,Object> map_string= MyjdbcTemplate.queryForMap(sql);
//
//        System.out.println(map_string);
//        user user = new user();
//        user.setUserId(Integer.parseInt(map_string.get("userId").toString()));
//        user.setUserName(map_string.get("userName").toString());
//        user.setpassword(map_string.get("password").toString());
//        user.setSex(Integer.parseInt(map_string.get("sex").toString()));
//        user.setAge(Integer.parseInt(map_string.get("age").toString()));
//        user.setPhone(map_string.get("phone").toString());
//        user.setAvatar(map_string.get("avatar").toString());
//
//        return user;

        List<user> user = userService.selectUserById(userId);
        Map<String,Object> result = new HashMap<>(1);
        result.put("date",user);
        JsonResult jr = new JsonResult();
        jr.setCode(ok);
        jr.setMsg("查询用户成功");
        jr.setObj(result);
        return jr;
    }



//    /**
//     * 修改用户信息
//     *
//     * 用户id
//     *头像
//     * 性别
//     *年龄
//     * 手机号
//     * */
//    @RequestMapping("/update")
//    public String update(@RequestParam("avatar") String avatar,
//                         @RequestParam("sex") int sex,
//
//
//
//
//
//
//
//
//                         @RequestParam("age") int age,
//                         @RequestParam("phone") String phone){
//        String sql = "update user set avatar='" +
//                avatar+"',sex="
//                +sex+",age="
//                +age+",pone='"
//                +phone +"' where userId=" +
//                userId;
//
//        System.out.println(sql);
//
//        MyjdbcTemplate.execute(sql);
//
//        return "update success!";
//
//    }




    /**
     * 用户发表的文章
     *
     * 用户id
     *
     * 返回值，，用户发表的所有新闻 array数组
     *
     * 返回值是数组的话就是 直接查询新闻表 条件是用户id  和新闻是不是发表状态
     * o 草稿  1 待审核  2 发布状态
     * */
//
////    没有测试过
//    @RequestMapping("/userNews")
//    public int[] userNews(){
//        String sql = "select newsId from news where userId="+userId+"and newsType='2'";
//        System.out.println("the sql:"+sql);
//
//        Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//        int[] num = new int[map_array.size()];
//        for(int i=0;i<map_array.size();i++)
//        {
//            num[i] = Integer.parseInt(map_array.get("newsId").toString());
//        }
//        return num;
//    }

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
    @RequestMapping("/userNews")
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
            allNews = userService.selectNewsByUserId(Integer.parseInt(userId), Integer.parseInt(state));
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





    @PostMapping("/update")
    public JsonResult update(@RequestParam("avatar") String avatar,
                             @RequestParam("sex") int sex,
                             @RequestParam("age") int age,
                             @RequestParam("phone") String phone,
                             @RequestParam("id") String userId){
        List<user> userInfo = null;
        String Msg="接口错误！";
        int code = False;
        if(userId=="")
        {
            Msg="userId不能为空！";
        }  else {
//        构建符合的数据格式，，，，使用map
            int sig = userService.updateUserInfo(avatar,age,phone,sex,Integer.parseInt(userId));
            if (sig==2)
            {
                Msg="该用户不存在！";
            } else
            {
                Msg ="用户信息更新成功！";
                code = ok;
            }

        }
        Map<String,Object> result = new HashMap<>(1);
        result.put("date",userInfo);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(result);
        return jr;
    }




    /**
     * 用户收藏的文章
     *
     *用户id
     *
     * 返回值  用户收藏的新闻  array
     *
     *
     *
     * */
////未测试
//    @RequestMapping("/selectUserStar")
//    public int[] selectUserStar(){
//        String sql = "select newsId from star where userId="+userId;
//        System.out.println("the sql:"+sql);
//        Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//        int[] num = new int[map_array.size()];
//        for(int i=0;i<map_array.size();i++)
//        {
//            num[i] = Integer.parseInt(map_array.get("newsId").toString());
//        }
//        return num;
//    }




    /**
     * 用户收藏的文章
     *
     *用户id
     *
     * 返回值  用户收藏的新闻  array
     *
     *
     *
     * */
//未测试
    @RequestMapping("selectUserStar")
    public JsonResult selectUserStar( @RequestParam("id") String userId){
//    String sql = "select newsId from star where userId="+userId;
//    System.out.println("the sql:"+sql);
//    Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//    int[] num = new int[map_array.size()];
//    for(int i=0;i<map_array.size();i++)
//    {
//        num[i] = Integer.parseInt(map_array.get("newsId").toString());
//    }
        List<star> newsStar = null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(userId=="")
        {
            Msg = "userI不能为空！";
        } else {
            newsStar = starService.selectNewsByUid(Integer.parseInt(userId));
            if(newsStar.size()==0)
            {
                Msg = "该用户收藏为零！";
            }else {
                date = new HashMap<>(newsStar.size());
                Msg="获取用户收藏成功！";
                code = ok;
                for (int i = 0;i<newsStar.size();i++)
                {
                    date.put(String.valueOf(i),userService.selectNewsById(newsStar.get(i).getNewsId()));
                }
            }

        }

        Map<String,Object> dateStar =  new HashMap<>(2);
        dateStar.put("records", date);
        dateStar.put("total", newsStar.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateStar);
        return jr;

    }


    /**
     * @Description 收藏接口
     * @Date 2023/2/18 17:01
     * @Created by 余
     */
    @RequestMapping("/doNewsStar")
    public JsonResult doNewsStar(@RequestParam("userId")String userId,@RequestParam("newsId")String newsId)
    {
        int sig=0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            sig = starService.addNewStar(Integer.parseInt(userId),Integer.parseInt(newsId));
            if(sig==0)
            {
                Msg = "收藏失败！请检查userId或newsId是否正确1";
            }else {
                List<news> New = userService.selectNewsByNS(Integer.parseInt(newsId),2);


                newsService.updateAllNews(New.get(0).getUserId(), New.get(0).getNewsId(),New.get(0).getNewsTitle(),New.get(0).getNewsArticle(),
                        New.get(0).getNewsImage(),New.get(0).getNewsTime(),New.get(0).getNewsYesCount(),New.get(0).getNewsStarCount()+1);
                Msg="收藏成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }
    /**
     * 取消收藏的新闻接口
     *
     * 新闻id
     *
     * */
//未测试
    @RequestMapping("/deleteStarNews")
    public JsonResult deleteStarNews(@RequestParam("newsId") String newsId,@RequestParam("userId") String userId){
        int sig=0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            sig = starService.deleteStar(Integer.parseInt(userId),Integer.parseInt(newsId));
            if(sig==0)
            {
                Msg = "取消收藏失败！请检查userId或newsId是否正确!";
            }else {
                List<news> New = userService.selectNewsByNS(Integer.parseInt(newsId),2);
                if(New.get(0).getNewsStarCount()!=0)
                    newsService.updateAllNews(New.get(0).getUserId(),New.get(0).getNewsId(),New.get(0).getNewsTitle(),New.get(0).getNewsArticle(),
                            New.get(0).getNewsImage(),New.get(0).getNewsTime(),New.get(0).getNewsYesCount(),New.get(0).getNewsStarCount()-1);
                Msg="取消收藏成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }





    /**
     * @Description 点赞接口
     * @Date 2023/2/18 18:58
     * @Created by 余
     */

    @RequestMapping("/doNewsYes")
    public JsonResult doNewsYes(@RequestParam("userId")String userId,@RequestParam("newsId")String newsId)
    {
        int sig=0;
        List<news> New=null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            New = userService.selectNewsById(Integer.parseInt(newsId));
            if(New.size()==0)
            {
                Msg = "点赞失败！请检查userId或newsId是否正确1";
            }else {
                newsService.updateAllNews(New.get(0).getUserId(), New.get(0).getNewsId(),New.get(0).getNewsTitle(),New.get(0).getNewsArticle(),
                        New.get(0).getNewsImage(),New.get(0).getNewsTime(),New.get(0).getNewsYesCount()+1,New.get(0).getNewsStarCount());
                Msg="点赞成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }
    /**
     * 取消点赞的新闻接口
     *
     * 新闻id
     *
     * */
//未测试
    @RequestMapping("/cancelYes")
    public JsonResult cancelYes(@RequestParam("newsId") String newsId,@RequestParam("userId") String userId){
        int sig=0;
        List<news> New=null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            New = userService.selectNewsById(Integer.parseInt(newsId));
            if(New.size()==0)
            {
                Msg = "取消点赞失败！请检查userId或newsId是否正确1";
            }else {
                if(New.get(0).getNewsYesCount()!=0)
                    newsService.updateAllNews(New.get(0).getUserId(), New.get(0).getNewsId(),New.get(0).getNewsTitle(),New.get(0).getNewsArticle(),
                            New.get(0).getNewsImage(),New.get(0).getNewsTime(),New.get(0).getNewsYesCount()-1,New.get(0).getNewsStarCount());
                Msg="取消点赞成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }






//
//    /**
//     *
//     *
//     * 用户草稿：get
//     参数：用户id
//     返回值：用户的草稿（array）
//     *
//     *
//     *
//     * */
////未测试
//    @RequestMapping("/selectNews0")
//    public int[] selectNews0(){
//        String sql = "select newsId from news where userId ="+userId+"and newsType='0'";
//        Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//        int[] num = new int[map_array.size()];
//        for(int i=0;i<map_array.size();i++)
//        {
//            num[i] = Integer.parseInt(map_array.get("newsId").toString());
//        }
//        return num;
//    }
//
//
//
//    /**
//     *
//     * 用户浏览记录：get
//     * 参数：用户id
//     * 返回值：浏览历史
//     *
//     * */
////    未测试
//    @RequestMapping("/selectUserHistory")
//    public int[] selectUserHistory(){
//        String sql = "select newsId from history where userId ="+userId;
//        Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//        int[] num = new int[map_array.size()];
//        for(int i=0;i<map_array.size();i++)
//        {
//            num[i] = Integer.parseInt(map_array.get("newsId").toString());
//        }
//        return num;
//    }
//
//
//
//
//
////    删除操作一定要明确用户对象，，，，数据都在一张news表里面  收藏表什么的，，，newsId会重复，加上用户id才唯一确定删除的信息不会删错
//    /**
//     * 删除新闻接口
//     *
//     * 新闻id
//     *
//     * */
////未测试
//    @RequestMapping("/deleteNews")
//    public void deleteNews(@RequestParam("newsId") int newsId)
//    {
//        String sql = "delete from news where newsId="+newsId+"and userId="+userId;
//        MyjdbcTemplate.execute(sql);
//    }
//
//
//
//    /**
//     * 删除收藏的新闻接口
//     *
//     * 新闻id
//     *
//     * */
////未测试
//    @RequestMapping("/deleteStarNews")
//    public void deleteStarNews(@RequestParam("newsId") int newsId){
//        String sql = "delete from star where newsId ="+newsId+"and userId="+userId;
//        MyjdbcTemplate.execute(sql);
//    }
//
//
//    /**
//     * 删除草稿
//     *
//     * 新闻id
//     *
//     * *///未测试
//    @RequestMapping("/deleteNews0")
//    public void deleteNews0(@RequestParam("newsId") int newsId){
//        String sql = "delete from news where newsId="+newsId+"and userId="+userId;
//        MyjdbcTemplate.execute(sql);
//    }
//
//
//    /**
//     *发布新闻
//     *
//     * 标题
//     * 正文
//     * 封面
//     * 类型
//     *
//     * 就是将编辑好的新闻，，，保存，，在news表里面，，，，类型设置成2
//     //* */
////未测试
//    @RequestMapping("/publishNews")
//    public String publishNews(@RequestParam("newsTitle") String newsTitle,
//                              @RequestParam("newsArticle") String newsArtcle,
//                              @RequestParam("newsImage") String newsImage,
//                              @RequestParam("newsType") String newsType
//    ){
////        发布新闻，，状态是字符2
//        newsType = "2";
//        String sql = "insert into news(userId,newsTitle,newsArticle\n" +
//                ",newsImage,newsType) values ("+userId+",'"+newsTitle+"',"+"'"+newsArtcle+"',"
//                +"'"+newsImage+"'"+"'"+newsType+"'";
//
//        System.out.println("publishNews sql :"+ sql);
//        MyjdbcTemplate.execute(sql);
//        return "publish news success!";
//    }
//
//
//
//    /**
//     *保存新闻      编辑好新闻之后没有点击发布，点保存直接变成草稿
//     *
//     *标题
//     * 正文
//     * 封面
//     * 类型
//     *
//     * */
////未测试
//    @RequestMapping("/saveNews0")
//    public String saveNews0(@RequestParam("newsTitle") String newsTitle,
//                            @RequestParam("newsArticle") String newsArtcle,
//                            @RequestParam("newsImage") String newsImage,
//                            @RequestParam("newsType") String newsType
//    ){
////        保存草稿，，状态是字符0
//        newsType = "0";
//        String sql = "insert into news(userId,newsTitle,newsArticle,newsImage,newsType) values ("+userId+",'"+newsTitle+"',"+"'"+newsArtcle+"',"
//                +"'"+newsImage+"',"+"'"+newsType+"'";
//
//        System.out.println("publishNews sql :"+ sql);
//        MyjdbcTemplate.execute(sql);
//        return "save news success!";
//    }
//
//
//
//
//
//    /**
//     *发布草稿
//     *
//     *新闻id
//     * */
////未测试
//    @RequestMapping("/publishNews0")
//    public void publishNews(@RequestParam("newsId") int newsId){
//
//        String sql = "update news set newsType='2' where newId = "+newsId;
//        MyjdbcTemplate.execute(sql);
//    }
//
//
//    /**
//     *获取所有的新闻
//     *
//     *返回值：所有新闻
//     *
//     * */
//    @RequestMapping("/selectAllNews")
//    public List<news> selectAllNews(){
//        String sql = "select * from news where newsType = '2'";
//        List<Map<String,Object>> list_map = MyjdbcTemplate.queryForList(sql);
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
//        return allNews;
//    }
//
//
//
//    /**
//     * 获取单个新闻
//     *
//     * 新闻id
//     *
//     * 返回值
//     * 新闻类
//     * */
//    @RequestMapping("/selectOneNews")
//    public news selectOneNew(@RequestParam("newsId") int newsId,
//                             Model model){
//        news oneNews = new news();
//        String sql = "select * from news where newsId ="+newsId;
//        Map<String,Object> map_news = MyjdbcTemplate.queryForMap(sql);
//        oneNews.setNewsId(Integer.parseInt(map_news.get("newsId").toString()));
//        oneNews.setUserId(Integer.parseInt(map_news.get("userId").toString()));
//        oneNews.setNewsTitle(map_news.get("newsTitle").toString());
//        oneNews.setNewsArticle(map_news.get("newsArticle").toString());
//        oneNews.setNewsImage(map_news.get("newsImage").toString());
//        oneNews.setNewsYesCount(Integer.parseInt(map_news.get("newsYesCount").toString()));
//        oneNews.setNewsStarCount(Integer.parseInt(map_news.get("newsStarCount").toString()));
//        oneNews.setNewsType(map_news.get("newsYesCount").toString());
//        return oneNews;
//    }
//
//    /**评论
//     *
//     * 新闻id
//     * 用户id
//     * 内容
//     * 时间
//     * */
////未测试
//    @RequestMapping("/goContent")
//    public void goContent(@RequestParam("newsId") int newsId,
//                          @RequestParam("content") String content,
//                          @RequestParam("time") String time){
//        String sql = "insert into content(userId,NewsId,time,content) values ("+
//                userId+","+newsId+",'"+time+"','"+content+"'";
//        System.out.println("goContent sql:"+sql);
//        MyjdbcTemplate.execute(sql);
////        实时刷新评论的时候直接，，在下面调用，，重载页面和接口的方法，这样就动态刷新看到评论了
//
//    }
//
//}
    /**
     *
     *
     * 用户草稿：get
     参数：用户id
     返回值：用户的草稿（array）
     *
     *
     *
     * */
//未测试
    @RequestMapping("/selectNews0")
    public JsonResult selectNews0(@RequestParam("id") String userId){
        List<news> allNews = null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            allNews = userService.selectNewsByNS(Integer.parseInt(userId),0);
            if(allNews.size()==0)
            {
                Msg = "该用户草稿为零！";
            }else {
                date = new HashMap<>(allNews.size());
                Msg="获取用户草稿成功！";
                code = ok;
                for (int i = 0;i<allNews.size();i++)
                {
                    date.put(String.valueOf(i),userService.selectNewsById(allNews.get(i).getNewsId()));
                }
            }
        }

        Map<String,Object> dateStar =  new HashMap<>(2);
        dateStar.put("records", date);
        dateStar.put("total", allNews.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateStar);
        return jr;

    }

    /**
     *
     * 用户浏览记录：get
     * 参数：用户id
     * 返回值：浏览历史
     *
     * */
//    未测试
    @RequestMapping("/selectUserHistory")
    public JsonResult selectUserHistory(@RequestParam("userId")String userId){
//        String sql = "select newsId from history where userId ="+userId;
//        Map<String,Object> map_array = MyjdbcTemplate.queryForMap(sql);
//        int[] num = new int[map_array.size()];
//        for(int i=0;i<map_array.size();i++)
//        {
//            num[i] = Integer.parseInt(map_array.get("newsId").toString());
//        }

        List<history> newsHistory = null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else {
            newsHistory = historyService.selectNewsByUid(Integer.parseInt(userId));
            if(newsHistory.size()==0)
            {
                Msg = "该用户浏览历史为空！";
            }else {
                date = new HashMap<>(newsHistory.size());
                Msg="获取用户浏览历史成功！";
                code = ok;
                for (int i = 0;i<newsHistory.size();i++)
                {
                    date.put(String.valueOf(i),userService.selectNewsById(newsHistory.get(i).getNewsId()));
                }
            }

        }
        Map<String,Object> dateStar =  new HashMap<>(2);
        dateStar.put("records", date);
        dateStar.put("total", newsHistory.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateStar);
        return jr;
    }






//    删除操作一定要明确用户对象，，，，数据都在一张news表里面  收藏表什么的，，，newsId会重复，加上用户id才唯一确定删除的信息不会删错

    /**
     * 删除新闻接口
     *
     * 新闻id
     *
     * */
//未测试
    @RequestMapping("/deleteNews")
    public JsonResult deleteNews(@RequestParam("newsId") String newsId)
    {
        List<history> newsHistory = null;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else {
            int sig = adminService.deleteNews(Integer.parseInt(newsId));
            if(sig==0)
            {
                Msg = "删除失败为空！";
            }else {

                Msg="删除成功！";
                code = ok;
            }

        }

        Map<String,Object> dateStar =  new HashMap<>(2);
        dateStar.put("records", date);
        dateStar.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateStar);
        return jr;
    }


    /**
     * 删除草稿
     *
     * 新闻id
     *
     * *///未测试
    @RequestMapping("/deleteNews0")
    public JsonResult deleteNews0(@RequestParam("newsId" ) String newsId){
        int sig=0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        }  else {
            sig = adminService.deleteNews(Integer.parseInt(newsId));
            if(sig==0)
            {
                Msg = "删除失败！请检查newsId是否正确!";
            }else {
                Msg="删除成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }


    /**
     *发布新闻
     *
     * 标题
     * 正文
     * 封面
     * 类型
     *
     * 就是将编辑好的新闻，，，保存，，在news表里面，，，，类型设置成2
     //* */
//未测试
    @RequestMapping("/publishNews")
    public JsonResult publishNews(@RequestParam("newsTitle") String newsTitle,
                                  @RequestParam("newsArticle") String newsArtcle,
                                  @RequestParam("newsImage") String newsImage,
                                  @RequestParam("newsType") String newsType,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("newsKind") String nKind,
                                  @RequestParam("userName") String uName) {

        int sig = 0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String, List<news>> date = null;
        if (newsTitle == "") {
            Msg = "newsTitle不能为空！";
        } else if (userId == "") {
            Msg = "userId不能为空！";
        } else if (newsArtcle == "") {
            Msg = "newsArtcle不能为空！";
        } else if (newsImage == "") {
            Msg = "newsImage不能为空！";
        } else if (newsType == "") {
            Msg = "newsType不能为空！";
        } else {
            long time = System.currentTimeMillis();
            String newsTime = Long.toString(time);
            sig = newsService.addNews(Integer.parseInt(userId),uName, newsTitle, newsArtcle, newsImage, newsTime, 0, 0, 1, nKind);
            if (sig == 0) {
                Msg = "添加失败！请检查userId或newsId是否正确!";
            } else {

                Msg = "发布成功！";
                code = ok;


            }

        }
        Map<String, Object> dateContent = new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }





    /**
     *保存新闻      编辑好新闻之后没有点击发布，点保存直接变成草稿
     *
     *标题
     * 正文
     * 封面
     * 类型
     *
     * */
//未测试
    @RequestMapping("/saveNews0")
    public JsonResult saveNews0(@RequestParam("newsTitle") String newsTitle,
                                @RequestParam("newsArticle") String newsArtcle,
                                @RequestParam("newsImage") String newsImage,
                                @RequestParam("newsType") String newsType,
                                @RequestParam("usrId") String userId,
                                @RequestParam("nKind") String nKind,
                                @RequestParam("userName") String uName) {

        int sig = 0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String, List<news>> date = null;
        if (newsTitle == "") {
            Msg = "newsTitle不能为空！";
        } else if (userId == "") {
            Msg = "userId不能为空！";
        } else if (newsArtcle == "") {
            Msg = "newsArtcle不能为空！";
        } else if (newsImage == "") {
            Msg = "newsImage不能为空！";
        } else if (newsType == "") {
            Msg = "newsType不能为空！";
        } else {
            long time = System.currentTimeMillis();
            String newsTime = Long.toString(time);
            sig = newsService.addNews(Integer.parseInt(userId),uName, newsTitle, newsArtcle, newsImage, newsTime, 0, 0, 0, nKind);
            if (sig == 0) {
                Msg = "添加失败！请检查userId或newsId是否正确!";
            } else {

                Msg = "发布成功！";
                code = ok;


            }

        }
        Map<String, Object> dateContent = new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }





    /**
     *发布草稿
     *
     *新闻id
     * */
////未测试
//    @RequestMapping("/publishNews0")
//    public void publishNews(@RequestParam("newsId") int newsId){
//
//
//    }


    /**
     *获取所有的新闻
     *
     *返回值：所有新闻
     *
     * */
    @RequestMapping("/selectAllNews")
    public JsonResult selectAllNews(){
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
        int total = 0;
        List<news> allNews=null;



        /**
         * @Description 更换查询方法，采用MyBatis代替Jdbc
         * @Date 2023/2/17 22:21
         * @Created by 余
         */
        allNews = adminService.selectAllNewsByState(2);
        if(allNews.size()!=0) {
            total = adminService.countNews(2);
//        构建符合的数据格式，，，，使用map
            Map<String, Object> data = new HashMap<>(2);
            data.put("records", allNews);
            data.put("total", allNews.size());

            JsonResult jr = new JsonResult();
            jr.setCode(ok);
            jr.setMsg("获取所有发布的新闻");
            jr.setObj(data);
            return jr;
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
     * 获取单个新闻
     *
     * 新闻id
     *
     * 返回值
     * 新闻类
     * */
    @RequestMapping("/selectOneNews")
    public JsonResult selectOneNew(@RequestParam("newsId") String newsId,
                                   @RequestParam("userId") String uid,
                                   Model model){

//        传的userId主要是为了知道是哪个用户查看了，，写进浏览历史表
//        后面改可以在登陆的时候，userId直接存在session里面的

        List<news> allNews=null;
        String Msg="新闻获取失败！";
        int code = False;
        if(newsId=="")
        {
            Msg="id为空！";
        }else {
//        构建符合的数据格式，，，，使用map
            allNews = adminService.selectOneNewsById(Integer.parseInt(newsId));
            if (allNews.size()==0)
            {
                Msg="请输入正确的id！";
            } else {
                Msg ="获取新闻成功！";
                code = ok;

                int n = historyService.addHistory(Integer.parseInt(uid),Integer.parseInt(newsId));
            }
        }
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(allNews);
        return jr;
    }
    /**
     * @Description TODO
     * @Date 2023/2/18 15:28
     * @Created by 余
     */
    @RequestMapping("/contentView")
    public JsonResult goContentALl(@RequestParam ("id")String newsId)
    {
        List<content> newsContent = null;
        Map<String,Object> content = new HashMap<>(2);
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "userI不能为空！";
        } else {
            newsContent = contentService.selectContentByNId(Integer.parseInt(newsId));

            if(newsContent.size()==0)
            {
                Msg = "该用户评论为零！";
            }else {

                Msg="获取用户评论成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", newsContent);
        dateContent.put("total", newsContent.size());
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;
    }


    /**评论
     *
     * 新闻id
     * 用户id
     * 内容
     * 时间
     * */
//未测试
    @RequestMapping("/goContent")
    public JsonResult goContent(@RequestParam("newsId") String newsId,
                                @RequestParam("userId") String userId,
                                @RequestParam("content") String content,
                                @RequestParam("userImage") String uImage,
                                @RequestParam("username") String username){
        int sig=0;
        String Msg = "接口错误，获取失败！";
        int code = False;
        Map<String,List<news>> date =  null;
        if(newsId=="")
        {
            Msg = "newsId不能为空！";
        } else
        if(userId=="")
        {
            Msg = "userId不能为空！";
        } else
        if(username=="")
        {
            Msg = "username不能为空！";
        } else{
            sig = userService.addContent(Integer.parseInt(userId),Integer.parseInt(newsId),uImage,content,Long.toString(System.currentTimeMillis()),username);
            if(sig==0)
            {
                Msg = "该用户评论失败！";
            }else {

                Msg="用户评论成功！";
                code = ok;
            }

        }

        Map<String,Object> dateContent =  new HashMap<>(2);
        dateContent.put("records", null);
        dateContent.put("total", 0);
        JsonResult jr = new JsonResult();
        jr.setCode(code);
        jr.setMsg(Msg);
        jr.setObj(dateContent);
        return jr;

    }

}


