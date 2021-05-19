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
            col_name: obj.col_name === undefined ? "" : obj.col_name,
            col_type: obj.col_type === undefined ? "" : obj.col_type,
        }
    });
}

//初始化表格
function initDataTable() {
    layui.use('table', function () {
        let table = layui.table;
        baseTable = table;

        //监听行工具事件
        table.on('tool(option)', function (obj) {
            if (obj.event === 'del') {
                layer.confirm('确定删除?', function (index) {

                });
            }
        });

        //监听单元格编辑
        table.on('edit(option)', function (obj) {
            let menu = {};
            menu.id = obj.data.id;
            menu[obj.field] = obj.value;
            $.ajax({
                url: '/api/admin/menu/portal/update',
                type: 'POST',
                data: JSON.stringify(menu),
                contentType: 'application/json',
                success: function (xhr) {
                    if (xhr.code === 1) {
                        layer.msg("修改成功！");
                    } else {
                        layer.msg("修改失败！");
                    }
                }
            });
        });

        //头工具栏事件
        table.on('toolbar(option)', function (obj) {
            let o = {};
            switch (obj.event) {
                case 'allM':
                    o.col_type = -1;
                    break;
                case 'leftM':
                    o.col_type = 2;
                    break;
                case 'rightM':
                    o.col_type = 1;
                    break;
                case 'addM':
                    layer.open("新增窗口");
                    break;
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');//自定义头工具栏右侧图标 - 提示
                    break;
            }
            tableReload(o);
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
