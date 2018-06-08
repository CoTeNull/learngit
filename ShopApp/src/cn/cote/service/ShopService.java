package cn.cote.service;

import cn.cote.pojo.Commodity;

import java.util.List;

public interface ShopService {
    /**
     * 分页查找商品信息
     * @param pageId
     * @return
     */
    List<Commodity> getShopByPage(int pageId);
}
