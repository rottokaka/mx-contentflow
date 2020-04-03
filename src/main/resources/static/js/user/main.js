/**
 * root~user
 */
$(function () {

    // 根据用户名、页面索引、页面大小获取用户列表
    function getUersByName() {
        $.ajax({
            url: "/user",
            contentType: 'application/json',
            data: {
                "async": true
            },
            success: function (data) {
                $("#menuContent").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    }

    // 创建用户
    $("#createUser").click(function () {
        $.ajax({
            url: "/user/create",
            success: function (data) {
                $("#userModalContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    // 编辑用户
    $("#menuContent").on("click", ".user-update", function () {
        $.ajax({
            url: "/user/" + $(this).attr("user-code") + "/update",
            success: function (data) {
                $("#userFormContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    // 提交变更后，清空表单
    $("#submitUser").click(function () {
        $.ajax({
            type: 'POST',
            url: "/user",
            data: $("#userForm").serialize(),
            success: function (data) {
                $('#userForm')[0].reset();
                if (data.valid) {
                    $(".root-menu .list-group-item:first").trigger("click");
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
    $("#menuContent").on("click", ".user-delete", function () {
        $.ajax({
            type: 'DELETE',
            url: "/user/" + $(this).attr("user-code") + "/delete",
            success: function (data) {
                if (data.valid) {
                    // 从新刷新主界面
                    $(".root-menu .list-group-item:first").trigger("click");
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });
});