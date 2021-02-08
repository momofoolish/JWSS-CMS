<#macro Content>
<#-- 解决选项框不居中问题 -->
    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
            top: 50%;
            transform: translateY(-50%);
        }
    </style>

<#--  操作按钮  -->
    <script type="text/html" id="barUser">
        <a class="layui-btn layui-btn-xs" lay-event="pass">通过</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="noPass">退回</a>
    </script>

    <div class="layui-row">
        <div class="layui-col-md6">
            <input type="text" name="title" required lay-verify="required" id="searchInput"
                   placeholder="输入搜索" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-col-md3">
            <button type="button" style="margin-left: 1px" id="searchBtn"
                    class="layui-btn layui-btn-primary">搜索
            </button>
        </div>
    </div>

<#--  筛选工具  -->
    <script type="text/html" id="toolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="allE">查看所有</button>
            <button class="layui-btn layui-btn-sm" lay-event="backE">退回流程</button>
            <button class="layui-btn layui-btn-sm" lay-event="passE">通过流程</button>
            <button class="layui-btn layui-btn-sm" lay-event="examineE">审批流程</button>
        </div>
    </script>

    <table id="userAdminTable" lay-filter="option"></table>

    <script src="/public/js/admin/ueSubject.js"></script>
</#macro>
