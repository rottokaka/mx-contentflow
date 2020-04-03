/**
 * related html: pmc/axis.html
 */

$(function () {

    // #1 变量定义区~
    // =================================================================================================================

    const AXIS_INSERT = "AXIS_INSERT";
    const AXIS_REMOVE = "AXIS_REMOVE";
    const AXIS_DELETE = "AXIS_DELETE";
    const projectSelect = $('#projectSelect');
    const versionGroup = $('#versionGroup');
    const versionSelect = $('#versionSelect');
    const subjectGroup = $('#subjectGroup');
    const subjectSelect = $('#subjectSelect');
    const sectionGroup = $('#sectionGroup');
    const sectionSelect = $('#sectionSelect');

    // #2 函数定义区~
    // =================================================================================================================

    // 列出项目版本
    function listVersion() {
        const projectId = projectSelect.val();
        if (!$.isEmptyObject(projectId)) {
            $.ajax({
                url: "/versions/hosted?projectId=" + projectId,
                success: function (data) {
                    if (data.valid) {
                        versionSelect.empty();
                        if ($.isEmptyObject(data)) {
                            versionGroup.hide();
                        } else {
                            $.each(data.data, function (i, value) {
                                versionSelect.append("<option value=\"" + value.versionId + "\">" + value.name + "</option>");
                            });
                            versionGroup.show();
                        }
                        listSubject();
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error : function () {
                    toastr.error("ERROR!");
                }
            });
        }
    }

    // 列出所有专题
    function listSubject() {
        $.ajax({
            url: '/subjects/hosted?versionId=' + versionSelect.val(),
            success: function (data) {
                subjectSelect.empty();
                if ($.isEmptyObject(data)) {
                    subjectGroup.hide();
                } else {
                    subjectSelect.append("<option value=\"" + "" + "\">" + "请选择专题" + "</option>");
                    $.each(data.data, function (i, value) {
                        subjectSelect.append("<option value=\"" + value.subjectId + "\">" + value.name + "</option>");
                    });
                    subjectGroup.show();
                    listSection();
                }
            }
        });
    }

    // 列出所有篇章
    function listSection() {
        $.ajax({
            url: '/sections/hosted?versionId=' + versionSelect.val() + "&subjectId=" + subjectSelect.val(),
            success: function (data) {
                sectionSelect.empty();
                if ($.isEmptyObject(data)) {
                    sectionGroup.hide();
                } else {
                    sectionSelect.append("<option value=\"" + "" + "\">" + "请选择类型" + "</option>");
                    $.each(data.data, function (i, value) {
                        sectionSelect.append("<option value=\"" + value.sectionId + "\">" + value.name + "</option>");
                    });
                    sectionGroup.show();
                }
            }
        });
    }

    // 列出所有博客，通过版本，专题和类型
    function listProductByAxis() {
        // 构建查询对象
        const axisQuery = {
            "versionId": versionSelect.val(),
            "subjectId": subjectSelect.val(),
            "sectionId": sectionSelect.val(),
            "projectId": projectSelect.val()
        };
        $.ajax({
            type: 'GET',
            url: "/axis/product/export",
            /*contentType: "application/json; charset=utf-8",*/
            data: axisQuery,
            success: function (data) {
                $("#mainAxisContainer").html(data);
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    // 保存所有博客，通过版本，专题和类型
    function saveBlogByAxis(pattern) {
        // 定义一个空数组，接受数据
        const productList = [];
        if (AXIS_INSERT === pattern) {
            $("input[name='insertCheck']:checked").each(function () {
                let product = {};
                product.id = $(this).attr("blogId");
                product.type = "PRODUCT_BLOG";
                productList.push(product);
            });
        } else {
            $("input[name='updateCheck']:checked").each(function () {
                let product = {};
                product.id = $(this).attr("blogId");
                product.type = "PRODUCT_BLOG";
                productList.push(product);
            });
        }
        if (productList.length === 0) {
            alert("请选择要处理的博客！");
        } else {
            // 构建项目对象
            const axis = {
                "projectId": projectSelect.val(),
                "versionId": versionSelect.val(),
                "subjectId": subjectSelect.val(),
                "sectionId": sectionSelect.val()
            };
            // 构建管理传输对象
            const productListOnAxis = {
                "pattern": pattern,
                "products": JSON.stringify(productList),
                "axis": JSON.stringify(axis),
            };
            $.ajax({
                type: 'PUT',
                url: '/axes/products',
                dataType: "json",
                /*contentType: "application/json; charset=utf-8",*/ // 不加
                data: productListOnAxis,
                success: function (data) {
                    if (data.valid) {
                        listProductByAxis();
                        $("#blogAbbrModal").modal('hide');
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    $("#blogAbbrModal").modal('hide');
                    toastr.error("ERROR!");
                }
            });
        }
    }

    // #3 脚本初始化~
    // =================================================================================================================

    versionGroup.hide();
    listVersion();

    // #4 事件定义区~
    // =================================================================================================================

    // 项目变换事件
    projectSelect.change(function () {
        listVersion();
        listProductByAxis();
    });

    // 版本变化事件
    versionSelect.change(function () {
        listSubject();
        listSection();
        listProductByAxis();
    });


    // 专题变化事件
    subjectSelect.change(function () {
        listProductByAxis();
    });


    // 类型变化事件
    sectionSelect.change(function () {
        listProductByAxis();
    });
    // 查找按钮
    $("#searchBlog").click(function () {
        listProductByAxis();
    });

    // 投稿图标点击时间，加载博客列表
    $('#blogAbbrModal').on('show.bs.modal', function () {
        $.ajax({
            url: "/axis/product/import",
            data: {
                "versionId": versionSelect.val(),
                "subjectId": subjectSelect.val(),
                "sectionId": sectionSelect.val()
            },
            success: function (data) {
                $("#blogAbbrModalContainer").html(data);
            }
        });
    });

    // 投稿保存提交事件
    $("#submitBlogList").click(function () {
        saveBlogByAxis(AXIS_INSERT);
    });

    // 移除点击事件
    $("#removeBlog").click(function () {
        saveBlogByAxis(AXIS_REMOVE);
    });

    // 删除点击事件
    $("#deleteBlog").click(function () {
        saveBlogByAxis(AXIS_DELETE);
    });

});
