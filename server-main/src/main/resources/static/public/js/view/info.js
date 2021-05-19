window.onload = function () {
    let cover;      //封面

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
