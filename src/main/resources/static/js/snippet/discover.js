/**
 * related html: snippet/discover.html
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
    function discoverSnippet(page, size) {
        const keyword = $('#sKeyword').val();
        const sort = snippetSort.val();
        window.location = "/snippet/discover?keyword=" + keyword + "&page=" + page + "&size=" + size + "&sort=" + sort;
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // 选择框初始化
    $('.form-control-chosen').chosen();

    // #4 事件定义区~
    //==================================================================================================================

    // 排序选择框变化事件
    snippetSort.change(function () {
        // 片段关键字
        const keyword = $('#sKeyword').val();
        // 排序方式
        const sort = $('#snippetSort').val();
        window.location = "/snippet/discover?keyword=" + keyword + "&sort=" + sort;
    });

    // 搜索点击事件
    $('#searchSnippet').click(function () {
        // 片段关键字
        const keyword = $('#sKeyword').val();
        window.location = "/snippet/discover?keyword=" + keyword;
    });

    // 分页页码点击事件
    $('#snippetCardContainer').on('click', '.page-item', function () {
        const page = $(this).val();
        alert(page);
        discoverSnippet(page, PAGE_DEFAULT_SIZE);
    });

});
