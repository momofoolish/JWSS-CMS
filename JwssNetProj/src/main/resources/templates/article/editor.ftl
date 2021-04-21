<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>
<#import '../common/encrypt.ftl' as encr>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@encr.EncryptConst></@encr.EncryptConst>
    <@macros.Body>
    <#-- 导入所需资源 -->
        <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap-grid.min.css">
        <link rel="stylesheet" href="/public/plus/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="/public/css/article/editor.css">
        <script type="text/javascript" src="/public/plus/jquery/jquery.js"></script>
        <script type="text/javascript" src="/public/plus/bootstrap/bootstrap.js"></script>
        <script type="text/javascript" src="/public/plus/wangEditor.min.js"></script>
        <script type="text/javascript" src="/public/js/content/editor.js"></script>

    <#-- 内容编辑 -->
        <div class="view-editor">
            <div class="article-list">
                <#-- 文章列表 -->
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <a class="list-group-item list-group-item-action active" data-toggle="list"
                           href="javascript:" role="tab" aria-controls="home">
                            <h5>新建文章标题</h5><span>${nowDate?string('yyyy/MM/dd')}</span>
                        </a>
                    </li>
                    <#list articleMap.articles as article>
                        <li class="list-group-item">
                            <a class="list-group-item list-group-item-action" data-toggle="list"
                               href="javascript:" role="tab" aria-controls="home">
                                <h5>${article.title}</h5>
                                <span>
                                ${article.createDate?string('yyyy/MM/dd')}
                            </span>
                            </a>
                        </li>
                    </#list>
                </ul>
                <#-- 分页 -->
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <a class="page-link" href="/author/editor?p=${articleMap.page-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <#list articleMap.page..articleMap.total as index>
                            <#if index < (5+articleMap.page)>
                                <li class="page-item">
                                    <a class="page-link" href="/author/editor?p=${index}">
                                        ${index}
                                    </a>
                                </li>
                            </#if>
                        </#list>
                        <#if (articleMap.total > 5)>
                            <li>
                                <a class="page-link" href="javascript:">
                                    <span aria-hidden="true">...</span>
                                </a>
                            </li>
                            <li>
                                <a class="page-link" href="/author/editor?p=${articleMap.total}" aria-label="Next">
                                    <span aria-hidden="true">${articleMap.total}</span>
                                </a>
                            </li>
                        </#if>
                        <li>
                            <a class="page-link" href="/author/editor?p=${articleMap.page+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

            <div style="width: 60%;padding: 1em;">
                <#-- 文章标题 -->
                <div style="width: 100%;text-align: center;">
                    <input type="text" class="form-control form-control-lg" placeholder="标题" aria-label="标题"
                           aria-describedby="basic-addon1" id="inputTitle">
                </div>
                <#-- 编辑器 -->
                <div id="editorDiv"></div>
            </div>

            <#-- 操作按钮 -->
            <div class="button-row">
                <div>
                    <select id="selectSort" class="form-control">
                        <option selected>分类</option>
                        <#list articleSortList as articleSort>
                            <option value="${articleSort.id}">${articleSort.name}</option>
                        </#list>
                    </select>
                </div>

                <#-- 上传图片 -->
                <div class="upload-img">
                    <div class="upload-div-bg">
                        <input type="file" id="uploadInput" accept="image/jpeg,image/png">
                        <@coms.UploadButtonIcon></@coms.UploadButtonIcon>
                    </div>
                    <div id="coverPre"></div>
                </div>

                <div>
                    <button id="submitBtn" type="button" class="btn btn-primary"
                            data-toggle="modal" data-target="#tipModal">提交
                    </button>
                    <button id="saveBtn" type="button" class="btn btn-secondary">存稿</button>
                    <button id="previewBtn" type="button" class="btn btn-info"
                            data-toggle="modal" data-target="#previewModal">预览
                    </button>
                    <button type="button" class="btn btn-danger"
                            data-toggle="modal" data-target="#giveUpModal">放弃
                    </button>
                </div>
            </div>
        </div>
    </@macros.Body>
<#-- 弹窗：提示窗口 -->
    <div class="modal fade" tabindex="-1" id="tipModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="tipText">...</p>
                </div>
            </div>
        </div>
    </div>
<#-- 弹窗：预览窗口 -->
    <div class="modal fade" tabindex="-1" id="previewModal">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">文章预览</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="previewText">...</p>
                </div>
            </div>
        </div>
    </div>
<#-- 弹窗：是否放弃编辑 -->
    <div class="modal fade" id="giveUpModal" tabindex="-1" aria-labelledby="giveUpModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="giveUpModalLabel">页面提示</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    放弃编辑? 你将失去本页未保存内容.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" onclick="window.location.href='/'" class="btn btn-primary">确定</button>
                </div>
            </div>
        </div>
    </div>
    <@macros.Foot></@macros.Foot>
</@macros.Base>
