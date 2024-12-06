package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CommodityInfo;
import cc.mrbird.febs.cos.dao.CommodityInfoMapper;
import cc.mrbird.febs.cos.service.ICommodityInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
public class CommodityInfoServiceImpl extends ServiceImpl<CommodityInfoMapper, CommodityInfo> implements ICommodityInfoService {

    /**
     * 分页查询商品信息
     *
     * @param page          分页对象
     * @param commodityInfo 商品信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> getCommodityByPage(Page<CommodityInfo> page, CommodityInfo commodityInfo) {
        return baseMapper.getCommodityByPage(page, commodityInfo);
    }

    /**
     * 小程序热销产品
     *
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getCommodityHot() {
        return baseMapper.getCommodityHot();
    }

    /**
     * 商品详情
     *
     * @param commodityId 商品ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> goodsDetail(Integer commodityId) {
        return baseMapper.goodsDetail(commodityId);
    }

    /**
     * 查询店铺及商品信息
     *
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selShopDetailList() {
        List<LinkedHashMap<String, Object>> shopList = baseMapper.selShopList();
        shopList.forEach(item -> item.put("goods", this.list(Wrappers.<CommodityInfo>lambdaQuery().eq(CommodityInfo::getShopId, item.get("id")))));
        return shopList;
    }

    @Override
    public LinkedHashMap<String, Object> getShopDetail(Integer shopId) {
        LinkedHashMap<String, Object> result = baseMapper.shopInfoById(shopId);
        result.put("goods", baseMapper.shopInfoDetail(shopId));
        return result;
    }

    /**
     * 店铺商品排序方式
     *
     * @param shopId 商铺ID
     * @param type   类型
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> shopCommoditySort(Integer shopId, Integer type) {
        return baseMapper.shopCommoditySort(shopId, type);
    }

    /**
     * 模糊查询店内商品
     *
     * @param shopId 商铺ID
     * @param key    商品
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> commodityLikeByShop(Integer shopId, String key) {
        return baseMapper.commodityLikeByShop(shopId, key);
    }

    /**
     * 查找商品或店铺
     *
     * @param key 商品
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> getGoodsFuzzy(String key) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("commodity", baseMapper.commodityLikeByShop(null, key));
        result.put("shop", baseMapper.shopInfoLike(key));
        return result;
    }

    /**
     * 根据用户获取商品信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getGoodsByUserId(Integer userId) {
        return baseMapper.getGoodsByUserId(userId);
    }
}
