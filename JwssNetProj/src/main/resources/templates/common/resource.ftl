<#macro Base>
    <!doctype html>
    <html lang="zh">
    <head>
        <meta charset="UTF-8" name="referrer" content="no-referrer">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>${baseTitle}-<#if title??>${title}</#if></title>
    </head>
    <body> <#nested> </body>
    </html>
</#macro>

<#--头部-->
<#macro Head>
    <link rel="stylesheet" href="/public/css/resource/head.css">
    <div class="jw-head-out">
        <ul class="jw-head-inLeft">
            <#list menuList as menu>
                <#if menu.colType=='1'>
                    <li><a href="${menu.colUrl}">${menu.colName}</a></li>
                </#if>
            </#list>
        </ul>
        <ul class="jw-head-inRight">
            <#if user??>
                <img src="${user.avatar}" style="width: 36px;border-radius: 16px;">
                <li><a href="${'/' + user.account}">${user.name}</a></li>
                <#if user.roles=='admin'>
                    <li><a href="/jwss/admin/">后台管理</a></li>
                </#if>
            </#if>

            <#list menuList as menu>
                <#if menu.colType=='0'>
                <#-- 如果登录了就不渲染登录/注册菜单 -->
                    <#if isLogin==menu.colState>
                        <li><a href="${menu.colUrl}">${menu.colName}</a></li>
                    </#if>
                </#if>
            </#list>
        </ul>
    </div>
</#macro>

<#--内容-->
<#macro Body>
    <#nested>
</#macro>

<#--底部-->
<#macro Foot>
    <div>底部</div>
</#macro>

<#--reactjs-->
<#macro ReactJs>
    <script src="https://unpkg.com/react@16/umd/react.development.js"></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
    <script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
</#macro>

<#--jquery boostrop框架-->
<#macro JqBootStroop>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript" src="http://lib.sinaapp.com/js/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://lib.sinaapp.com/js/bootstrap/4.3.1/css/bootstrap.min.css">
</#macro>

<#macro JqueryLayui>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript" src="/public/plus/layui/layui.js"></script>
    <link rel="stylesheet" href="/public/plus/layui/css/layui.css">
</#macro>

<#macro JqueryLabel>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link rel="stylesheet" href="http://res.vjwss.top/lib/jquerytag/dist/jquery.tagsinput-revisited.min.css">
    <script type="text/javascript"
            src="http://res.vjwss.top/lib/jquerytag/dist/jquery.tagsinput-revisited.min.js"></script>
</#macro>
