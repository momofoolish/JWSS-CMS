<#import '../../common/resource.ftl' as macros>
<#--引入layui框架-->
<@macros.JqueryLayui></@macros.JqueryLayui>
<style>
    body{
        overflow-y: auto;
        overflow-x: hidden;
    }
    .feedback-td, .account-td, .article-td{
        word-break:keep-all;
        white-space:nowrap;
        overflow:hidden;
        text-overflow:ellipsis;
    }
    .layui-table{
        table-layout: fixed;
    }
</style>

<script src="/public/plus/ECharts/echarts.min.js"></script>
<script src="/public/js/admin/default.js"></script>

<div class="layui-row layui-col-space10">
    <div class="layui-col-md4">
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th>最新文章</th>
            </tr>
            </thead>
            <tbody>
            <#list articleList as article>
                <tr>
                    <td class="article-td">${article.title}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="layui-col-md4">
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th>最近注册用户</th>
            </tr>
            </thead>
            <tbody>
            <#list userList as user>
                <tr>
                    <td class="account-td">${user.account}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="layui-col-md4">
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th>最新反馈</th>
            </tr>
            </thead>
            <tbody>
            <#list feedbackList as feedback>
                <tr>
                    <td class="feedback-td">${feedback.content}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<div class="layui-row layui-col-space10">
    <div class="layui-col-md8">
        <#-- 系统信息 -->
        <div id="systemChart" style="height: 300px"></div>
    </div>
    <div class="layui-col-md4">
        <#-- 网站访问量 -->
        <div id="websiteChart" style="height: 300px"></div>
    </div>
</div>

<div class="layui-row layui-col-space10">
    <div class="layui-col-md6">
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>JAVA_HOME: <span id="jHome"></span> </span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>JAVA_VERSION: <span id="jVersion"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>USER_HOME: <span id="uHome"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>操作系统: <span id="OS"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>启动时间: <span id="bootTime"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>CPU核数: <span id="cpuCount"></span></span>
        </blockquote>
    </div>
    <div class="layui-col-md6">
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>CPU使用率: <span id="usedCpu"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>已用内存: <span id="usedMemory"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>总内存: <span id="totalMemory"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>剩余空间: <span id="usableSpace"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span>总空间: <span id="totalSpace"></span></span>
        </blockquote>
        <blockquote class="layui-elem-quote layui-quote-nm">
            <span> END </span>
        </blockquote>
    </div>
</div>
