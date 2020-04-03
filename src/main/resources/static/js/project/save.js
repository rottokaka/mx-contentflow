/**
 * related html
 * topic/save.html
 */
$(function () {

    // 初始化
    $(".topic-save").hide();

    $("#createTopic").click(function () {
        $(".topic-save").show();
    });
    $("#backTopic").click(function () {
        $(".topic-save").hide();
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
                toastr.success("Success")
                $("#topic").html(data);
                $(".topic-save").hide();
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
});