<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>
<#import '../common/encrypt.ftl' as encr>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@macros.Jquery></@macros.Jquery>
    <@encr.EncryptConst></@encr.EncryptConst>
    <@macros.Body>
        <script type="text/javascript" src="/public/plus/wangEditor.min.js"></script>

    <#-- 内容编辑 -->
        <div class="view-editor">
            <ul class="article-list">
                <li class="new-article"><h3>新建文章标题</h3><span>${nowDate?string('yyyy/MM/dd')}</span></li>
                <#list articleList as article>
                    <li class="other-article">
                        <h3>${article.title}</h3><span>${article.createDate?string('yyyy/MM/dd')}</span>
                    </li>
                </#list>
            </ul>

            <div style="width: 60%;padding: 1em;">
                <#-- 文章标题 -->
                <div style="width: 100%;text-align: center;">
                    <input id="inputTitle" type="text" class="ae-input-title" placeholder="标题">
                </div>
                <#-- 编辑器 -->
                <style>
                    .w-e-text-container {
                        height: 640px !important;
                    }
                </style>
                <div id="editorDiv"></div>
            </div>

            <#-- 操作按钮 -->
            <div class="button-row">
                <div>
                    <select id="selectSort" class="select-sort">
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
                    <button id="submitBtn" type="button" class="button-col">提交</button>
                    <button id="saveBtn" type="button" class="button-col">存稿</button>
                    <button id="previewBtn" type="button" class="button-col">预览</button>
                    <button id="giveUpBtn" type="button">放弃</button>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="/public/js/content/editor.js"></script>
        <link rel="stylesheet" href="/public/css/editor.css">
    </@macros.Body>
    <@macros.Foot></@macros.Foot>
</@macros.Base>

<#--弹出层-->
<@coms.Alert>
    <p id="pTit"> 标题 </p>
    <p id="pMsg"> 内容 </p>
    <p id="pTip"> 提示 </p>
    <button id="pBtn"> 确定</button>
</@coms.Alert>

<#--弹出层-->
<@coms.Preview>
    <p id="preMsg" style="overflow: auto;"> 内容 </p>
    <button id="preBtn" style="width: 91px"> 关闭预览 </button>
</@coms.Preview>
