/**
 * related html: iaa/profile.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================

    // 用户模态框
    const userModal = $('#userModal');
    const userId = $('.user-update').attr("userId");

    // #2 函数定义区~
    //==================================================================================================================

    // 加载个人信息编辑页面
    function loadUserModifyForm() {
        $.ajax({
            url: "/user/" + userId + "/modify",
            success: function (data) {
                $("#userModalContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 加载个人信息查看页面
    function loadUserVo() {
        $.ajax({
            url: "/iaa/profile",
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 更新用户，提交变更后，清空表单
    $("#submitUser").click(function () {
        const userModifyForm = {
            "website": $('#website').val(),
            "note": $('#note').val(),
            "notice": $('#notice').val()
        };

        $.ajax({
            type: 'PUT',
            url: "/users/" + userId,
            contentType: 'application/json;charset=UTF8',
            data: JSON.stringify(userModifyForm),
            success: function (data) {
                if (data.valid) {
                    userModal.modal('hide');
                    toastr.success("保存成功！");
                    $('#userForm')[0].reset();
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // #3 脚本初始化~
    //==================================================================================================================
    // #4 事件定义区~
    //==================================================================================================================

    // 用户模态框打开后，触发事件，加载个人信息编辑页面
    userModal.on('show.bs.modal', function () {
        loadUserModifyForm();
    });

    // 用户模态框关闭后，触发时间，加载个人信息查看页面
    userModal.on('hidden.bs.modal', function () {
        loadUserVo();
    });
});
