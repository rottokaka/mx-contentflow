/**
 * related html: project/config/modify.html
 */
$(function () {

    // 项目配置更新
    $('#projectConfigUpdate').click(function () {
        const configModifyForm = {
            "scope": $('#scopeSelect').val(),
            "contributionNotAllowed": $('#contributionNotAllowedSelect').val()
        };
        $.ajax({
            type: 'PATCH',
            url: '/projects/' + $('#projectConfigId').val()  + "/config",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(configModifyForm),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    $("#projectConfigModal").modal('hide');
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });
});
