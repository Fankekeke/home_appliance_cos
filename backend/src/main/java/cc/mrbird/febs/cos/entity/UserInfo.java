package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    private String code;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 微信openID
     */
    private String openId;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 用户类型 1.普通用户 2.商铺
     */
    private Integer type;

    /**
     * 创建时间
     */
    private String createDate;


}
