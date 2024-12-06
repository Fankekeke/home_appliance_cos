package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 收获地址管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AddressInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 地址
     */
    private String address;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号码
     */
    private String phone;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 默认地址
     */
    private Integer defaultAddress;

    /**
     * 创建时间
     */
    private String createDate;


}
