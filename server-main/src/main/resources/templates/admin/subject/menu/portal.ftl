<#import '../../../common/resource.ftl' as macros>

<#--引入layui框架-->
<@macros.JqueryLayui></@macros.JqueryLayui>

<#-- 解决选项框不居中问题 -->
<style>
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
        top: 50%;
        transform: translateY(-50%);
    }
</style>

<#--  筛选工具  -->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="allM">查看所有</button>
        <button class="layui-btn layui-btn-sm" lay-event="leftM">左侧菜单</button>
        <button class="layui-btn layui-btn-sm" lay-event="rightM">右侧菜单</button>
        <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="addM">新增菜单</button>
    </div>
</script>

<#--  操作按钮  -->
<script type="text/html" id="barUser">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div class="layui-row">
    <div class="layui-col-md6">
        <input id="searchInput" type="text" name="title" required lay-verify="required"
               placeholder="输入搜索" autocomplete="off" class="layui-input">
    </div>

    <div class="layui-col-md3">
        <button type="button" style="margin-left: 1px" id="searchBtn"
                class="layui-btn layui-btn-primary">搜索
        </button>
    </div>
</div>

<#-- 表格 -->
<table class="layui-table" lay-data="{url:'/api/admin/menu/portal/queryByPage',id: 'optionSelects',
        page: true, toolbar: '#toolbar'}" lay-filter="option">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox'}">ID</th>
        <th lay-data="{field:'id', width:'5%'}">ID</th>
        <th lay-data="{field:'col_name', width:'15%', edit: 'text'}">名称</th>
        <th lay-data="{field:'col_type', edit: 'text', sort: true, width: '15%'}">类型</th>
        <th lay-data="{field:'col_state', width:'15%', sort: true, edit: 'text'}">权重</th>
        <th lay-data="{field:'col_url', edit: 'text', width: '30%'}">路径</th>
        <th lay-data="{field:'right',width: '15%',toolbar: '#barUser'}">操作</th>
    </tr>
    </thead>
</table>

<script src="/public/js/admin/menu/portal.js"></script>

