<#import '../common/resource.ftl' as macros>
<#import './subject/aoSubject.ftl' as aos>
<#import './layout.ftl' as layout>

<#--引入layui框架-->
<@macros.JqueryLayui></@macros.JqueryLayui>

<#--布局-->
<@layout.Contianer>
    <@layout.Nav></@layout.Nav>
    <@layout.Menu></@layout.Menu>
    <@layout.Body>
        <@aos.Content></@aos.Content>
    </@layout.Body>
    <@layout.Footer></@layout.Footer>
</@layout.Contianer>
