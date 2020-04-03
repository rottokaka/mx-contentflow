/**
 * related html: blog/list.html
 */

$(function () {

    //   initialize all popovers
    $('[data-toggle="popover"]').each(function () {
        const element = $(this);
        element.popover({
            trigger: 'click hover focus',
            placement: 'bottom',
            html: true,
            content: customBlogPopover()
        });
    });

    // 自定义操作内容
    function customBlogPopover() {
        return '<a class="text-dark" href="#">评论</a><br/>'
            + '<a class="text-dark" href="#">举报</a><br>';
    }
});
