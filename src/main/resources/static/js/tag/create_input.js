/**
 * related html: tag/create_input.html
 */
$(function () {

    // 标签按钮组
    const tagBtnGroup = $('#tagBtnGroup');

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

    // 清空所有标签
    tagBtnGroup.on('click', '.btn-remove-tagName', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });

});
