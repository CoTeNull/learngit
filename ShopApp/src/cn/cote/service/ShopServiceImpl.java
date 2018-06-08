package cn.cote.service;

import cn.cote.mapper.CommodityMapper;
import cn.cote.pojo.Commodity;
import cn.cote.pojo.CommodityExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public List<Commodity> getShopByPage(int pageId) {
        CommodityExample example = new CommodityExample();
//      根据id倒序排列
        example.setOrderByClause("commodity_id DESC");
        example.setStartRow((pageId-1)*6);
//      读取六条数据 number = 5
        example.setEndRow(pageId*6);
        List<Commodity> data=commodityMapper.selectByExampleWithBLOBs(example);
        return data;
    }
}
