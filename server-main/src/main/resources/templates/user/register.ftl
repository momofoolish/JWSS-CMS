<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>

<@macros.Base>
    <link rel="stylesheet" href="/public/css/portal/register.css">
    <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap-grid.min.css">
    <script type="text/javascript" src="/public/plus/jquery/jquery.js"></script>
    <script type="text/javascript" src="/public/plus/bootstrap/bootstrap.js"></script>
    <script type="text/javascript" src="/public/plus/encryption/md5.min.js"></script>
    <script type="text/javascript" src="/public/js/view/register.js"></script>

    <@macros.Body>
        <div class="out-box container">
            <div class="d-flex justify-content-between">
                <div></div>
                <div class="shadow bg-white rounded">
                    <!-- 标题 -->
                    <h3 class="form-title margin-top-1em">
                        <a href="/">LOGO</a> <span class="text-secondary">|</span> 账号注册
                    </h3>
                    <!-- 登录表单 -->
                    <div class="form-box">
                        <!-- 用户名 -->
                        <input type="text" class="form-control" placeholder="用户名" aria-label="用户名"
                               aria-describedby="basic-addon1" id="userNameInput">
                        <!-- 密码 -->
                        <input type="password" class="form-control margin-top-1em" placeholder="密码" aria-label="密码"
                               aria-describedby="basic-addon1" id="passwordInput">
                        <!-- 再次输入密码 -->
                        <input type="password" class="form-control margin-top-1em" placeholder="再次输入密码"
                               aria-label="再次输入密码" aria-describedby="basic-addon1" id="passwordAgain">
                        <!-- 验证码 -->
                        <div class="margin-top-1em verify-code-box">
                            <input type="text" class="form-control" placeholder="验证码" aria-label="验证码"
                                   aria-describedby="basic-addon1" id="verifyInput">
                            <span id="verifyCode">
                                <img src="/api/register/verifyCode" class="verify-code" alt="验证码">
                            </span>
                        </div>
                        <!-- 表单提交 -->
                        <div class="margin-top-1em submit-box">
                            <span>已有账号！<a href="/login?preUrl=/">前往登录</a></span>
                            <button onclick="registerTipShow()" type="button" class="btn btn-primary"
                                    data-target="#tipModal" data-toggle="modal">
                                立即注册
                            </button>
                        </div>
                    </div>
                </div>
                <!-- 错误提示弹窗 -->
                <div id="loginTip">
                    <div id="tipModal" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">注册提示</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>tip</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </@macros.Body>
</@macros.Base>
