/**
 * related html: project/detail.html
 */

$(function () {

    // #1 变量定义区~
    //==================================================================================================================

    // 项目
    const project = $('#project');
    // 项目ID
    const projectId = project.attr("projectId");
    // 版本组
    const versionGroup = $('#versionGroup');
    // 版本选择框
    const versionSelect = $('#versionSelect');
    // 版本ID
    const versionId = versionSelect.val();
    // 专题条目
    const subjectList = $("#subjectList .list-group-item");
    // 类型条目
    const sectionList = $("#sectionList .list-group-item");
    // 模式：专题模式【默认】
    const pattern = "PATTERN_SUBJECT";
    // 投稿Modal
    const contributeModal = $('#contributeModal');

    // #2 函数定义区~
    //==================================================================================================================
    // 加载项目级别产品
    function searchByProjectId() {
        $.ajax({
            url: "/axis/product?projectId=" + projectId,
            success: function (data) {
                $("#mainContainer").html(data);
            }
        });
    }

    // 通过专题和类型筛选博客
    function searchBySubjectAndSection() {
        // 选择专题ID
        let subjectId = subjectList.filter(".active").attr("subjectId");
        // 选择类型ID
        let sectionId = sectionList.filter(".active").attr("sectionId");
        // 加载博客列表页面
        $.ajax({
            type: 'GET',
            url: "/axis/product",
            data: {
                "projectId": projectId,
                "versionId": versionId,
                "subjectId": subjectId,
                "sectionId": sectionId
            },
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
        customBlogPopover();
    }

    // 自定义博客弹出框
    function customBlogPopover() {
        //   initialize all popovers
        $('[data-toggle="popover"]').each(function () {
            const element = $(this);
            element.popover({
                trigger: 'click hover',
                placement: 'bottom',
                html: true,
                content: customContent()
            });
        });

        // 自定义操作内容
        function customContent() {
            let projectPopover = "";
            const remove = "移除";
            let subjectId = subjectList.filter(".active").attr("subjectId");
            let sectionId = sectionList.filter(".active").attr("sectionId");
            if (typeof (subjectId) == "undefined" && typeof (sectionId) == "undefined") {
                subjectId = "";
                sectionId = "";
                projectPopover += "<p>" + remove + "</p>";
            }
            if (typeof (subjectId) == "undefined" && typeof (sectionId) != "undefined") {
                /*$("a.contentType").each(function () {
                    projectPopover += '<a class="text-dark" href="#">' + $(this).text() + '</a><br/>';
                });*/
                projectPopover += "<p>" + remove + "</p>";
            }
            return projectPopover;
        }
    }

    // #3 脚本初始化~
    //==================================================================================================================

    // 版本选择框初始化
    $(".form-control-chosen").chosen();
    // 加载项目级别产品
    searchByProjectId();

    // #4 事件定义区~
    //==================================================================================================================

    // 投稿图标点击时间，加载博客列表
    contributeModal.on('show.bs.modal', function () {
        $.ajax({
            url: "/axis/project/contribute?projectId=" + projectId,
            success: function (data) {
                $("#contributeModalContainer").html(data);
            }
        });
    });

    // 投稿Modal隐藏后，刷新项目博客
    contributeModal.on('hidden.bs.modal', function () {
        searchByProjectId();
    });

    // 投稿提交按钮
    $("#submitBlogList").click(function () {
        // 定义一个空数组，接受数据
        const products = [];
        $("input[name='insertCheck']:checked").each(function (i) {
            let product = {};
            product.id = $(this).attr("blogId");
            product.type = "PRODUCT_BLOG";
            products.push(product);
        });
        // 构建对象
        const contributeVo = {
            "products": JSON.stringify(products)
        };
        $.ajax({
            type: 'POST',
            url: '/axes/' + projectId + "/products",
            /*contentType: "application/json; charset=utf-8",*/ // 不加
            data: contributeVo,
            success: function (data) {
                $("#contributeModal").modal('hide');
                if (data.valid) {
                    toastr.success("投稿成功！");
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                $("#contributeModal").modal('hide');
                toastr.error("ERROR!");
            }
        });
    });

    // 项目版本显示
    project.click(function () {
        window.location = '/project/' + projectId + '/detail';
    });

    // 监听版本更改
    versionSelect.change(function () {
        window.location = '/project/' + projectId + '/detail'
            + "?versionId=" + versionSelect.val();
    });

    // 专题列表设置
    subjectList.click(function () {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
        } else {
            subjectList.removeClass("active");
            $(this).addClass("active");
        }
        searchBySubjectAndSection();
    });

    // 类型导航设置
    sectionList.click(function () {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
        } else {
            sectionList.removeClass("active");
            $(this).addClass("active");
        }
        searchBySubjectAndSection();
    });

    // Github网站跳转
    $("#website").click(function () {
        const url = $(this).attr("website");
        window.open(url, '_blank');
    });

    // 项目图书化
    $("#preview").click(function () {
        const url = "/book/preview?&projectId=" + projectId + "&versionId=" + versionId + "&pattern=" + pattern;
        window.open(url, '_blank');
    });
});
