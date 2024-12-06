package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CommodityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface CommodityInfoMapper extends BaseMapper<CommodityInfo> {

    /**
     * 分页查询商品信息
     *
     * @param page          分页对象
     * @param commodityInfo 商品信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> getCommodityByPage(Page<CommodityInfo> page, @Param("commodityInfo") CommodityInfo commodityInfo);

    /**
     * 小程序热销产品
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getCommodityHot();

    /**
     * 商品详情
     *
     * @param commodityId 商品ID
     * @return 结果
     */
    LinkedHashMap<String, Object> goodsDetail(@Param("commodityId") Integer commodityId);

    /**
     * 获取全部商家信息
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selShopList();

    /**
     * 商户信息
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    LinkedHashMap<String, Object> shopInfoById(@Param("shopId") Integer shopId);

    /**
     * 获取商铺及商品详细信息
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopInfoDetail(@Param("shopId") Integer shopId);

    /**
     * 店铺商品排序方式
     *
     * @param shopId 商铺ID
     * @param type   类型
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopCommoditySort(@Param("shopId") Integer shopId, @Param("type") Integer type);

    /**
     * 模糊查询店内商品
     *
     * @param shopId 商铺ID
     * @param key    商品
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> commodityLikeByShop(@Param("shopId") Integer shopId, @Param("key") String key);

    /**
     * 模糊查询商铺信息
     *
     * @param key 商品
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopInfoLike(@Param("key") String key);

    /**
     * 根据用户获取商品信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getGoodsByUserId(@Param("userId") Integer userId);
}
