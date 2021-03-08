let baseTable = "";

window.onload = function () {
    initDataTable();
    searchByClick();
    searchByEnter();
}

//表格重载
function tableReload(obj) {
    baseTable.reload({
        where: {
            title: obj.title === undefined ? "" : obj.title,
            roles: (obj.roles === undefined || obj.roles === "") ? "" : obj.roles,
        }
    });
}

//初始化表格
function initDataTable() {
    layui.use('table', function () {
        let table = layui.table;
        baseTable = table.render({
            elem: '#userAdminTable'
            , id: 'optionSelects'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , limit: 15
            , url: '/api/admin/user/page' //数据接口
            , page: true //开启分页
            , cols: [[
                {field: 'id', type: 'checkbox', width: '5%', align: 'center', fixed: 'left'}
                , {field: 'name', title: '用户名', width: '10%'}
                , {field: 'account', title: '账号', width: '15%'}
                , {field: 'password', title: '密码', width: '15%'}
                , {field: 'mail', title: '邮箱', width: '15%'}
                , {field: 'roles', title: '角色', width: '10%', sort: true}
                , {field: 'registerDate', title: '注册日期', width: '15%', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barUser', width: '15%'}
            ]]
        });

        //批量删除按钮
        $("#deleteBtn").on('click', function () {
            let checkStatus = table.checkStatus('optionSelects');
            let ids = [];
            checkStatus.data.forEach((item, index) => {
                ids.push(item.id);
            });
            console.log(ids);
        });

        //头工具栏事件
        table.on('toolbar(option)', function(obj){
            let o = {};
            switch(obj.event){
                case 'userE':
                    o.roles = "user";
                    break;
                case 'authorE':
                    o.roles = "author";
                    break;
                case 'adminE':
                    o.roles = "admin";
                    break;
                case 'allE':
                    o.roles = "";
                    break;
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');//自定义头工具栏右侧图标 - 提示
                    break;
            }
            tableReload(o);
        });

        //监听行工具事件
        table.on('tool(option)', function (obj) {
            let data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除该用户么?', function (index) {
                    //删除单个用户操作
                    $.ajax({
                        url: '/api/admin/user/delete',
                        type: 'POST',
                        data: data,
                        success: function (xhr) {
                            if (xhr.code === 1) {
                                obj.del();
                                layer.close(index);
                            } else {
                                layer.msg("删除失败！");
                            }
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , title: '修改用户角色'
                    , value: data.roles
                }, function (value, index) {
                    let req = data;
                    req.roles = value;
                    $.ajax({
                        url: '/api/admin/user/updateRoles',
                        type: 'POST',
                        data: req,
                        success: function (xhr) {
                            if (xhr.code === 1) {
                                obj.update({roles: value});
                                layer.close(index);
                            } else {
                                layer.msg('更新失败！');
                            }
                        }
                    });
                });
            }
        });

    });
}

//单击搜索功能
function searchByClick() {
    $("#searchBtn").on('click', function () {
        let s = $("#searchInput").val();
        let obj = {};
        obj.title = s;
        tableReload(obj);
    });
}

//回车键搜索
function searchByEnter() {
    $("#searchInput").on('keydown', function (e) {
        if (e.keyCode === 13) {
            let s = $("#searchInput").val();
            let obj = {};
            obj.name = s;
            tableReload(obj);
        }
    });
}
