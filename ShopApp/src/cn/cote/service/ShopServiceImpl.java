package cn.cote.service;

import cn.cote.mapper.CommodityMapper;
import cn.cote.mapper.MyCommodityMapper;
import cn.cote.pojo.Commodity;
import cn.cote.pojo.CommodityExample;
import cn.cote.pojo.MyCommodity;
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

    @Override
    public List<Commodity> getShop(int number) {
        CommodityExample example = new CommodityExample();
//      根据id倒序排列
        example.setOrderByClause("commodity_id DESC");
        example.setStartRow(0);
        example.setEndRow(number);
        List<Commodity> data=commodityMapper.selectByExampleWithBLOBs(example);
        return data;
    }

    @Override
    public int insertCommodity(Commodity commodity) {
//        int code=commodityMapper.insert(commodity);
        commodityMapper.insertSelective(commodity);
        int code = commodity.getCommodityId();
        return code;
    }

    @Override
    public int addCommodityImg(String newFileName, int this_id) {
        Commodity this_shop;
        this_shop = commodityMapper.selectByPrimaryKey(this_id);
        this_shop.setCommodityImg("/img/commoditys/"+newFileName);
        int code = commodityMapper.updateByPrimaryKey(this_shop);
        return code;
    }

    @Autowired
    MyCommodityMapper myCommodityMapper;
    @Override
    public List<MyCommodity> getShopAndMaster(int number) {
        List<MyCommodity> data=myCommodityMapper.selectCommodityAndMaster(0,number);
        return data;
    }
}
