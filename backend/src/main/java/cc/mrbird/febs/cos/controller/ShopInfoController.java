package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.ShopInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IShopInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cc.mrbird.febs.system.service.UserService;
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
@RequestMapping("/cos/shop-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopInfoController {

    private final IShopInfoService shopInfoService;

    private final IUserInfoService userInfoService;

    private final UserService userService;

    /**
     * 分页查询商铺信息
     *
     * @param page     分页对象
     * @param shopInfo 商铺信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ShopInfo> page, ShopInfo shopInfo) {
        return R.ok(shopInfoService.getShopInfoByPage(page, shopInfo));
    }

    /**
     * 获取商铺信息详情
     *
     * @param shopId 商铺ID
     * @return 结果
     */
    @GetMapping("/detail/{shopId}")
    public R detail(@PathVariable("shopId") Integer shopId) {
        return R.ok(shopInfoService.selectShopDetail(shopId));
    }

    /**
     * 修改商铺信息
     *
     * @param shopInfo 商铺信息
     * @return 结果
     */
    @PutMapping
    public R edit(ShopInfo shopInfo) {
        ShopInfo shopInfoBack = shopInfoService.getById(shopInfo.getId());
        UserInfo userInfo = userInfoService.getById(shopInfoBack.getUserId());
        userInfo.setUserName(shopInfo.getName());
        userInfo.setAvatar(shopInfo.getImages());
        userInfoService.updateById(userInfo);
        return R.ok(shopInfoService.updateById(shopInfo));
    }

    /**
     * 修改商铺信息
     *
     * @param shopInfo 商铺信息
     * @return 结果
     */
    @PostMapping
    public R add(ShopInfo shopInfo) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(shopInfo.getName());
        userInfo.setAvatar(shopInfo.getImages());
        userInfo.setCode("U-" + System.currentTimeMillis());
        userInfo.setType(2);
        userInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        userInfoService.save(userInfo);

        shopInfo.setCode("S-" + System.currentTimeMillis());
        shopInfo.setStatus("0");
        shopInfo.setUserId(userInfo.getId());


        userInfoService.updateById(userInfo);
        userService.registShop(shopInfo.getCode(),"1234qwer", shopInfo);
        return R.ok(true);
    }

    /**
     * 删除商铺信息
     *
     * @param ids 主键
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(shopInfoService.removeByIds(ids));
    }

}
