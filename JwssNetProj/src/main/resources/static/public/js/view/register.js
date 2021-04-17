const key = "www.vjwss.top";

window.onload = function () {
    changeVerifyCode();
}

const registerTipShow = () => {
    onClickToRegister();
}

//提交注册按钮
const onClickToRegister = () => {
    let account = $("#userNameInput").val();
    let password = $("#passwordInput").val();
    let passwordAgain = $("#passwordAgain").val();
    let verifyCode = $("#verifyInput").val();
    let tipP = $("#tipModal p");

    if (account === '' || account.length < 6 || account.length > 16) {
        tipP.html(getTipType("账号必须在6~16间", "error"));
        return;
    }
    if (password === '' || password.length < 6 || password.length > 16) {
        tipP.html(getTipType("密码必须在6~16间", "error"));
        return;
    }
    if (passwordAgain === '' || passwordAgain !== password) {
        tipP.html(getTipType("与第一次输入密码不符", "error"));
        return;
    }
    if (verifyCode === '') {
        tipP.html(getTipType("请输入验证码", "error"));
        return;
    }
    tipP.html(getTipType("正在注册...", "info"));
    //发起http请求
    httpRegister(account, password, verifyCode);
}

//注册表单内容提交
const httpRegister = (account, password, verifyCode) => {
    let formData = {
        account: account,
        password: password,
        code: verifyCode,
        key: getNowData()
    }
    $.ajax({
        url: "/api/register/join",
        method: 'post',
        data: formData,
        success: function (response) {
            if (response.code === 1) {
                let tip = response.content + "两秒后自动跳转到登录页面, 也可以";
                let quickJump = "<a href='/login?preUrl=/'>立即跳转</a>";
                $("#tipModal p").html(getTipType(tip + quickJump, "info"));
                setTimeout(function () {
                    window.location.href = "/login?preUrl=/";
                }, 2000);
            } else if (response.content !== '') {
                $("#tipModal p").html(getTipType(response.content, "error"));
            }
        }
    });
}


//更换验证码
const changeVerifyCode = () => {
    let verifyCodeSpan = $("#verifyCode");
    verifyCodeSpan.on('click', () => {
        verifyCodeSpan.html('');
        verifyCodeSpan.html('<img src="/api/register/verifyCode" class="verify-code" alt="验证码">');
    });
}

//获取提示类型,error/info
const getTipType = (content, type) => {
    let s = '';
    if (type === "error") {
        s = '<div class="alert alert-danger" role="alert">' + content + '</div>';
    } else if (type === "info") {
        s = '<div class="alert alert-success" role="alert">' + content + '</div>';
    }
    return s;
}

//获取当前时间
const getNowData = () => {
    let Dates = new Date();
    let Y = Dates.getFullYear();
    let M = Dates.getMonth() + 1;
    let D = Dates.getDate();
    let H = Dates.getHours();
    let t = Y + (M < 10 ? "/0" : "/") + M + (D < 10 ? "/0" : "/") + D + " " + (H < 10 ? "0" : "") + H;
    let k = "register" + key + t;
    return md5(k);
}
