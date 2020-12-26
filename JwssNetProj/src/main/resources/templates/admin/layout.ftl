<#--布局容器-->
<#macro Contianer>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>layout 后台大布局 - Layui</title>
    </head>
    <body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <#nested>
    </div>
    </body>
    </html>
</#macro>

<#--导航栏-->
<#macro Nav>
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>
</#macro>

<#--菜单-->
<#macro Menu>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="/jwss/admin/">默认首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">文章管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/jwss/admin/article/operation">管理员操作</a></dd>
                        <dd><a href="/jwss/admin/article/examine">审核列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">用户管理</a></li>
                <li class="layui-nav-item"><a href="">访问统计</a></li>
                <li class="layui-nav-item"><a href="">数据管理</a></li>
            </ul>
        </div>
    </div>
</#macro>

<#--主体-->
<#macro Body>
    <div class="layui-body">
        <div style="padding: 15px;"><#nested></div>
    </div>
</#macro>

<#--底部固定区域-->
<#macro Footer>
    <div class="layui-footer">
        © layui.com - 底部固定区域
    </div>
</#macro>
