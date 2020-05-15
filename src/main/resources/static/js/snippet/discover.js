/**
 * related html: snippet/discover.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // 分页
    const pagination = $('.pagination');
    // 分页数据大小
    const PAGE_SIZE = 7;

    // 片段排序选择框
    const snippetSort = $('#snippetSort');

    // #2 函数定义区~
    //==================================================================================================================

    // 重定向页面
    function loadPage(page) {
        const sort = snippetSort.val();
        const keyword = $('#snippetKeyword').val();
        window.location = "/snippet/discover?page=" + page + "&size=" + PAGE_SIZE + "&sort=" + sort + "&keyword=" + keyword;
    }

    // 获取当前页码
    function getCurrentNumber() {
        return parseInt($('.pagination .active').text().trim());
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // 选择框初始化
    $('.form-control-chosen').chosen();

    // #4 事件定义区~
    //==================================================================================================================

    // 分页点击事件
    pagination.on('click', '.page-item', function () {
        if (!$(this).hasClass('disabled')
            && !$(this).hasClass('pagination-previous')
            && !$(this).hasClass('pagination-next')) {
            const page = $(this).children('.page-link').text();
            loadPage(page);
        }
    });

    // 上一页点击事件
    pagination.on('click', '.pagination-previous', function () {
        if (!$(this).hasClass('disabled')) {
            const pageNumber = getCurrentNumber();
            // 如果只有两页的时候，获取不到页码，没有（.active），返回NaN，请求第一页
            if (isNaN(pageNumber)) {
                loadPage(1);
            } else {
                loadPage(pageNumber - 1);
            }
        }
    });

    // 下一页点击事件
    pagination.on('click', '.pagination-next', function () {
        if (!$(this).hasClass('disabled')) {
            const pageNumber = getCurrentNumber();
            // 如果只有两页的时候，获取不到页码，没有（.active），返回NaN，请求第二页
            if (isNaN(pageNumber)) {
                loadPage(2)
            } else {
                loadPage(parseInt($('.pagination .active').text().trim()) + 1);
            }
        }
    });

    // 跳转事件
    $('.page-jump-input').keyup(function (event) {
        if (event.keyCode === 13) {
            const page = parseInt($('.page-jump-input').val());
            const totalPages = parseInt($('#pageTotalPages').val());
            if (isNaN(page)) {
                loadPage(totalPages);

            } else if (page > totalPages) {
                loadPage(totalPages);
            } else if (page < 1) {
                loadPage(1)
            } else {
                loadPage(page)
            }
        }
    });

    // 排序选择框变化事件
    snippetSort.change(function () {
        // 片段关键字
        const keyword = $('#snippetKeyword').val();
        // 排序方式
        const sort = $('#snippetSort').val();
        window.location = "/snippet/discover?keyword=" + keyword + "&sort=" + sort;
    });

    // 搜索点击事件
    $('#searchSnippet').click(function () {
        // 片段关键字
        const keyword = $('#snippetKeyword').val();
        window.location = "/snippet/discover?keyword=" + keyword;
    });

    // 分页页码点击事件
    $('#snippetCardContainer').on('click', '.page-item', function () {
        const page = $(this).val();
        alert(page);
        discoverSnippet(page, PAGE_DEFAULT_SIZE);
    });

});
