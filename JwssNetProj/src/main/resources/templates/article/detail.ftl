<#import '../common/resource.ftl' as macros>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@macros.Body>
        <link rel="stylesheet" href="/public/css/article/detail.css">

        <div class="detail-box-out">
            <h1>${article.title}</h1>
            <p>${article.content}</p>
        </div>

    </@macros.Body>
    <@macros.Foot></@macros.Foot>
</@macros.Base>
