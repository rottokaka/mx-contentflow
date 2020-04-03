/**
 * related html : snippet/modify.html
 */
$(function () {
    // #1 变量定义区~
    //==================================================================================================================
    // 片段ID
    const snippetId = $('#snippetId').val();
    // #2 函数定义区~
    //==================================================================================================================
    // #3 脚本初始化~
    //==================================================================================================================

    // markdown 初始化
    const editor = editormd("editormd", {
        height: 770,
        path: "../../editor.md/lib/", // Autoload modules mode, codemirror, marked... dependents libs path
        saveHTMLToTextarea: true,
        toolbarIcons: "simple"
    });

    // 单选框初始化
    $('.form-control-chosen').chosen();

    // 标签初始化
    $('.form-control-tag').tagEditor({
        /*initialTags: ['Hello', 'World'],*/
        maxTags: 5,
        maxLength: 50,
        delimiter: ', ',
        forceLowercase: false,
        animateDelete: 0,
        placeholder: '可选择至多五个标签，“Enter”,“Space”确定一个标签）',
    });

    // #4 事件定义区~
    //==================================================================================================================

    // 更新片段
    $("#snippetUpdate").click(function () {
        // 片段内容
        const content = editor.getMarkdown(); // 获取 Markdown 源码
        // 片段内容HTML形式
        const contentHtml = editor.getHTML(); // 获取 Textarea 保存的 HTML 源码
        /*const contentPreviewedHtml = editor.getPreviewedHTML();*/  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        // 片段修改视图对象
        const snippetUpdateVo = {
            "title": $('#title').val(),
            "description": $('#description').val(),
            "content": content,
            "contentHtml": contentHtml,
            "scope": $('#scopeSelect').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/snippets/' + snippetId,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(snippetUpdateVo),
            success: function (data) {
                if (data.valid) {
                    window.location = "/snippet/" + snippetId + "/detail";
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });
});
