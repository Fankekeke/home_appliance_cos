package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.OrderInfoMapper;
import cc.mrbird.febs.cos.entity.CommodityInfo;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.entity.ShopInfo;
import cc.mrbird.febs.cos.dao.ShopInfoMapper;
import cc.mrbird.febs.cos.service.ICommodityInfoService;
import cc.mrbird.febs.cos.service.IShopInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopInfoServiceImpl extends ServiceImpl<ShopInfoMapper, ShopInfo> implements IShopInfoService {

    private final ICommodityInfoService commodityInfoService;

    private final OrderInfoMapper orderInfoMapper;

    /**
     * 分页查询商铺信息
     *
     * @param page     分页对象
     * @param shopInfo 商铺信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> getShopInfoByPage(Page<ShopInfo> page, ShopInfo shopInfo) {
        return baseMapper.getShopInfoByPage(page, shopInfo);
    }

    /**
     * 获取商铺信息详情
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectShopDetail(Integer shopId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("user", null);
                put("order", Collections.emptyList());
            }
        };

        ShopInfo shopInfo = this.getOne(Wrappers.<ShopInfo>lambdaQuery().eq(ShopInfo::getSysUserId, shopId));

        // 商铺信息
        LinkedHashMap<String, Object> shop = baseMapper.selectShopDetail(shopInfo.getId());
        result.put("user", shop);
        // 商铺订单
        List<OrderInfo> orderList = orderInfoMapper.selectList(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getShopId, shopInfo.getId()));
        result.put("order", orderList);
        return result;
    }

    /**
     * 查询商铺信息
     *
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> shopInfoHot() {
        List<LinkedHashMap<String, Object>> shopList = baseMapper.shopInfoHot();
        shopList.forEach(item -> {
            item.put("commodityList", commodityInfoService.list(Wrappers.<CommodityInfo>lambdaQuery().eq(CommodityInfo::getShopId, Integer.parseInt(item.get("id").toString()))));
        });
        return shopList;
    }
}
