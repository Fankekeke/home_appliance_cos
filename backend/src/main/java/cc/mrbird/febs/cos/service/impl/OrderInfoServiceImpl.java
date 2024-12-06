package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ShopInfoMapper;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.dao.OrderInfoMapper;
import cc.mrbird.febs.cos.service.IBulletinInfoService;
import cc.mrbird.febs.cos.service.ICommodityInfoService;
import cc.mrbird.febs.cos.service.IOrderInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    private final IUserInfoService userInfoService;

    private final ICommodityInfoService commodityInfoService;

    private final ShopInfoMapper shopInfoMapper;

    private final IBulletinInfoService bulletinInfoService;

    /**
     * 分页查询订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> getOrderByPage(Page<OrderInfo> page, OrderInfo orderInfo) {
        return baseMapper.getOrderByPage(page, orderInfo);
    }

    /**
     * 根据用户ID获取订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> orderListByUserId(Integer userId) {
        return baseMapper.orderListByUserId(userId);
    }

    /**
     * 获取主页数据
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> home() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("userNum", userInfoService.count());
        result.put("userAuditNum", userInfoService.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getType, 2)));
        result.put("orderNum", this.count(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getOrderStatus, 2)));
        result.put("commodityNum", this.commodityInfoService.count());
        // 订单统计
        result.put("orderRevenueData", baseMapper.orderRevenueData());
        // 本月订单量 本月收益
        result.put("orderMonthRevenue", baseMapper.orderMonthRevenue());
        // 本月收益占比
        result.put("orderPriceRate", baseMapper.orderPriceRate());
        // 商铺收益订单统计
        result.put("shopOrderRate", baseMapper.shopOrderRate());
        // 所有店铺信息
        List<LinkedHashMap<String, Object>> shopList = baseMapper.shopList();
        result.put("shopList", shopList);
        return result;
    }

    /**
     * 根据商铺获取订单统计
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> shopOrderRateByComm(Integer shopId) {
        return baseMapper.shopOrderRateByComm(shopId);
    }

    /**
     * 商家首页统计
     *
     * @param shopId 商家ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectHomeDataByShop(Integer shopId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        // 商家信息
        ShopInfo shopInfo = shopInfoMapper.selectOne(Wrappers.<ShopInfo>lambdaQuery().eq(ShopInfo::getSysUserId, shopId));

        List<OrderInfo> orderInfoList = this.list(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getShopId, shopInfo.getId()).eq(OrderInfo::getOrderStatus, 1).or().eq(OrderInfo::getOrderStatus, 3));
        // 总订单数量
        result.put("orderCode", orderInfoList.size());
        // 总收益
        result.put("orderPrice", orderInfoList.stream().map(e -> NumberUtil.mul(e.getOrderPrice(), e.getUserNum())).reduce(BigDecimal.ZERO, BigDecimal::add));
        // 商品数量
        result.put("pharmacyNum", commodityInfoService.count(Wrappers.<CommodityInfo>lambdaQuery().eq(CommodityInfo::getShopId, shopInfo.getId())));

        // 本月订单数量
        List<OrderInfo> orderList = baseMapper.selectOrderByMonth(shopInfo.getId());
        result.put("monthOrderNum", CollectionUtil.isEmpty(orderList) ? 0 : orderList.size());
        BigDecimal orderPrice = orderList.stream().map(e -> NumberUtil.mul(e.getOrderPrice(), e.getUserNum())).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 获取本月收益
        result.put("monthOrderPrice", orderPrice);

        // 本年订单数量
        List<OrderInfo> orderYearList = baseMapper.selectOrderByYear(shopInfo.getId());
        result.put("yearOrderNum", CollectionUtil.isEmpty(orderYearList) ? 0 : orderYearList.size());
        // 本年总收益
        BigDecimal orderYearPrice = orderYearList.stream().map(e -> NumberUtil.mul(e.getOrderPrice(), e.getUserNum())).reduce(BigDecimal.ZERO, BigDecimal::add);
        result.put("yearOrderPrice", orderYearPrice);

        // 公告信息
        result.put("bulletin", bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, 1)));

        // 近十天内订单统计
        result.put("orderNumWithinDays", baseMapper.selectOrderNumWithinDays(shopInfo.getId()));
        // 近十天内收益统计
        result.put("orderPriceWithinDays", baseMapper.selectOrderPriceWithinDays(shopInfo.getId()));
        return result;
    }

    /**
     * 获取订单详情
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectOrderDetail(String orderCode) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("order", null);
                put("detail", Collections.emptyList());
                put("shop", null);
                put("user", null);
                put("commodity", null);
            }
        };

        // 订单信息
        OrderInfo orderInfo = this.getOne(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getCode, orderCode));
        result.put("order", orderInfo);

        // 发起人
        UserInfo userInfo = new UserInfo();
        result.put("user", userInfo);

        // 商品信息
        CommodityInfo commodityInfo = commodityInfoService.getById(orderInfo.getCommodityId());
        result.put("commodity", commodityInfo);

        // 商铺信息
        ShopInfo shopInfo = shopInfoMapper.selectById(commodityInfo.getShopId());
        result.put("shop", shopInfo);

        // 订单详情
        result.put("detail", baseMapper.selectOrderDetail(orderCode));

        return result;
    }

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selGoodsCart(Integer userId) {
        return baseMapper.selGoodsCart(userId);
    }

    /**
     * 根据订单ID获取订单信息
     *
     * @param ids 订单IDs
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selOrderListByOrderIds(List<String> ids) {
        return baseMapper.selOrderListByOrderIds(ids);
    }

    /**
     * 获取用户所有订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getOrderListByUserId(Integer userId) {
        return baseMapper.getOrderListByUserId(userId);
    }

    /**
     * 根据用户获取卖出的订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getOrderByUserId(Integer userId) {
        return baseMapper.getOrderByUserId(userId);
    }
}
