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
    <div id="headOut">
        <ul id="headInLeft">
            <#list menuList as menu>
                <#if menu.col_type==0>
                    <li><a href="${menu.col_url}">${menu.col_name}</a></li>
                </#if>
            </#list>
        </ul>
        <ul id="headInRight">
            <#if user??>
                <li><a href="javascript:"><img alt="${user.name}" src="${user.avatar}"></a></li>
            </#if>

            <#list menuList as menu>
                <#if menu.col_type!=0>
                    <li><a href="${menu.col_url}">${menu.col_name}</a></li>
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
