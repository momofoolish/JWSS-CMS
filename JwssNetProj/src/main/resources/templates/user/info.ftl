<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@macros.Body>
    <#-- 导入所需资源 -->
        <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap-grid.min.css">
        <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="/public/css/portal/info.css">
        <script type="text/javascript" src="/public/plus/jquery/jquery.js"></script>
        <script type="text/javascript" src="/public/plus/bootstrap/bootstrap.js"></script>
        <script type="text/javascript" src="/public/plus/bootstrap/bootstrap-paginator.min.js"></script>
        <script type="text/javascript" src="/public/plus/wangEditor.min.js"></script>
        <script type="text/javascript" src="/public/js/view/info.js"></script>

        <div class="card">
            <div class="card-body">
                <div class="row">
                    <#-- 左侧导航栏 -->
                    <div class="col-3">
                        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist"
                             aria-orientation="vertical">
                            <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home"
                               role="tab" aria-controls="v-pills-home" aria-selected="true">基本信息</a>
                            <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile"
                               role="tab" aria-controls="v-pills-profile" aria-selected="false">我的简介</a>
                            <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages"
                               role="tab" aria-controls="v-pills-messages" aria-selected="false">我的消息</a>
                            <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings"
                               role="tab" aria-controls="v-pills-settings" aria-selected="false">我的设置</a>
                        </div>
                    </div>
                    <#-- 右侧卡片 -->
                    <div class="col-9">
                        <div class="tab-content" id="v-pills-tabContent">
                            <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                                 aria-labelledby="v-pills-home-tab">
                                <#-- 基本信息 -->
                                <div class="card">
                                    <div class="card">
                                        <div class="row no-gutters">
                                            <div class="col-md-4" id="coverPre">
                                                <img src="https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg"
                                                     alt="...">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body">
                                                    <h5 class="card-title">系统管理员</h5>
                                                    <p class="card-text">This is a wider card with supporting text below
                                                        as a natural lead-in to additional content. This content is a
                                                        little bit longer.</p>
                                                    <p class="card-text">
                                                        <small class="text-muted">
                                                            注册于 2020/4/12
                                                        </small>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <#-- 信息修改 -->
                                <div class="card" style="margin-top: 1em;">
                                    <h5 class="card-header">信息修改</h5>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-group">
                                                <div class="form-group col-md-6">
                                                    <label for="inputName">昵称</label>
                                                    <input type="text" class="form-control" id="inputName"
                                                           placeholder="新的昵称">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputEmail">邮箱</label>
                                                    <input type="email" class="form-control" id="inputEmail"
                                                           placeholder="新邮箱">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="form-group col-md-12">
                                                    <label for="inputSign">签名</label>
                                                    <input type="text" class="form-control" id="inputSign"
                                                           placeholder="新签名">
                                                </div>
                                            </div>
                                            <#-- 上传图片 -->
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <div class="upload-img">
                                                        <div class="upload-div-bg">
                                                            <label>修改头像</label>
                                                            <input type="file" id="uploadInput"
                                                                   accept="image/jpeg,image/png">
                                                            <@coms.UploadHeadIcon></@coms.UploadHeadIcon>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <button type="submit" class="btn btn-primary">保存</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>


                            </div>

                            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                                 aria-labelledby="v-pills-profile-tab">...
                            </div>
                            <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                                 aria-labelledby="v-pills-messages-tab">...
                            </div>
                            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
                                 aria-labelledby="v-pills-settings-tab">...
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </@macros.Body>
    <@macros.Foot></@macros.Foot>
</@macros.Base>
