package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MessageInfo;
import cc.mrbird.febs.cos.entity.ShopInfo;
import cc.mrbird.febs.cos.service.IMessageInfoService;
import cc.mrbird.febs.cos.service.IShopInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/message-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageInfoController {

    private final IMessageInfoService messageInfoService;

    private final IShopInfoService shopInfoService;

    /**
     * 分页查询消息
     *
     * @param page        分页对象
     * @param messageInfo 消息信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MessageInfo> page, MessageInfo messageInfo) {
        return R.ok(messageInfoService.getMessageByPage(page, messageInfo));
    }

    /**
     * 消息回复
     *
     * @param messageInfo
     * @return 结果
     */
    @PostMapping("/messageReply")
    public R messageReply(MessageInfo messageInfo) {
        messageInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        messageInfo.setTaskStatus(0);
        return R.ok(messageInfoService.save(messageInfo));
    }

    /**
     * 查找聊天记录
     *
     * @param takeUser
     * @param sendUser
     * @return 结果
     */
    @GetMapping("/getMessageDetail")
    public R getMessageDetail(@RequestParam(value = "takeUser") Integer takeUser, @RequestParam(value = "sendUser") Integer sendUser, @RequestParam(value = "userId") Integer userId) {
        ShopInfo shopInfo = shopInfoService.getOne(Wrappers.<ShopInfo>lambdaQuery().eq(ShopInfo::getSysUserId, userId));
        if (takeUser.equals(shopInfo.getUserId())) {
            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
                    .eq(MessageInfo::getTakeUser, takeUser).eq(MessageInfo::getSendUser, sendUser));
        } else {
            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
                    .eq(MessageInfo::getTakeUser, sendUser).eq(MessageInfo::getSendUser, takeUser));
        }
        return R.ok(messageInfoService.getMessageDetail(takeUser, sendUser));
    }

    /**
     * 查询消息信息
     *
     * @param userId
     * @return 结果
     */
    @GetMapping("/messageListById")
    public R messageListById(@RequestParam Integer userId) {
        ShopInfo shopInfo = shopInfoService.getOne(Wrappers.<ShopInfo>lambdaQuery().eq(ShopInfo::getSysUserId, userId));
        return R.ok(messageInfoService.messageListById(shopInfo.getUserId()));
    }

    /**
     * 删除评价信息
     *
     * @param ids 主键
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(messageInfoService.removeByIds(ids));
    }

    /**
     * 根据用户编号获取联系人
     *
     * @param userCode 用户编号
     * @return 结果
     */
    @GetMapping("/contact/person")
    public R selectContactPerson(@RequestParam("userCode") String userCode, @RequestParam("flag") Integer flag) {
        return R.ok(messageInfoService.selectContactPerson(userCode, flag));
    }

    /**
     * 查询聊天记录
     *
     * @return 结果
     */
    @GetMapping("/record")
    public R selectChatList(@RequestParam("expertCode") String expertCode, @RequestParam("enterpriseCode") String enterpriseCode) {
        return R.ok(messageInfoService.selectChatList(expertCode, enterpriseCode));
    }

}
