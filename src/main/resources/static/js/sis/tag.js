/**
 * related html: sis/tag-person.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================
    // #2 函数定义区~
    //==================================================================================================================

    // 列出标签
    function listTag() {
        $.ajax({
            url: "/sis/tag",
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 搜索标签
    $("#tagSearch").click(function () {
        listTag();
    });

    // 加载标签列表
    $('#tagModal').on('hidden.bs.modal', function () {
        listTag();
    });

    // 删除标签
    $(".tag-delete").click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/tags/" + $(this).attr("tagId"),
                success: function (data) {
                    if (data.valid) {
                        // 刷新列表
                        toastr.success(data.message);
                        listTag();
                        $("#tagModal").modal('hide');
                    } else {
                        toastr.error(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 加载标签新建页面
    $(".tag-create").click(function () {
        $.ajax({
            type: 'GET',
            url: "/tag/create",
            success: function (data) {
                $("#tagModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 加载标签更新页面
    $(".tag-update").click(function () {
        $.ajax({
            type: 'GET',
            url: "/tag/" + $(this).attr("tagId") + "/modify",
            success: function (data) {
                $("#tagModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

});
