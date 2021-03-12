<#import 'common/resource.ftl' as macros>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@macros.Body>
        <link rel="stylesheet" href="/public/css/door/index.css">

        <div class="jwss-box-out">
            <#--左侧有封面列表-->
            <div class="jwss-content-left">
                <ul>
                    <#list articleList as article>
                        <#if article.cover ??>
                            <li>
                                <a href="/mapper/article/detail?aid=${mapper.article.id}">
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
                            <li><a href="/mapper/article/detail?aid=${mapper.article.id}">${article.title}</a></li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>

    </@macros.Body>
    <@macros.Foot></@macros.Foot>
</@macros.Base>
