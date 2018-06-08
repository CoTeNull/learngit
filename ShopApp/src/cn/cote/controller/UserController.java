package cn.cote.controller;

import cn.cote.myutils.WebData;
import cn.cote.pojo.User;
import cn.cote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("user")
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 登陆
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public WebData login(HttpServletRequest request,HttpSession session){
     //获取用户数据，放入User模型中
     User this_user =new User();
     this_user.setUserName(request.getParameter("userName"));
     this_user.setUserPassword(request.getParameter("userPassword"));
     //引用工具类返回前端
     this_user=userService.findUserMessage(this_user);
     WebData data =new WebData();
     if(this_user!=null){
         //------------手动日志-------------
         System.out.println("登陆:找到的User为："+this_user);
         //------------手动日志-------------
         data.setCode(1);
         data.setMessage("FindIt");
         data.setData(this_user);
         session.setAttribute("userId",this_user.getUserId());
     }else{
         data.setCode(0);
         data.setMessage("NotFind");
     }
     return data;
    }
    /**
     * 注册
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public WebData register(HttpServletRequest request,HttpSession session){
        User this_user =new User();
        this_user.setUserName(request.getParameter("userName"));
        this_user.setUserPassword(request.getParameter("userPassword"));
        WebData data = new WebData();
        //尝试插入当前新用户数据
        int code=userService.insertUser(this_user);
        if(code==1){
          data.setCode(1);
          data.setMessage("注册成功");
          //通过Session创建userId
         this_user = userService.findUserMessage(this_user);
         session.setAttribute("userId",this_user.getUserId());
        //------------手动日志-------------
         System.out.println("新入户入驻,userId为："+this_user.getUserId());
        //------------手动日志-------------
        }else if(code==0){
          data.setCode(0);
          data.setMessage("用户存在");
        }
        else{
          data.setCode(-1);
          data.setMessage("注册失败");
        }
        return data;
    }
    /**
     * 修改昵称信息
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "registerNc",method = RequestMethod.POST)
    public WebData registerNc(HttpServletRequest request,HttpSession session){
    String this_userNc =request.getParameter("userNc");
    //从当前的Session中获取当前用户的userId
    int this_id= (int) session.getAttribute("userId");
    int code=userService.changeRegisterNc(this_userNc,this_id);
    WebData data=new WebData();
    data.setCode(code);
    data.setMessage("修改成功");
    return data;
    }
    /**
     *修改用户头像
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "registerImg",method = RequestMethod.POST)
    public WebData registerImg(MultipartFile file,HttpSession session) throws IOException {
        WebData data = new WebData();
        if (!file.isEmpty()) {
            // 设置图片存放路径
            String path = "D:\\Web\\JavaDemo\\ShopApp\\web\\view\\img\\userImg\\";
            String originalFileName = file.getOriginalFilename();
            String type =originalFileName.substring(originalFileName.lastIndexOf("."));
            if(".GIF".equals(type.toUpperCase())||".PNG".equals(type.toUpperCase())||".JPG".equals(type.toUpperCase())){
                String newFileName = UUID.randomUUID() + type;// 新的图片名称
                File newFile = new File(path + newFileName);// 新的图片
                //------------手动日志-------------
                System.out.println("接受到图片为：\n"+newFile);
                //------------手动日志-------------
                file.transferTo(newFile);  // 将内存中的数据写入磁盘
//              存入数据库
                int this_id= (int) session.getAttribute("userId");
                int code = userService.addUserImg(newFileName,this_id);
                data.setCode(code);
                data.setMessage("修改头像操作完成");
                data.setData("/img/userImg/"+newFileName);
            }else{data.setCode(0);data.setMessage("图片为不接受的类型");}
        }else {data.setCode(-1);data.setMessage("发生未知错误");}

        return data;
    }
}
