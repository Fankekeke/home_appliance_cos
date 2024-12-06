package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.MessageInfo;
import cc.mrbird.febs.cos.dao.MessageInfoMapper;
import cc.mrbird.febs.cos.service.IMessageInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
public class MessageInfoServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements IMessageInfoService {

    /**
     * 分页查询消息
     *
     * @param page        分页对象
     * @param messageInfo 消息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> getMessageByPage(Page<MessageInfo> page, MessageInfo messageInfo) {
        return baseMapper.getMessageByPage(page, messageInfo);
    }

    /**
     * 查询消息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> messageListById(Integer userId) {
        return baseMapper.messageListById(userId);
    }

    /**
     * 查找聊天记录
     *
     * @param takeUser 发送者
     * @param sendUser 接收人
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getMessageDetail(Integer takeUser, Integer sendUser) {
        return baseMapper.getMessageDetail(takeUser, sendUser);
    }

    /**
     * 根据用户编号获取联系人
     *
     * @param userCode 用户编号
     * @param flag
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectContactPerson(String userCode, Integer flag) {
        return baseMapper.selectContactPerson(userCode, flag);
    }

    /**
     * 查询聊天记录
     *
     * @param expertCode
     * @param enterpriseCode
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectChatList(String expertCode, String enterpriseCode) {
        return baseMapper.selectChatList(expertCode, enterpriseCode);
    }
}
