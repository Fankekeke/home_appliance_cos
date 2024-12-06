package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.Evaluation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IEvaluationService extends IService<Evaluation> {

    /**
     * 分页查询评价信息
     *
     * @param page       分页对象
     * @param evaluation 评价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> getEvaluationPage(Page<Evaluation> page, Evaluation evaluation);

    /**
     * 根据订单获取评价
     *
     * @param id 订单ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getEvaluationByOrderId(Integer id);

    /**
     * 根据商品ID获取评价
     *
     * @param id 商品ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getEvaluationByCommodityId(Integer id);
}
