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
            <input type="text" name="title" required lay-verify="required"
                   placeholder="输入搜索" autocomplete="off" class="layui-input">

        </div>
        <div class="layui-col-md3">
            <button type="button" style="margin-left: 1px" class="layui-btn layui-btn-primary">搜索</button>
        </div>
        <div class="layui-col-md3" style="text-align: right">
            <button type="button" class="layui-btn layui-btn-normal">删除</button>
        </div>
    </div>
    <table id="articleAdminTable"></table>

    <script>
        layui.use('table', function () {
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#articleAdminTable'
                , height: 312
                , limit: 15
                , url: '/api/admin/page' //数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', type: 'checkbox', width: '5%', align: 'center', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: '10%',}
                    , {field: 'title', title: '标题', width: '20%'}
                    , {field: 'authorId', title: '作者ID', width: '10%'}
                    , {field: 'description', title: '内容描述', width: '20%'}
                    , {field: 'label', title: '标签', width: '15%'}
                    , {field: 'alterDate', title: '修改日期', width: '10%', sort: true}
                    , {field: 'createDate', title: '创建日期', width: '10%', sort: true}
                ]]
            });

        });
    </script>
</#macro>
