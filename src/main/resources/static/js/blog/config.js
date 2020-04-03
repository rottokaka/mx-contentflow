/**
 * related html : blog/create.html
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
        height: 770,
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
    $(function() {
        $('[title="clickable_optgroup"]').addClass('chosen-container-optgroup-clickable');
    });
    // Clickable optgroup
    $(document).on('click', '[title="clickable_optgroup"] .group-result', function() {
        const unselected = $(this).nextUntil('.group-result').not('.result-selected');
        if(unselected.length) {
            unselected.trigger('mouseup');
        } else {
            $(this).nextUntil('.group-result').each(function() {
                $('a.search-choice-close[data-option-array-index="' + $(this).data('option-array-index') + '"]').trigger('click');
            });
        }
    });
    // #4 事件定义区 start
    //==================================================================================================================
    // 移除所有标签
    $(document).on('click', '.btn-remove-tagName', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });
    // 新增或更新博客
    $("#submitBlog").click(function () {
        // 自定义k-v键值对
        // 是否隐藏字段
        let hidden = 0;
        if ($("#hidden").prop("checked")) {
            hidden = 1;
        }
        // 是否允许收录字段
        let notAllowCollect = 0;
        if ($('#notAllowCollect').prop("checked")) {
            notAllowCollect = 1;
        }
        // 内容
        const content = editor.getMarkdown(); // 获取 Markdown 源码
        // 内容HTML格式
        const contentHtml = editor.getHTML(); // 获取 Textarea 保存的 HTML 源码
        const contentPreviewedHtml = editor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        // 默认值处理
        const aboveId = 0;
        const archived = 0;
        // 博客视图对象
        const blogInsertVO = {
            "title": $('#title').val(),
            "content": content,
            "contentHtml": contentHtml,
            "tags": $('#tagName').val(),
            "hidden": hidden,
            "notAllowCollect": notAllowCollect
        };

        $.ajax({
            async: true,
            type: 'POST',
            url: '/blogs',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(blogInsertVO),
            success: function (data) {
                if (data.valid) {
                    window.location = data.data;
                    $("#userModal").modal('hide');
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });
});
