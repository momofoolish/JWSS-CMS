let editor = null;

window.onload = function () {
    let content;    //文章内容
    let cover;      //封面

    //创建文本编辑器
    editor = new wangEditor('#editorDiv');
    editor.config.zIndex = 1;
    editor.config.onchangeTimeout = 500;
    editor.config.uploadImgMaxSize = 3 * 1024 * 1024;
    editor.config.uploadFileName = "articleMedia";
    editor.config.uploadImgServer = "/api/file/upload/articleMedia";
    editor.config.onchange = function (html) {
        content = html;
    }
    editor.create();

    //提交
    $("#submitBtn").on('click', function () {
        let inputTitle = $("#inputTitle").val();
        let selectSort = $("#selectSort").val();
        let tipText = $("#tipText");
        //判断内容是否为空
        if (inputTitle.length < 4 || inputTitle.length > 32) {
            let tip = "<div class=\"alert alert-warning\" role=\"alert\">\n" +
                "标题长度不对\n" +
                "</div>";
            tipText.html(tip);
            return;
        }
        if (content === undefined || content.length < 32) {
            let tip = "<div class=\"alert alert-warning\" role=\"alert\">\n" +
                "内容过少, 至少16个字\n" +
                "</div>";
            tipText.html(tip);
            return;
        }
        if (selectSort === "分类") {
            let tip = "<div class=\"alert alert-warning\" role=\"alert\">\n" +
                "请选择分类\n" +
                "</div>";
            tipText.html(tip);
            return;
        }
        //表单数据
        let formData = new FormData();
        formData.append("edKey", encryptConst);
        formData.append("sort", selectSort);
        formData.append("title", inputTitle);
        formData.append("content", content);
        formData.append("cover", cover === undefined ? "" : cover);
        //显示上传中
        let tip = "<div class=\"alert alert-primary\" role=\"alert\">\n" +
            "提交中...\n" +
            "</div>";
        tipText.html(tip);
        //异步请求
        setTimeout(() => {
            $.ajax({
                url: '/api/article/author/add',
                method: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.code === 1) {
                        tip = "<div class=\"alert alert-success\" role=\"alert\">\n" +
                            response.content + "\n" +
                            "</div>";
                    } else {
                        tip = "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            response.content + "\n" +
                            "</div>";
                    }
                    tipText.html(tip);
                }
            });
        }, 800);
    });

    //保存
    $("#saveBtn").on('click', function () {

    });

    //预览
    $("#previewBtn").on('click', function () {
        $("#previewText").html(content);
    });

    //图片上传
    $("#uploadInput").on('change', function (event) {
        compressImage(event.target.files[0], function (file) {
            let img = document.createElement("img");
            img.src = URL.createObjectURL(file);
            img.width = 200;
            $("#coverPre").html(img);
            cover = file;
        });
    });

    render();
}

// 压缩图片
const compressImage = (file, success) => {
    const name = file.name; //文件名
    const reader = new FileReader();
    // 判断是否超过1M
    if (file.size < 1024 * 1024) {
        success(file);
        return;
    }
    if (file.size > 1024 * 1024 * 3) {
        alert("文件不能超过3M")
        return;
    }
    reader.readAsDataURL(file);
    reader.onload = e => {
        const src = e.target.result;
        const img = new Image();

        img.src = src;
        img.onload = e => {
            const w = img.width;
            const h = img.height;
            const quality = 0.6;  // 默认图片质量为0.92
            // 生成canvas
            const canvas = document.createElement('canvas');
            const ctx = canvas.getContext('2d');
            // 创建属性节点
            const anw = document.createAttribute("width");
            anw.nodeValue = w;
            const anh = document.createAttribute("height");
            anh.nodeValue = h;
            canvas.setAttributeNode(anw);
            canvas.setAttributeNode(anh);

            // 铺底色 PNG转JPEG时透明区域会变黑色
            ctx.fillStyle = "#fff";
            ctx.fillRect(0, 0, w, h);

            ctx.drawImage(img, 0, 0, w, h);
            // quality值越小，所绘制出的图像越模糊
            const base64 = canvas.toDataURL('image/jpeg', quality); // 图片格式jpeg或webp可以选0-1质量区间
            // 去掉url的头，并转换为byte
            const bytes = window.atob(base64.split(',')[1]);
            // 处理异常,将ascii码小于0的转换为大于0
            const ab = new ArrayBuffer(bytes.length);
            const ia = new Uint8Array(ab);
            for (let i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }
            file = new Blob([ab], {type: 'image/jpeg'});
            file.name = name;
            success(file);
        }
        img.onerror = e => {
            console.log(e)
        }
    }
    // 错误处理
    reader.onerror = e => {
        console.log(e)
    }
}

//添加左侧选项卡
const addArticleTab = (id, title, createDate) => {
    return "    <li class=\"list-group-item\">\n" +
        "        <a class=\"list-group-item list-group-item-action\" data-toggle=\"list\"\n" +
        "           href=\"javascript:\" role=\"tab\" aria-controls=\"home\">\n" +
        "            <h5>" + title + "</h5>\n" +
        "            <span>\n" +
        "            " + createDate + "\n" +
        "            </span>\n" +
        "            <span id=\"hiddenId\" style=\"display: none\">" + id + "</span>\n" +
        "        </a>\n" +
        "    </li>";
}

//默认左侧选项卡
const defaultArticleTab = () => {
    return "<li class=\"list-group-item\">\n" +
        "                        <a class=\"list-group-item list-group-item-action active\" data-toggle=\"list\"\n" +
        "                           href=\"javascript:\" role=\"tab\" aria-controls=\"home\">\n" +
        "                            <h5>新建文章标题</h5><span>" + getNowData() + "</span>\n" +
        "                        </a>\n" +
        "                    </li>";
}

//渲染分页
const render = (currentPage) => {
    if (currentPage === null || currentPage === undefined) {
        currentPage = 1;
    }
    let total = 5;
    $.ajax({
        url: "/api/article/author/sel?index=" + currentPage + "&total=" + total,
        success: function (result) {
            if (result.code === 1) {
                let res = result.content;
                let articleUl = $("#articleListUl");
                //默认选项卡
                articleUl.html(defaultArticleTab());
                for (let i = 0; i < res.articleList.length; i++) {
                    let id = res.articleList[i].id;
                    let title = res.articleList[i].title;
                    let createDate = res.articleList[i].createDate;
                    // 将数据渲染到页面
                    articleUl.append(addArticleTab(id, title, createDate));
                }
                bindTabClick(res.articleList);
                //设置分页
                let pageSum = Math.ceil(res.articleTotal / total);
                setPage(currentPage, pageSum, render);
            }
        }
    });
}

//设置分页
const setPage = (currentPage, pageSum) => {
    $("#pagination").bootstrapPaginator({
        //设置版本号
        bootstrapMajorVersion: 3,
        // 显示第几页
        currentPage: currentPage,
        // 总页数
        totalPages: pageSum,
        //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
        onPageClicked: function (event, originalEvent, type, page) {
            // 调用ajax,渲染页面
            render(page);
        }
    })
}

//绑定Tab事件
const bindTabClick = (articleList) => {
    //左侧选项卡单击事件（选择文章进行编辑）
    $('a[data-toggle="list"]').on('click', function () {
        // 找到列表id项
        let articleId = $(this).find('span[id="hiddenId"]').text();
        let article = null;
        for (let i = 0; i < articleList.length; i++) {
            if (articleList[i].id === articleId) {
                article = articleList[i];
                break;
            }
        }
        if (article === null) {
            //新文章
            editor.txt.html('');
            $("#inputTitle").val('新建文章');
            $("#selectSort").val(0);
        } else {
            //往编辑器赋值
            editor.txt.html(article.content);
            $("#inputTitle").val(article.title);
            $("#selectSort").val(article.sortId);
        }
    });
}

//获取当前时间
const getNowData = () => {
    let Dates = new Date();
    let Y = Dates.getFullYear();
    let M = Dates.getMonth() + 1;
    let D = Dates.getDate();
    return Y + (M < 10 ? "-0" : "-") + M + (D < 10 ? "-0" : "-") + D;
}
