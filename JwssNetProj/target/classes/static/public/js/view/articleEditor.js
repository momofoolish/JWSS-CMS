window.onload = function () {
    let content;    //文章内容
    let cover;      //封面
    let tag = [];   //标签

    //创建文本编辑器
    const editor = new wangEditor('#editorDiv');
    editor.config.zIndex = 1;
    editor.config.onchangeTimeout = 500;
    editor.config.onchange = function (html) {
        content = html;
    }
    editor.create();

    //标签组件回调
    $(function () {
        $('#form-tags-2').tagsInput({
            'onAddTag': function (input, value) {
                tag.push(value);
            },
            'onRemoveTag': function (input, value) {
                tag.forEach(function (v, index) {
                    if (value === v) {
                        delete tag[index];
                    }
                })
            },
            'onChange': function (input, value) {

            }
        });
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

    //提交
    $("#submitBtn").on('click', function () {
        //判断内容是否为空
        let warn = "<span style='color: orange;font-size: 28px'>警告</span>";
        if ($("#inputTitle").val().length < 4 || $("#inputTitle").val().length > 32) {
            jwssAlert(warn, "标题长度不对", "", "关闭");
            return;
        }
        if ($("#inputDesc").val().length < 4 || $("#inputDesc").val().length >= 128) {
            jwssAlert(warn, "描述字数不对", "", "关闭");
            return;
        }
        if (content === undefined || content.length < 32) {
            jwssAlert(warn, "内容过少，至少16个字", "", "关闭");
            return;
        }
        if ($("#selectSort").val() === "分类") {
            jwssAlert(warn, "请选择分类", "", "关闭");
            return;
        }
        if (tag.toString().length <= 0) {
            jwssAlert(warn, "写个标签吧！", "", "好的!");
            return;
        } else if (tag.toString().length > 32) {
            jwssAlert(warn, "标签过多", "", "关闭");
            return;
        }
        if (cover === "" || cover === undefined) {
            jwssAlert(warn, "缺少封面", "", "重选");
            return;
        }

        let formData = new FormData();
        formData.append("edKey", encryptConst);
        formData.append("sort", $("#selectSort").val());
        formData.append("title", $("#inputTitle").val());
        formData.append("desc", $("#inputDesc").val());
        formData.append("tag", tag.toString());
        formData.append("content", content);
        formData.append("cover", cover);
        $.ajax({
            url: '/api/article/author/add',
            method: 'post',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                let success = "<span style='color: limegreen;font-size: 28px'>提示</span>";
                if (response.code === 1) {
                    jwssAlert(success, response.content, "", "确定");
                } else {
                    jwssAlert("提示：", response.content, "", "关闭");
                }
            }
        });
    });

    alertButtonHandle();
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

// 弹窗使用
const jwssAlert = (t, c, ti, b) => {
    $("#pTit").html(t);
    $("#pMsg").html(c);
    $("#pTip").html(ti);
    $("#pBtn").html(b);
    $("#JwssAlert").css({'visibility': 'visible'});
}

// 弹窗按钮事件
const alertButtonHandle = () => {
    $("#JwssAlert button").on('click', function () {
        $("#JwssAlert").css({'visibility': 'hidden'});
    });
}
