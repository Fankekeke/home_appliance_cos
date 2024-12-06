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
 * 用户评价
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评价
     */
    private String content;

    /**
     * 评价时间
     */
    private String createDate;

    /**
     * 所属订单
     */
    private Integer orderId;

    @TableField(exist = false)
    private String commodityName;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String shopName;

    @TableField(exist = false)
    private Integer type;

    @TableField(exist = false)
    private Integer shopId;
}
