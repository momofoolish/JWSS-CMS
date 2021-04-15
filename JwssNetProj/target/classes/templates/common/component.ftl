<#--自定义输入框-->
<#macro Input>
    <link rel="stylesheet" href="/public/js/component/input/input.css">
    <script src="/public/js/component/input/input.js" type="text/babel"></script>
</#macro>

<#--自定义按钮-->
<#macro Button>
    <link rel="stylesheet" href="/public/js/component/button/button.css">
    <script src="/public/js/component/button/button.js" type="text/babel"></script>
</#macro>

<#--弹窗-->
<#macro Alert>
    <div id="JwssAlert" style="z-index: 9999;background: rgba(0, 0, 0, 0.6);position: fixed;outline: 0;
        width: 100%;height: 100%;-webkit-overflow-scrolling: touch;overflow: hidden;
        left: 0;top: 0;right: 0;bottom: 0;display: none;justify-content: center;
        align-items: center;">
        <div style="width: 330px;height: 330px;display: flex;align-items: center;flex-direction: column;
            background: #fff9ec;border-radius: 6px;justify-content: center;">
            <#nested>
        </div>
    </div>
</#macro>

<#--预览内容-->
<#macro Preview>
    <div id="JwssPreview" style="z-index: 9999;background: rgba(0, 0, 0, 0.6);position: fixed;outline: 0;
        width: 100%;height: 100%;-webkit-overflow-scrolling: touch;overflow: hidden;
        left: 0;top: 0;right: 0;bottom: 0;display: none;justify-content: center;
        align-items: center;">
        <div style="width: 90%;height: 90%;display: flex;align-items: center;flex-direction: column;
            background: #fff9ec;border-radius: 6px;justify-content: center;">
            <#nested>
        </div>
    </div>
</#macro>

<#--图标-->
<#macro UploadButtonIcon>
    <svg t="1606486721945" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="3047" width="128" height="128">
        <path d="M802.19 923.46H221.81c-66.67 0-120.9-54.24-120.9-120.9V222.19c0-66.67 54.24-120.9 120.9-120.9h580.37c66.67 0 120.9 54.24 120.9 120.9v66.2c0 16.02-12.98 29-29 29s-29-12.98-29-29v-66.2c0-34.68-28.22-62.9-62.9-62.9H221.81c-34.68 0-62.9 28.22-62.9 62.9v580.37c0 34.68 28.22 62.9 62.9 62.9h580.37c34.68 0 62.9-28.22 62.9-62.9V423.78c0-16.02 12.98-29 29-29s29 12.98 29 29v378.79c0.01 66.66-54.23 120.89-120.89 120.89z"
              p-id="3048" fill="#ffffff"></path>
        <path d="M715.28 541.38H308.72c-16.02 0-29-12.98-29-29s12.98-29 29-29h406.56c16.02 0 29 12.98 29 29 0 16.01-12.99 29-29 29z"
              p-id="3049" fill="#ffffff"></path>
        <path d="M512 744.65c-16.02 0-29-12.98-29-29V309.1c0-16.02 12.98-29 29-29s29 12.98 29 29v406.56c0 16.01-12.98 28.99-29 28.99z"
              p-id="3050" fill="#ffffff"></path>
    </svg>
</#macro>
