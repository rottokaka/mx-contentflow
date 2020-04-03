/**
 * related html: snippet/index.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // 默认每页显示大小
    const PAGE_DEFAULT_SIZE = 1;
    // 默认显示页码
    const PAGE_DEFAULT_PAGE = 1;

    // 片段排序选择框
    const snippetSort = $('#snippetSort');

    // #2 函数定义区~
    //==================================================================================================================
    // 加载博客卡片页面
    function loadSnippetInPage(page, size) {
        const keyword = $('#snippetK').val();
        const sort = snippetSort.val();
        $.ajax({
            type: "GET",
            url: "/snippet/card?keyword=" + keyword + "&page=" + page + "&size=" + size + "&sort=" + sort,
            success: function (data) {
                $("#snippetCardContainer").html(data);
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // 选择框初始化
    $('.form-control-chosen').chosen();

    // #4 事件定义区~
    //==================================================================================================================

    // 排序选择框变化事件
    snippetSort.change(function () {
        loadSnippetInPage();
    });

    // 搜索点击事件
    $('#searchSnippet').click(function () {
        loadSnippetInPage();
    });

    // 分页页码点击事件
    $('#snippetCardContainer').on('click', '.page-item', function () {
        const page = $(this).val();
        alert(page);
        loadSnippetInPage(page, PAGE_DEFAULT_SIZE);
    });

});
