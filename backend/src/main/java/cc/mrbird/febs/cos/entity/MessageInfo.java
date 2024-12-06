package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 消息管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 发送人
     */
    private Integer sendUser;

    /**
     * 接收人
     */
    private Integer takeUser;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送时间
     */
    private String createDate;

    /**
     * 状态
     */
    private Integer taskStatus;

    @TableField(exist = false)
    private String sendUserName;

    @TableField(exist = false)
    private String takeUserName;
}
