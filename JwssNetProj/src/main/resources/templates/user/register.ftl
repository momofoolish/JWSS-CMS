<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>

<@macros.Base>
    <@macros.JqBootStroop></@macros.JqBootStroop>
    <@macros.ReactJs></@macros.ReactJs>

<#--自定义React组件-->
    <@coms.Button></@coms.Button>
    <@coms.Input></@coms.Input>

    <script src="https://cdn.bootcdn.net/ajax/libs/blueimp-md5/2.18.0/js/md5.min.js"></script>

    <link rel="stylesheet" href="/public/css/register.css">
    <script type="text/babel" src="/public/js/view/register.js"></script>
    <script type="text/babel">
        ReactDOM.render(
            <Register/>,
            document.getElementById('registerRoot')
        );
    </script>

    <@macros.Body>
        <div id="registerRoot" class="container registerBg align-middle"></div>
    </@macros.Body>
</@macros.Base>
