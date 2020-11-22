<#macro Base>
    <!doctype html>
    <html lang="zh">
    <head>
        <meta charset="UTF-8" name="referrer" content="no-referrer">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>${baseTitle}-<#if title??>${title}</#if></title>
        <link rel="stylesheet" href="/public/css/resource/head.css">
    </head>
    <body> <#nested> </body>
    </html>
</#macro>

<#--头部-->
<#macro Head>
    <div class="jw-head-out">
        <ul class="jw-head-inLeft">
            <#list menuList as menu>
                <#if menu.colType=='1'>
                    <li><a href="${menu.colUrl}">${menu.colName}</a></li>
                </#if>
            </#list>
        </ul>
        <ul class="jw-head-inRight">
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
    <div><#nested></div>
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
    <link rel="stylesheet" href="http://lib.sinaapp.com/js/bootstrap/4.3.1/css/bootstrap.min.css">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://lib.sinaapp.com/js/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</#macro>

<#macro JqueryLabel>
    <link rel="stylesheet" href="http://res.vjwss.top/lib/jquerytag/dist/jquery.tagsinput-revisited.min.css">
    <script type="text/javascript"
            src="http://res.vjwss.top/lib/jquerytag/dist/jquery.tagsinput-revisited.min.js"></script>
    <#nested>
</#macro>
