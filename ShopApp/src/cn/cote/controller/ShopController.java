package cn.cote.controller;

import cn.cote.myutils.WebData;
import cn.cote.pojo.Commodity;
import cn.cote.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("shop")
@ResponseBody
public class ShopController {
    @Autowired
    ShopService shopService;

    @RequestMapping(value = "getShopByPage",method = RequestMethod.GET)
    public WebData getShopByPage(HttpServletRequest request){
        int pageId = Integer.parseInt(request.getParameter("pageId"));
        System.out.println("当前页数："+pageId);
        List<Commodity> this_list=shopService.getShopByPage(pageId);
        WebData data= new WebData();
        data.setCode(0);
        data.setLength(this_list.size());
        data.setMessage("成功查找");
        data.setData(this_list);
        return data;
    }

    @RequestMapping(value = "getShop",method = RequestMethod.GET)
    public WebData getShop(HttpServletRequest request){
        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("获取商品个数为："+number);
        List<Commodity> this_list=shopService.getShop(number);
        WebData data= new WebData();
        data.setCode(0);
        data.setLength(this_list.size());
        data.setMessage("成功查找");
        data.setData(this_list);
        return data;
    }
}
