package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.Evaluation;
import cc.mrbird.febs.cos.dao.EvaluationMapper;
import cc.mrbird.febs.cos.service.IEvaluationService;
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
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements IEvaluationService {

    /**
     * 分页查询评价信息
     *
     * @param page       分页对象
     * @param evaluation 评价信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> getEvaluationPage(Page<Evaluation> page, Evaluation evaluation) {
        return baseMapper.getEvaluationPage(page, evaluation);
    }

    /**
     * 根据订单获取评价
     *
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getEvaluationByOrderId(Integer id) {
        return baseMapper.getEvaluationByOrderId(id);
    }

    /**
     * 根据商品ID获取评价
     *
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getEvaluationByCommodityId(Integer id) {
        return baseMapper.getEvaluationByCommodityId(id);
    }
}
