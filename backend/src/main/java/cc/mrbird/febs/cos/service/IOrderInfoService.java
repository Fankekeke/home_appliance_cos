package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.OrderInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 分页查询订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> getOrderByPage(Page<OrderInfo> page, OrderInfo orderInfo);

    /**
     * 根据用户ID获取订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> orderListByUserId(Integer userId);

    /**
     * 获取主页数据
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> home();

    /**
     * 根据商铺获取订单统计
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> shopOrderRateByComm(Integer shopId);

    /**
     * 商家首页统计
     *
     * @param shopId 商家ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectHomeDataByShop(Integer shopId);

    /**
     * 获取订单详情
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    LinkedHashMap<String, Object> selectOrderDetail(String orderCode);

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selGoodsCart(Integer userId);

    /**
     * 根据订单ID获取订单信息
     *
     * @param ids 订单IDs
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selOrderListByOrderIds(List<String> ids);

    /**
     * 获取用户所有订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getOrderListByUserId(Integer userId);

    /**
     * 根据用户获取卖出的订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getOrderByUserId(@Param("userId") Integer userId);
}
