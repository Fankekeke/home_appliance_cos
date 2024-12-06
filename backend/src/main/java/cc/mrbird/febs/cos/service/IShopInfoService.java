package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ShopInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IShopInfoService extends IService<ShopInfo> {

    /**
     * 分页查询商铺信息
     *
     * @param page     分页对象
     * @param shopInfo 商铺信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> getShopInfoByPage(Page<ShopInfo> page, ShopInfo shopInfo);

    /**
     * 获取商铺信息详情
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectShopDetail(Integer shopId);

    /**
     * 查询商铺信息
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopInfoHot();
}
