//表格基础配置
const baseOption = {
    elem: '#articleAdminTable'
    , id: 'optionSelects'
    , limit: 15
    , url: '/api/admin/article/examineList' //数据接口
    , page: true //开启分页
    , cols: [[ //表头
        {field: 'id', width: '5%', hide: true}
        , {field: 'title', title: '标题', width: '20%'}
        , {field: 'author', title: '作者', width: '15%'}
        , {field: 'sort', title: '分类', width: '20%'}
        , {field: 'label', title: '标签', width: '20%'}
        , {field: 'alter_date', title: '修改日期', width: '15%'}
        , {field: 'create_date', title: '创建日期', width: '10%', sort: true}
    ]]
}

let baseTable = "";

//JS方法入口
window.onload = function () {
    layui.use('table', function () {
        let table = layui.table;
        baseTable = table.render(baseOption);
        //监听行单击事件
        table.on('row(option)', function (obj) {
            openLayer(obj.data);
        });
    });
    searchByClick();
    searchByEnter();
}

//开启弹窗
function openLayer(content) {
    layui.use('layer', function () {
        let layer = layui.layer;
        layer.open({
            type: 2,
            title: '文章内容',
            shadeClose: true,
            offset: 'auto',
            area: ['80%', '86%'],
            content: ['/article/detail?aid=' + content.id],
            btn: ['提交', '退回'],
            btn1: function (index, layero) {
                updateState(content.id, 1);
                return false;
            },
            btn2: function (index, layero) {
                updateState(content.id, 2);
                return false;
            }
        });
    });
}

//提示弹窗
function tipLayer(s) {
    layui.use('layer', function () {
        let layer = layui.layer;
        layer.open({
            type: 0,
            title: '提示',
            content: s + '',
            yes: function () {
                //关闭所有弹窗
                layer.closeAll();
                return true;
            }
        });
    });
}

//发送更新请求
function updateState(aId, state) {
    let formData = {
        aId: aId,
        state: state
    }
    $.ajax({
        url: "/api/admin/article/updateState",
        method: 'post',
        data: formData,
        success: function (resp) {
            if (resp.code === 1) {
                //操作成功
                tableReload("");
                tipLayer("操作成功");
            } else {
                //更新失败
                tipLayer("更新失败");
            }
        }
    });
}

//表格重载
function tableReload(obj) {
    baseTable.reload({
        where: {
            title: obj.title === undefined ? "" : obj.title,
        }
    });
}

//搜索功能
function searchByClick() {
    $("#searchBtn").on('click', function () {
        let s = $("#searchInput").val();
        let obj = {};
        obj.title = s;
        tableReload(obj);
    });
}

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
