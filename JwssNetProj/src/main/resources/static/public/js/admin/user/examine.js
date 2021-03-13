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
            examineType: obj.examineType === undefined ? "" : obj.examineType,
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
            , url: '/api/admin/flowBase/queryByPage' //数据接口
            , page: true //开启分页
            , cols: [[
                {field: 'id', type: 'checkbox', width: '5%', align: 'center', fixed: 'left'}
                , {field: 'title', title: '流程标题', width: '10%'}
                , {field: 'content', title: '内容', width: '15%'}
                , {field: 'opinion', title: '意见', width: '15%'}
                , {
                    field: 'audit_state', title: '当前状态', width: '10%', templet: function (v) {
                        switch (v.audit_state) {
                            case 0:
                                return "待审批";
                            case 1:
                                return "已通过";
                            case 2:
                                return "已退回";
                            case 3:
                                return "已归档";
                        }
                    }
                }
                , {field: 'flow_id', title: '流程ID', width: '10%'}
                , {field: 'current_operator', title: '当前操作人', width: '10%'}
                , {field: 'create_date', title: '创建时间', width: '10%', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barUser', width: '15%'}
            ]]
        });

        //监听行工具事件
        table.on('tool(option)', function (obj) {
            let data = obj.data;
            if (obj.event === 'pass') {
                console.log("---------------");
                layer.confirm('确定提交?', function (index) {
                    updateFlowBase(data.id, 1, index);
                });
            } else if (obj.event === 'noPass') {
                layer.confirm('确定退回？', function (index) {
                    updateFlowBase(data.id, 2, index);
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(option)', function (obj) {
            let o = {};
            switch (obj.event) {
                case 'allE':
                    o.examineType = -1;
                    break;
                case 'backE':
                    o.examineType = 2;
                    break;
                case 'passE':
                    o.examineType = 1;
                    break;
                case 'examineE':
                    o.examineType = 0;
                    break;
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');//自定义头工具栏右侧图标 - 提示
                    break;
            }
            tableReload(o);
        });
    });
}

//更新流程状态
function updateFlowBase(id, examineType, index) {
    let obj = {};
    obj.id = id;
    obj.examineType = examineType;
    $.ajax({
        url: '/api/admin/flowBase/update',
        type: 'POST',
        data: obj,
        success: function (xhr) {
            if (xhr.code === 1) {
                tableReload("");
                layer.close(index);
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
            obj.title = s;
            tableReload(obj);
        }
    });
}
