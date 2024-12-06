package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 订单名称
     */
    private String orderName;

    /**
     * 商品ID
     */
    private Integer commodityId;

    /**
     * 所属商铺
     */
    private Integer shopId;

    /**
     * 价格
     */
    private BigDecimal orderPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单状态（0.正在拼单 1.已完成 2.拼单失败）
     */
    private Integer orderStatus;

    /**
     * 用户人数
     */
    private Integer userNum;

    /**
     * 下单时间
     */
    private String createDate;

    /**
     * 下单时间
     */
    private String payDate;

    /**
     * 发起人ID
     */
    private Integer userId;

    /**
     * 发起人收货地址
     */
    private Integer addressId;

    /**
     * 完成时间
     */
    private String finishDate;

    @TableField(exist = false)
    private String commodityName;

    @TableField(exist = false)
    private BigDecimal price;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String shopName;

    @TableField(exist = false)
    private Integer type;

}
