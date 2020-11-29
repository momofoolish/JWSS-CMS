<#import '../common/resource.ftl' as macros>
<#import '../common/component.ftl' as coms>
<#import '../common/encrypt.ftl' as encr>

<@macros.Base>
    <@macros.Head></@macros.Head>
    <@encr.EncryptConst></@encr.EncryptConst>
    <@macros.Body>
        <@macros.JqueryLabel></@macros.JqueryLabel>
        <script type="text/javascript" src="http://res.vjwss.top/lib/wangEditor-4.5.0/wangEditor.min.js"></script>

    <#-- 内容编辑 -->
        <div class="view-editor">
            <#--    面包屑-->
            <span class="com-bread">
                <cite>编辑文章</cite>
            </span>

            <#-- 文章标题 -->
            <label style="margin-top: 1em;">
                <input id="inputTitle" type="text" class="ae-input-title" placeholder="标题">
            </label>

            <#-- 描述 -->
            <label>
                <textarea id="inputDesc" class="ae-input-Desc" placeholder="内容描述"></textarea>
            </label>
            
            <#-- 编辑器 -->
            <style>
                .w-e-text-container {
                    height: 600px !important;
                }
            </style>
            <div id="editorDiv">
                <p>请输入内容...</p>
            </div>

            <#-- 上传图片 -->
            <div class="upload-img">
                <div class="upload-div-bg">
                    <input type="file" id="uploadInput" accept="image/jpeg,image/png">
                    <@coms.UploadButtonIcon></@coms.UploadButtonIcon>
                </div>
                <div id="coverPre"></div>
            </div>

            <#-- 其他选项  -->
            <div class="other-row" style="margin-top: 1em">
                <div class="other-row-col-1">
                    <select id="selectSort" class="select-sort">
                        <option selected>分类</option>
                        <#list articleSortList as articleSort>
                            <option value="${articleSort.id}">${articleSort.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="other-row-col-2">
                    <div class="jq22-content">
                        <form id="form" class="d-flex align-items-center">
                            <input id="form-tags-2" name="tags-2" type="text" value="输入标签">
                        </form>
                    </div>
                </div>
            </div>

            <#-- 操作按钮 -->
            <div class="button-row">
                <button id="submitBtn" type="button" class="button-col">提交</button>
                <button type="button" class="button-col">存稿</button>
                <button type="button" class="button-col">预览</button>
                <button type="button">放弃</button>
            </div>
        </div>

        <script type="text/javascript" src="/public/js/view/articleEditor.js"></script>
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
