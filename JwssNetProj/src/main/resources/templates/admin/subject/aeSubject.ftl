<#macro Content>
<#-- 解决选项框不居中问题 -->
    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
            top: 50%;
            transform: translateY(-50%);
        }
    </style>

    <div class="layui-row">
        <div class="layui-col-md6">
            <input id="searchInput" type="text" name="title" required lay-verify="required"
                   placeholder="输入搜索" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-col-md3">
            <button type="button" style="margin-left: 1px" id="searchBtn"
                    class="layui-btn layui-btn-primary">搜索</button>
        </div>
    </div>

    <table id="articleAdminTable" lay-filter="option"></table>

    <script src="/public/js/admin/aeSubject.js"></script>
</#macro>
