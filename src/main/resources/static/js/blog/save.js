/**
 * related html
 * blog/save.html
 */

$(function () {

    $("#createBlog").click(function () {
        var blogVo = {
            "code": $('#code').val(),
            "title": $('#title').val(),
            "summary": $('#summary').val(),
            "content": $('#content').val(),
            "tags": $('.form-control-tag').val(),
            "topic": {"code": $('.btn-topic').find(".btn-dark").attr("value")}
        };
        $.ajax({
            type: 'POST',
            url: '/blog',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(blogVo),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    window.location = data.data;
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    $(".btn-topic").on('click', '.btn', function () {

        // light->dark
        if ($(this).hasClass('btn-light')) {
            var tempValue = $(".btn-topic").find('.btn-dark').length;
            if (tempValue < 1) {
                $(this).removeClass('btn-light');
                $(this).addClass('btn-dark');
            } else {
                toastr.warning("只允许选定一个话题");
            }
        }
        // dark->light
        else if ($(this).hasClass('btn-dark')) {
            $(this).removeClass('btn-dark');
            $(this).addClass('btn-light');
        }
    });

    $("#saveTopic").click(function () {
        var topicVo = {
            "name": $('#name').val(),
            "description": $('#description').val()
        };
        $.ajax({
            type: 'POST',
            url: '/topic',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(topicVo),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    $.ajax({
                        type: 'GET',
                        url: '/topic',
                        contentType: "application/json; charset=utf-8"
                    });
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 初始化标签
    $('.form-control-tag').tagsInput({
        'defaultText': 'Add Tags',
        width: "100%",
        height: "100%"
    });
});