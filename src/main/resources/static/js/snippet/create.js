/**
 * related html : snippet/create.html
 */
$(function () {
    // #1 变量定义区 start
    //==================================================================================================================
    // #2 函数定义区 start
    //==================================================================================================================
    // #3 脚本初始化 start
    //==================================================================================================================

    // markdown 初始化
    const editor = editormd("editormd", {
        height: 500,
        path: "../editor.md/lib/", // Autoload modules mode, codemirror, marked... dependents libs path
        saveHTMLToTextarea: true,
        toolbarIcons: "simple"
    });

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

    // 选择框初始化
    $(".form-control-chosen").chosen();
    $(".form-control-chosen-optgroup").chosen();
    $(function () {
        $('[title="clickable_optgroup"]').addClass('chosen-container-optgroup-clickable');
    });

    // Clickable optgroup
    $(document).on('click', '[title="clickable_optgroup"] .group-result', function () {
        const unselected = $(this).nextUntil('.group-result').not('.result-selected');
        if (unselected.length) {
            unselected.trigger('mouseup');
        } else {
            $(this).nextUntil('.group-result').each(function () {
                $('a.search-choice-close[data-option-array-index="' + $(this).data('option-array-index') + '"]').trigger('click');
            });
        }
    });

    // #4 事件定义区 start
    //==================================================================================================================

    // 移除所有标签
    $(document).on('click', '.form-control-tag', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });

    // 插入片段
    $("#snippetPublish").click(function () {
        // 内容
        const content = editor.getMarkdown(); // 获取 Markdown 源码
        // 内容HTML格式
        const contentHtml = editor.getHTML(); // 获取 Textarea 保存的 HTML 源码
        const contentPreviewedHtml = editor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        // 博客视图对象
        const snippetInsertVO = {
            "title": $('#title').val(),
            "content": content,
            "contentHtml": contentHtml,
            "description": $('#description').val(),
            "scope": $('#scopeSelect').val()
        };

        $.ajax({
            type: 'POST',
            url: '/snippets',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(snippetInsertVO),
            success: function (data) {
                if (data.valid) {
                    window.location = '/snippet';
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
