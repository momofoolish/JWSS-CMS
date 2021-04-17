<#macro Base>
    <!doctype html>
    <html lang="zh">
    <head>
        <meta charset="UTF-8" name="referrer" content="no-referrer">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>${baseTitle}-<#if title??>${title}</#if></title>
        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
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
                <#if menu.col_type==1>
                    <li><a href="${menu.col_url}">${menu.col_name}</a></li>
                </#if>
            </#list>
        </ul>
        <ul class="jw-head-inRight">
            <#if user??>
                <li><a href="${'/' + user.account}"><img src="${user.avatar}"></a></li>
                <#if user.roles=='admin'>
                    <li><a href="/jwss/admin/">后台管理</a></li>
                </#if>
            </#if>

            <#list menuList as menu>
                <#if menu.col_type==0>
                <#-- 如果登录了就不渲染登录/注册菜单 -->
                    <#if isLogin==menu.col_state>
                        <li><a href="${menu.col_url}">${menu.col_name}</a></li>
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
    <div style="text-align: center;">底部</div>
</#macro>

<#macro JqueryLayui>
    <script src="/public/plus/jquery/jquery.js"></script>
    <script type="text/javascript" src="/public/plus/layui/layui.js"></script>
    <link rel="stylesheet" href="/public/plus/layui/css/layui.css">
</#macro>
