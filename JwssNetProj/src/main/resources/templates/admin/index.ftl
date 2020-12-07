<#import '../common/resource.ftl' as macros>
<#import './subject/default.ftl' as defa>
<#import './layout.ftl' as layout>

<#--引入layui框架-->
<@macros.JqueryLayui></@macros.JqueryLayui>

<#--布局-->
<@layout.Contianer>
    <@layout.Nav></@layout.Nav>
    <@layout.Menu></@layout.Menu>
    <@layout.Body> <@defa.Index></@defa.Index> </@layout.Body>
    <@layout.Footer></@layout.Footer>
</@layout.Contianer>

<#--JavaScript脚本执行-->
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
