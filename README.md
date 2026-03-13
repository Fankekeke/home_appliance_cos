### 基于SpringBoot + Vue的家庭电器购买平台.

家用电器电商平台、家电在线商城、家庭电器零售平台

#### 商铺管理员功能模块介绍：
###### 商铺信息：维护家电店铺的基本资料，如名称、简介和营业状态。我的商品：管理本店上架的家用电器，支持编辑、上下架操作。商铺订单：处理用户下单、发货、退换货等全流程订单事务。订单评价：查看顾客对家电产品及服务的评分与文字反馈。消息回复：及时响应用户咨询、投诉或平台通知消息。商铺审核：提交资质材料，配合平台完成入驻或年审流程。用户沟通：与买家在线交流，解答产品功能、安装等问题。

#### 系统管理员功能模块介绍：
###### 收货地址管理：监管用户配送地址，确保订单准确送达。商铺审核管理：审核家电商家资质，控制平台准入质量。公告信息：发布促销活动、系统公告或家电安全提示。商品管理：审核并管理全平台家电商品信息与合规性。用户评价：监控和处理用户对商品、商铺的评论内容。消息管理：统一管理平台内各类通知、私信与提醒。订单管理：跟踪全平台订单状态，协调异常订单处理。贴子管理：审核社区论坛发帖，维护健康交流环境。商铺管理：维护所有家电商铺资料，支持推荐或冻结操作。用户管理：管理用户账号，处理违规行为或安全问题。商品类型：配置家电分类，如冰箱、洗衣机、空调、厨电等。

#### 用户功能模块介绍：
###### 商铺详情：浏览家电店铺信息、主营品牌、评分及服务承诺。查看公告：阅读平台或商家发布的优惠、售后等重要通知。搜索商品：按品牌、型号或功能快速查找所需家用电器。系统论坛：参与家电使用、选购、维修等话题讨论。发帖回复：在社区中发表经验分享或回复他人帖子。用户订单：查看家电购买记录、物流进度及售后服务。加入购物车：将心仪家电暂存购物车，方便比价与结算。收货地址管理：管理多个收货地址，适配不同配送需求。订单评价：对已购家电进行使用体验评分与文字评价。联系客服：通过在线方式咨询产品、订单或安装问题。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios
报表：Spread.js

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

#### 商铺管理员
商铺信息、我的商品、商铺订单、订单评价、消息回复、商铺审核、用户沟通

#### 系统管理员
收货地址管理、商铺审核管理、公告信息、商品管理、用户评价、消息管理、订单管理、贴子管理、商铺管理、用户管理、商品类型

#### 用户
商铺详情、查看公告、搜索商品、系统论坛、发帖回复、用户订单、加入购物车、收货地址管理、订单评价、联系客服


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[商家]
test1
1234qwer

[用户]
小程序登录

#### 项目截图

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733549748575.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733549731145.png) | 
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550223773.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550336212.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550211655.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550298972.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733549791031.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550291987.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733549760604.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550257701.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550234984.png) |  |

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550585193.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550528985.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550572824.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550521611.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550563720.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550513448.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550546591.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550500291.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1733550537839.png) |  |


#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png)

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
