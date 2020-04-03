/**
 * related html: tag/create.html
 */
$(function () {

    // #1 变量定义区~
    // =================================================================================================================

    // 默认上级ID
    const aboveId = 0;
    // 上级标签集合选择框区域
    const aboveGroup = $('#aboveTagGroup');
    // 上级标签集合选择框
    const aboveSelect = $('#aboveTagSelect');
    // 下级标签集合选择框区域
    const belowGroup = $('#belowTagListGroup');
    // 下级标签集合选择框
    const belowSelect = $('#belowTagListSelect');
    // 标签作用选择框
    const propertySelect = $("#propertySelect");

    // #2 函数定义区~
    // =================================================================================================================

    // 加载管理标签列表
    function loadAboveTagList() {
        $.ajax({
            url: "/tags/property?property=" + propertySelect.val(),
            success: function (data) {
                if (data.valid) {
                    aboveSelect.empty();
                    $.each(data.data, function (i, value) {
                        aboveSelect.append("<option value=\"0\">未选择任何管理标签</option>");
                        aboveSelect.append("<option value=\"" + value.tagId + "\">" + value.tagName + "</option>");
                    });
                    aboveSelect.chosen({
                        allow_single_deselect: true
                    });
                }
            }
        })
    }

    // 加载未被管理标签列表
    function loadBelowTagList() {
        $.ajax({
            url: "/tags/property?property=" + propertySelect.val(),
            success: function (data) {
                if (data.valid) {
                    belowSelect.empty();
                    $.each(data.data, function (i, value) {
                        belowSelect.append("<option value=\"" + value.tagId + "\">" + value.tagName + "</option>");
                    });
                    belowSelect.chosen();
                }
            }
        })
    }

    // #3 脚本初始化~
    // =================================================================================================================

    // 标签输入框初始化
    $('.form-control-tag').tagEditor({
        initialTags: [],
        delimiter: ', ',
        forceLowercase: false,
        animateDelete: 0,
        placeholder: '输入标签...'
    });
    // 隐藏标签列表
    belowGroup.hide();
    loadAboveTagList();
    aboveGroup.show();
    // 标签作用选择框初始化
    propertySelect.chosen({
        allow_single_deselect: true
    });

    // #4 事件定义区~
    // =================================================================================================================

    // Removing all tagName
    $(document).on('click', '.btn-remove-tags', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });

    // 监听标签作用选择框变化事件
    propertySelect.change(function () {
        // 性质
        const property = propertySelect.val();
        // hide 清楚数据，显示加载数据
        // 标记标签
        if ("2" === property) {
            // 清楚标签列表
            belowSelect.empty();
            belowGroup.hide();
            // 加载管理标签列表
            loadAboveTagList();
            aboveGroup.show();
        }
        // 管理标签
        if ("3" === property) {
            // 清楚管理标签列表
            aboveSelect.empty();
            aboveGroup.hide();
            // 加载未被管理标签列表
            loadBelowTagList();
            belowGroup.show();
        }
    });

    // 新建标签
    $("#tagSubmit").click(function () {
        // 标签创建表单
        const tagCreateForm = {
            "property": $('#propertySelect').val(),
            "name": $('#name').val(),
            "description": $('#description').val()
        };
        $.ajax({
            type: 'POST',
            url: '/tags',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tagCreateForm),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                    $("#tagModal").modal('hide');
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
