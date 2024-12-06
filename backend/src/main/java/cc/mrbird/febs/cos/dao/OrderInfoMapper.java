package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 分页查询订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> getOrderByPage(Page<OrderInfo> page, @Param("orderInfo") OrderInfo orderInfo);

    /**
     * 根据用户ID获取订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> orderListByUserId(@Param("userId") Integer userId);

    /**
     * 订单统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> orderRevenueData();

    /**
     * 本月订单量 本月收益
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> orderMonthRevenue();

    /**
     * 本月收益占比
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> orderPriceRate();

    /**
     * 商铺收益订单统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopOrderRate();

    /**
     * 根据商铺获取订单统计
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopOrderRateByComm(@Param("shopId") Integer shopId);

    /**
     * 订单详情
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderDetailList(@Param("orderCode") String orderCode);

    /**
     * 所有店铺信息
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopList();

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selGoodsCart(@Param("userId") Integer userId);

    /**
     * 根据订单ID获取订单信息
     *
     * @param ids 订单IDs
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selOrderListByOrderIds(@Param("ids") List<String> ids);

    /**
     * 获取用户所有订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getOrderListByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户获取卖出的订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getOrderByUserId(@Param("userId") Integer userId);

    /**
     * 订单详情
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderDetail(@Param("orderCode") String orderCode);

    /**
     * 获取本月订单信息
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    List<OrderInfo> selectOrderByMonth(@Param("shopId") Integer shopId);

    /**
     * 获取本年订单信息
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    List<OrderInfo> selectOrderByYear(@Param("shopId") Integer shopId);

    /**
     * 十天内订单数量统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderNumWithinDays(@Param("shopId") Integer shopId);

    /**
     * 十天内订单收益统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderPriceWithinDays(@Param("shopId") Integer shopId);
}
