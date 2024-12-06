package cc.mrbird.febs.cos.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商铺管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class
ShopInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 店铺编号
     */
    private String code;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 标签
     */
    private String tag;

    /**
     * 商铺名称
     */
    private String name;

    /**
     * 商铺图片
     */
    private String images;

    /**
     * 账户用户ID
     */
    private Integer sysUserId;

    /**
     * 状态（0.未审核 1.已审核）
     */
    private String status;

    /**
     * 头像
     */
    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String userName;

}
