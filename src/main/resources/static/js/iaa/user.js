/**
 * related html: iaa/user.html
 */
$(function () {

    /* BEN ================================================================================================ 函数定义区 */

    // 列出博客
    function listUser() {
        $.ajax({
            typ: 'GET',
            url: '/roots/users',
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        })
    }

    /* END ================================================================================================ 函数定义区 */
    /* BEN ================================================================================================ 事件处理区 */
    // 新建用户
    $(".user-create").click(function () {
        $.ajax({
            url: "/users/create",
            success: function (data) {
                $("#userModalContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    // 编辑用户
    $(".user-update").click(function () {
        $.ajax({
            url: "/users/" + $(this).attr("userId") + "/update",
            data: {
                "username": $(this).attr("username")
            },
            success: function (data) {
                $("#userModalContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    // 保存用户，提交变更后，清空表单
    $("#submitUser").click(function () {
        const userInsertVO = {
            "email": $('#email').val(),
            "username": $('#username').val(),
            "password": $('#password').val(),
            "site": $('#site').val(),
            "note": $('#note').val(),
            "notice": $('#notice').val()
        };

        $.ajax({
            type: 'POST',
            url: "/users",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(userInsertVO),
            success: function (data) {
                if (data.valid) {
                    listUser();
                    toastr.success("保存成功！");
                    $('#userForm')[0].reset();
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    });

    // 删除用户
    $(".user-delete").click(function () {
        const r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: "/users/" + $(this).attr("userId"),
                success: function (data) {
                    if (data.valid) {
                        // 刷新列表
                        listUser();
                        toastr.success("删除成功！");
                    } else {
                        toastr.error(data.message);
                    }
                },
                error: function () {
                    toastr.error("error!");
                }
            });
        }
    });
    /* END ================================================================================================ 事件处理区 */
});
