<%-- 进入页面的欢迎页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用SaaS-Export</title>
</head>
<body>
<h2>云服务介绍</h2>
<div style="color: darkgrey; margin-left: 20px; margin-right: 20px" id = "saas">
    <p>云服务是指通过网络以按需、易扩展的方式获得所需服务。这种服务可以是IT和软件、互联网相关，也可是其他服务。它意味着计算能力也可作为一种商品通过互联网进行流通。</p>
    <h4>云服务的三大分类</h4>
    <ur>
        <li>IaaS：Infrastructure as a Service ——基础设施即服务</li>
        <li>PaaS：Platform as a Service ——平台即服务</li>
        <li>SaaS：Software as a Service ——软件即服务</li>
    </ur>
    <p>主要作用</p>
    <ur>
        <li>IaaS – 为公司提供服务器硬件，网络，存储设备等 – 基础设施即服务</li>
        <li>PaaS - 为公司提供基础设施+软件平台 – 平台即服务</li>
        <li>Saas - 为公司提供基础设备+平台+软件（完整的一条龙服务） – 软件即服务</li>
    </ur>
    <p style="color: red">【就相当于】Iaas就是卖硬件，Paas就是卖开发的运行环境，Saas就是卖软件产品。</p>
</div>

<h3>SaaS介绍</h3>
<div style="color: darkgrey; margin-left: 20px; margin-right: 20px" id = "saas2">
    <p>SaaS是云服务模式之一，SaaS（Software as a Service）也就是【软件即服务】。<br/>
        IBM ，的软件架构师 Albert Barron 曾经使用披萨作为比喻，David Ng 进一步，让它变得更准确易懂。</p>
    <p style="background-color: aqua">请设想你是一个餐饮业者，打算做披萨生意厨房、炉子、煤气，你使用这些 — 基础设施即服务（IaaS），将配料洒在饼皮，让别人帮你烤出来就行了 – 平台即服务（PaaS），
        他人直接做好了披萨，到手的就是一个成品，你贴牌 – 软件即服务（SaaS）</p>
</div>

<div style="color: darkgrey; margin-left: 20px; margin-right: 20px" id = "export">
    <p>export是出口、输出的意思，Export系统是一种针对国际物流的进出口报关平台系统。主要用于国际物流。（就是针对国际物流的进出口报关平台）</p>
    <p>
        进出口业务的缺陷是：
        <ul>
            <li>有多个关联方，比如国际物流的业务闭环涉及到海关、船东、港区、仓库、拖车、报关行等等</li>
            <li>交易周期非常长（各种审核、报批，多部门审批等）</li>
            <li>支付结算体系受到限制</li>
        </ul>  
    </p>

    <p>
        Export系统对于进出口报送平台的作用<br/>
        <span>报关业务是非常复杂的（流程复杂），通过软件工具来建立不同关联方的链接，完成信息的传递，实现复杂业务的数字化（解决审批过程，文档丢失、遗漏等各种问题）<br/>
            Export系统服务于货代企业，通过提供SaaS工具，帮助中小企业简单便利的对国际物流全环节进行流程控。
        </span>
    </p>

    <p>一个Export项目主要模块
        <ul>
            <li>1.权限模块（用户，角色，权限，部门）</li>
            <li>2.货运模块（购销合同，货物，附件等）</li>
            <li>3.报运模块（报关，装箱）</li>
            <li>4.统计模块（财务）</li>
        </ul>
    </p>

</div>

<h2>SaaS - Export项目介绍（本项目）</h2>
<div style="color: darkgrey; margin-left: 20px; margin-right: 20px" id = "SaaS-Export">
    <h4>使用的技术</h4>
    <ul>
        <li>SSM（开发基础）</li>
        <li>Apache Shiro（权限框架）</li>
        <li>Apache Dubbo（分布式开发）</li>
        <li>RabbitMQ (消息队列)</li>
        <li>Jasper Report (PDF报表)</li>
        <li>POI（Excel报表）</li>
    </ul>
    <h4>开发环境</h4>
    <p>JDK、MySQL、Idea、Maven</p>
    <h4>项目技术架构</h4>
    <p>后端架构采用：Spring +SpringMVC+mybatis +Dubbo（分布式开发）<br/>
        前端采用AdminLTE框架的前端解决方案</p>
</div>

<div style="color: darkgrey; margin-left: 20px; margin-right: 20px" id = "foot">
    <h2>本文来自博客</h2>
    <a href="https://striveday.blog.csdn.net/article/details/109218019" title="博客介绍">SaaS介绍，Export系统介绍</a>
</div>

</body>
</html>
