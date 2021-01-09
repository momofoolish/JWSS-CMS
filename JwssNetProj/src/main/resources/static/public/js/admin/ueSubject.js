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
            , height: 312
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

        //监听行工具事件
        table.on('tool(option)', function (obj) {
            let data = obj.data;
            if (obj.event === 'pass') {
                layer.confirm('确定通过?', function (index) {
                    alterUserPower(data.id, 1);
                });
            } else if (obj.event === 'noPass') {
                layer.confirm('确定退回？', function (value, index) {
                    alterUserPower(data.id, 0);
                });
            }
        });

    });
}

//修改用户权限
function alterUserPower(id, examineType) {
    let obj = {};
    obj.id = id;
    obj.examineType = examineType;
    $.ajax({
        url: '/api/admin/user/alterPower',
        type: 'POST',
        data: obj,
        success: function (xhr) {
            if (xhr.code === 1) {
                obj.roles = "user";
                tableReload(obj);
            } else {
                layer.msg("操作失败！");
            }
        }
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
