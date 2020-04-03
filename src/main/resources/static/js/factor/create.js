/**
 * related html
 * factor/create.html
 */
$(function () {
    // 选择框初始化
    $(".chosen-first").chosen();
    // “版本”选择框初始化
    $("#versionGroup").hide();

    // 处理“版本”选择框
    function handleVersion() {
        const axis = $("#axis").val();
        if ("X" === axis || "Y" === axis) {
            const topic = $("#topic").val();
            if (topic !== "") {
                $.ajax({
                    type: 'GET',
                    url: '/factor/version',
                    dataType: "json",
                    data: {
                        "topic": topic,
                        "axis": axis
                    },
                    success: function (data) {
                        $("#version").empty();
                        $.each(data, function (i, value) {
                            $("#version").append("<option value=\"" + value.id + "\">" + value.name + "</option>");
                        });
                        $(".chosen-second").chosen();
                    },
                    error: function () {
                        toastr.error("Error");
                    }
                });
                $("#versionGroup").show();
            } else {
                alert("请先选择话题");
            }
        }
        if ("Z" === axis) {
            $("#versionGroup").hide();
        }
    }

    // “因素分类”选择框时间，修改“版本”选择框的被选值
    $("#axis").change(function () {
        handleVersion();
    });

    $("#topic").change(function () {
        handleVersion();
    });
});