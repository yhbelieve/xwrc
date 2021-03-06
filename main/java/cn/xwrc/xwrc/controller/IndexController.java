package cn.xwrc.xwrc.controller;

import cn.xwrc.xwrc.pojo.*;
import cn.xwrc.xwrc.service.IndexService;
import cn.xwrc.xwrc.utils.DESUtils;
import cn.xwrc.xwrc.utils.DateUtils;
import cn.xwrc.xwrc.utils.StringUtils;
import cn.xwrc.xwrc.utils.Uuid;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/Index")
public class IndexController {

    @Resource
    private IndexService indexService;


    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("register")
    public String register() {
        return "view/register";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@ModelAttribute("msg") String msg, Model model) {
        System.out.println("login");
        model.addAttribute("msg", msg);
        return "view/login";
    }

    /**
     * 显示首页内容
     *
     * @param model
     * @return
     */

//
//    @RequestMapping(value = "/showIndex", method = RequestMethod.GET)
//    public String findAcategory(Model model, HttpServletRequest request) {
//        Acategory acategory = new Acategory();
//        acategory.setAxs(true);
//        List<Map> list = indexService.findAcategory(acategory);
//        for (int i = 0; i < list.size(); i++) {
//            Bcategory bcategory = new Bcategory();
//            bcategory.setAid(list.get(i).get("aid").toString());
//            List<Map> bcategories = indexService.findBcategoryByAid(bcategory);
//            list.get(i).put("bname", bcategories);
//        }
//        JSONArray list1 = JSON.parseArray(JSON.toJSONString(list));
//        Video video = new Video();
//        video.setIsShow(1);
//        video.setIsReview(1);
//
//        PageHelper.startPage(1, 6);
//        PageHelper.orderBy("like_num desc");
//        List<Map> likeVideos = indexService.findVideo(video);
//        PageHelper.startPage(1, 6);
//        PageHelper.orderBy("download_num desc");
//        List<Map> downloadVideos = indexService.findVideo(video);
//        PageHelper.startPage(1, 6);
//        PageHelper.orderBy("click_num desc");
//        List<Map> clickVideos = indexService.findVideo(video);
//        JSONArray likeList = JSON.parseArray(JSON.toJSONString(likeVideos));
//        JSONArray downloadList = JSON.parseArray(JSON.toJSONString(downloadVideos));
//        JSONArray clickList = JSON.parseArray(JSON.toJSONString(clickVideos));
//        model.addAttribute("likeList", likeList);
//        model.addAttribute("downloadList", downloadList);
//        model.addAttribute("clickList", clickList);
//        request.getSession().setAttribute("list", list1);
//        return "view/index";
//    }
//
//    /**
//     * 通过bid查找该大分类下面的所有小分类
//     *
//     * @param model
//     * @param bid
//     * @return
//     */
//    @RequestMapping(value = "findBcategoryVideo/{bid}", method = RequestMethod.GET)
//    public String findBcategoryVideo(Model model, @PathVariable("bid") String bid) {
//        PageHelper.startPage(1, 6);
//        PageHelper.orderBy("click_num desc");
//        List<Map> maps = indexService.findVideoByBid(bid);
//        JSONArray list = JSON.parseArray(JSON.toJSONString(maps));
//        Bcategory bcategory = indexService.selectByPrimaryKey(bid);
//        Map map = indexService.findAcategotyAll(bcategory.getAid());
//        model.addAttribute("list", list);
//        model.addAttribute("map", map);
//        return "view/products";
//    }
//
//    /**
//     * ajax查找评论
//     *
//     * @return
//     */
//    @RequestMapping("ajaxFindComment")
//    public Map ajaxFindComment() {
//        return null;
//    }
//
//    /**
//     * ajax查找试题
//     *
//     * @return
//     */
//    @RequestMapping("ajaxFindExam")
//    @ResponseBody
//    public Map ajaxFindExam(Model model, String next, String vid) {
//        if (StringUtils.isEmpty(next)) {
//            PageHelper.startPage(1, 1);
//        } else {
//            PageHelper.startPage(Integer.parseInt(next), 1);
//        }
//        List<Map> list = indexService.findExamByVid(vid);
//        Map map = new HashMap();
//        if (list.size() == 0) {
//            map.put("msg", "您已经答完了所有的题目！如果不过瘾，可以尝试其他实验哦");
//        } else {
//            map.put("a", list.get(0).get("a"));
//            map.put("b", list.get(0).get("b"));
//            map.put("c", list.get(0).get("c"));
//            map.put("d", list.get(0).get("d"));
//            map.put("answer", list.get(0).get("answer"));
//            map.put("tip", list.get(0).get("tip"));
//            map.put("content", list.get(0).get("content"));
//            if (StringUtils.isEmpty(next)) {
//                map.put("next", 2);
//            } else {
//                map.put("next", Integer.parseInt(next) + 1);
//            }
//            map.put("msg", "");
//        }
//        return map;
//    }
//
//    /**
//     * 通过bid查找该大分类下面的所有小分类
//     *
//     * @param model
//     * @param cid
//     * @return
//     */
//    @RequestMapping(value = "findCcategoryVideo/{cid}/{bid}", method = RequestMethod.GET)
//    public String findCcategoryVideo(Model model, @PathVariable("cid") String cid, @PathVariable("bid") String bid) {
//        Video video = new Video();
//        video.setIsShow(1);
//        video.setIsReview(1);
//        video.setCid(cid);
//
//        Bcategory bcategory = indexService.selectByPrimaryKey(bid);
//        Map map = indexService.findAcategotyAll(bcategory.getAid());
//        model.addAttribute("map", map);
//
//        List<Map> clickVideos = indexService.findVideo(video);
//        model.addAttribute("list", clickVideos);
//        return "view/products";
//    }
//
//    /**
//     * 查找一个实验相关内容
//     *
//     * @param model
//     * @param vid
//     * @return
//     */
//    @RequestMapping(value = "findVideoById/{vid}", method = RequestMethod.GET)
//    public String findVideoById(Model model, @PathVariable("vid") String vid) {
//        Map maps = indexService.findVideoById(vid);
//        System.out.println(maps);
//        model.addAttribute("list", maps);
//        return "view/single";
//    }
//
//    /**
//     * 评论添加成功
//     * 博客评论
//     * 实验评论
//     * 习题评论
//     *
//     * @param discuss
//     * @return
//     */
//
//
//    @RequestMapping(value = "ajaxAddComment", method = RequestMethod.POST)
//    public Map<String, Object> ajaxAddComment(Discuss discuss) {
//        discuss.setTime(DateUtils.getNowTime());
//        discuss.setId(Uuid.getUuid());
//        indexService.addCommment(discuss);
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "评论成功");
//        return map;
//    }
//
//    /**
//     * 模拟登陆
//     * @param request
//     * @return
//     */
//    @RequestMapping("testUserLogin")
//    @ResponseBody
//    public String testUserLogin( HttpServletRequest request) {
//        User user = new User();
//        user.setPassword("111111");
//        user.setUsername("过来看风");
//        user.setPassword(DESUtils.getEncryptString(user.getPassword()));
//        List<Map> listUser = sysUserService.findUser(user);
//        //登录成功
//        user.setUid(listUser.get(0).get("uid").toString());
//        user.setUsername(listUser.get(0).get("username").toString());
//        user.setPassword(listUser.get(0).get("password").toString());
//        user.setSex(listUser.get(0).get("sex").toString());
//        user.setEmail(listUser.get(0).get("email").toString());
//        user.setBirthday(listUser.get(0).get("birthday").toString());
//        user.setMoney((Integer) listUser.get(0).get("money"));
//        user.setPhone(listUser.get(0).get("phone").toString());
//        user.setImage(listUser.get(0).get("image").toString());
//        user.setIsShow((Boolean) listUser.get(0).get("is_show"));
//        user.setLevel((Integer) listUser.get(0).get("level"));
//        user.setIsActive((Boolean) listUser.get(0).get("is_active"));
//        user.setAddress(listUser.get(0).get("address").toString());
//        user.setRegistTime(listUser.get(0).get("regist_time").toString());
//        user.setType(listUser.get(0).get("type").toString());
//        request.getSession().setAttribute("user", user);
//        return "";
//    }
//
//    @RequestMapping("/test-logback")
//    @ResponseBody
//    public Date testLogback() {
//        User user = sysUserService.getAll("123");
//        System.out.print(user);
//        PageHelper.startPage(1, 2);
//        List<User> list = sysUserService.findAll();
//        System.out.println(list.size());
//
//        return new Date();
//    }


}
