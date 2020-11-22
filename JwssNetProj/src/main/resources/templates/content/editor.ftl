<#import '../common/resource.ftl' as macros>

<@macros.Base>
<@macros.Head></@macros.Head>
<@macros.Body>
<@macros.JqBootStroop></@macros.JqBootStroop>
<@macros.JqueryLabel>
    <style>
        .w-e-text-container {
            height: 65vh !important;
        }
    </style>
    <script type="text/javascript" src="http://res.vjwss.top/lib/wangEditor-3.1.1/release/wangEditor.min.js"></script>
    <script type="text/javascript" src="/public/js/view/articleEditor.js"></script>
    <link rel="stylesheet" href="/public/css/articleEditor.css">
</@macros.JqueryLabel>

<#--    内容编辑-->
<div class="container shadow-sm p-3 mb-5 bg-white rounded">
    <#--    面包屑-->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb bg-transparent" style="padding: 0;">
            <li class="breadcrumb-item"><a href="/">主页</a></li>
            <li class="breadcrumb-item active" aria-current="page">编辑文章</li>
        </ol>
    </nav>
    <#--    文章标题-->
    <label style="width: 100%;">
        <input type="text" class="form-control ae-input-title" placeholder="标题">
    </label>
    <#--    描述-->
    <label style="width: 100%;">
        <textarea class="form-control" placeholder="内容描述"></textarea>
    </label>
    <#--    编辑器-->
    <div id="editorDiv"></div>
    <#--        其他选项-->
    <div class="row" style="margin-top: 1em">
        <div class="col-2">
            <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                <option selected>游戏分类</option>
                <option value="1">赛车</option>
                <option value="2">恐怖</option>
                <option value="3">沙盒</option>
            </select>
        </div>
        <div class="col-10">
            <div class="jq22-content">
                <form id="form" class="d-flex align-items-center">
                    <label for="form-tags-2">标签：</label>
                    <input id="form-tags-2" name="tags-2" type="text" value="生化危机">
                </form>
            </div>
        </div>
    </div>
    <#--        操作按钮-->
    <div class="row" style="width: 50%;margin-top: 1em;">
        <div class="col-3">
            <button type="button" class="btn btn-lg btn-outline-primary" style="width: 114px;">提交</button>
        </div>
        <div class="col-3">
            <button type="button" class="btn btn-lg btn-outline-primary" style="width: 114px;">存稿</button>
        </div>
        <div class="col-3">
            <button type="button" class="btn btn-lg btn-outline-primary" style="width: 114px;">预览</button>
        </div>
        <div class="col-3">
            <button type="button" class="btn btn-lg btn-outline-primary" style="width: 114px;"
                    data-toggle="modal" data-target="#giveUpModal">放弃
            </button>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="giveUpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" style="z-index: 10001">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
    </@macros.Body>
    <@macros.Foot></@macros.Foot>
    </@macros.Base>
