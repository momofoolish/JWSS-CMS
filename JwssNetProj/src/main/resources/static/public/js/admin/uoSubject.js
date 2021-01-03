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
            , height: 312
            , limit: 15
            , url: '/api/admin/user/page' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
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

        //监听行工具事件
        table.on('tool(userAdminTable)', function (obj) {
            let data = obj.data;
            console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除该用户么?', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , value: data.email
                }, function (value, index) {
                    obj.update({
                        email: value
                    });
                    layer.close(index);
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
            obj.title = s;
            tableReload(obj);
        }
    });
}
