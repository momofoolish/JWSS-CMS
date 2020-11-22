window.onload = function () {
    //创建文本编辑器
    var E = window.wangEditor;
    var editor = new E('#editorDiv');
    editor.create();

    //标签组件回调
    $(function () {
        $('#form-tags-2').tagsInput({
            'onAddTag': function (input, value) {
                console.log('tag added', input, value);
            },
            'onRemoveTag': function (input, value) {
                console.log('tag removed', input, value);
            },
            'onChange': function (input, value) {
                console.log('change triggered', input, value);
            }
        });
    });

    //模糊弹框
}
