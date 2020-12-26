//表格基础配置
const baseOption = {
    elem: '#articleAdminTable'
    , id: 'optionSelects'
    , height: 312
    , limit: 15
    , url: '/api/admin/article/examineList' //数据接口
    , page: true //开启分页
    , cols: [[ //表头
        {field: 'id', type: 'checkbox', title: 'id', width: '5%'}
        , {field: 'title', title: '标题', width: '20%'}
        , {field: 'authorId', title: '作者ID', width: '10%'}
        , {field: 'label', title: '标签', width: '20%'}
        , {field: 'description', title: '内容描述', width: '20%'}
        , {field: 'content', title: '文章内容', width: '15%'}
        , {field: 'createDate', title: '创建日期', width: '10%', sort: true}
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
            type: 1,
            title: '文章内容',
            shadeClose: true,
            offset: 'auto',
            area: 'auto',
            maxWidth: 800,
            maxHeight: 480,
            content: content.content + '',
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
        if(e.keyCode === 13){
            let s = $("#searchInput").val();
            let obj = {};
            obj.title = s;
            tableReload(obj);
        }
    });
}
