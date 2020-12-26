let baseTable = "";

window.onload = function () {
    layui.use('table', function () {
        let table = layui.table;
        baseTable = table.render({
            elem: '#articleAdminTable'
            , id: 'optionSelects'
            , height: 312
            , limit: 15
            , url: '/api/admin/article/page' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', type: 'checkbox', width: '5%', align: 'center', fixed: 'left'}
                , {field: 'title', title: '标题', width: '20%'}
                , {field: 'authorId', title: '作者ID', width: '15%'}
                , {field: 'description', title: '内容描述', width: '20%'}
                , {field: 'label', title: '标签', width: '20%'}
                , {field: 'alterDate', title: '修改日期', width: '10%', sort: true}
                , {field: 'createDate', title: '创建日期', width: '10%', sort: true}
            ]]
        });

        table.on('checkbox(option)', function (obj) {
            // console.log(obj.checked); //当前是否选中状态
            // console.log(obj.data); //选中行的相关数据
            // console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        $("#deleteBtn").on('click', function () {
            let checkStatus = table.checkStatus('optionSelects');
            let ids = [];
            checkStatus.data.forEach((item, index) => {
                ids.push(item.id);
            });
            console.log(ids);
        });

    });
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
