<#import 'common/resource.ftl' as macros>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@macros.Body>
        <link rel="stylesheet" href="/public/css/portal/index.css">

        <div class="jwss-box-out">
            <#--左侧有封面列表-->
            <div class="jwss-content-left">
                <ul>
                    <#list articleList as article>
                        <#if article.cover ??>
                            <li>
                                <a href="/article/detail?aid=${article.id}">
                                    <img src="${article.cover}" alt="${article.title}">
                                    <span>${article.title}</span>
                                </a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
            <#--右侧无封面文章列表-->
            <div class="jwss-content-right">
                <ul>
                    <#list articleList as article>
                        <#if article.cover ??>
                        <#else>
                            <li><a href="/article/detail?aid=${article.id}">${article.title}</a></li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>

    </@macros.Body>
    <@macros.Foot></@macros.Foot>
</@macros.Base>
