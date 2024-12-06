package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.Evaluation;
import cc.mrbird.febs.cos.service.IEvaluationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/evaluation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EvaluationController {

    private final IEvaluationService evaluationService;

    /**
     * 根据订单获取评价
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/getEvaluationByOrderId")
    public R getEvaluationByOrderId(@RequestParam Integer id) {
        return R.ok(evaluationService.getEvaluationByOrderId(id));
    }

    /**
     * 根据商品ID获取评价
     *
     * @param id 商品ID
     * @return 结果
     */
    @GetMapping("/getEvaluationByCommodityId")
    public R getEvaluationByCommodityId(@RequestParam Integer id) {
        return R.ok(evaluationService.getEvaluationByCommodityId(id));
    }

    /**
     * 分页查询评价信息
     *
     * @param page       分页对象
     * @param evaluation 评价信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<Evaluation> page, Evaluation evaluation) {
        return R.ok(evaluationService.getEvaluationPage(page, evaluation));
    }

    /**
     * 删除评价信息
     *
     * @param ids 主键
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(evaluationService.removeByIds(ids));
    }

}
