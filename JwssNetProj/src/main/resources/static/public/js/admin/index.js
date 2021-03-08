const tabFilter = "myTab";  //选项卡ID

window.onload = function () {
    layui.use('element', function () {
        let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(leftCard)', function (elem) {
            let tabName = elem[0].innerText;
            //判断是否有span标签，有则认为是下拉
            if ("文章管理" === tabName) {
                return;
            }else if("用户管理" === tabName){
                return;
            }
            tabAddHandler(element, tabName, tabName);
            element.tabChange(tabFilter, tabName);
        });

        element.render('nav');
    });
}

//添加选项卡
function tabAddHandler(element, tabName, id) {
    let iframe = "/404";

    if("默认首页" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/default'></iframe>";
    }else if("管理员操作" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/article/operation'></iframe>";
    }else if("审核列表" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/article/examine'></iframe>";
    }else if("网站用户" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/user/operation'></iframe>";
    }else if("作者申请" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/user/examine'></iframe>";
    }else if("访问统计" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/count'></iframe>";
    }else if("数据管理" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/dataManager'></iframe>";
    }else if("版本管理" === tabName){
        iframe = "<iframe class='iframe-content' src='/jwss/admin/version'></iframe>";
    }

    element.tabAdd(tabFilter, {
        title: tabName
        , content: iframe
        , id: id
    });
}
