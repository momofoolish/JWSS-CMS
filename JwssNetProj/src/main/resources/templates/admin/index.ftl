<#import '../common/resource.ftl' as macros>
<#--引入layui框架-->
<@macros.JqueryLayui></@macros.JqueryLayui>

<#--布局-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script type="text/javascript" src="/public/js/admin/index.js"></script>
    <link rel="stylesheet" href="/public/css/admin/index.css">

    <title>JWSS 内容分发系统</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <#--导航栏-->
    <div class="layui-header">
        <div class="layui-logo">JWSS 后台管理</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/">前端用户中心</a></li>
            <li class="layui-nav-item"><a href="/author/editor">文章编写</a></li>
            <li class="layui-nav-item">
                <a href="javascript:">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:">
                    <img src="${admin.avatar}" class="layui-nav-img">
                    ${admin.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">注销</a></li>
        </ul>
    </div>

    <#--菜单-->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="leftCard">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="layui-this" href="javascript:">默认首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">文章管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:">管理员操作</a></dd>
                        <dd><a href="javascript:">审核列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:">网站用户</a></dd>
                        <dd><a href="javascript:">作者申请</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:">访问统计</a></li>
                <li class="layui-nav-item"><a href="javascript:">数据管理</a></li>
                <li class="layui-nav-item"><a href="javascript:">版本管理</a></li>
            </ul>
        </div>
    </div>

    <#--主体-->
    <div class="layui-body subject-div">
        <div class="layui-tab layui-tab-card my-tab" lay-allowclose="true" lay-filter="myTab">
            <ul class="layui-tab-title">
                <li class="layui-this">网站首页<i class="layui-icon layui-unselect layui-tab-close">ဆ</i></li>
            </ul>
            <div class="layui-tab-content" style="height: 100%;">
                <div class="layui-tab-item layui-show">
                    <iframe class='iframe-content' src='/jwss/admin/default'></iframe>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
