/**
 * related html: blog/view.html
 */
$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    // 投票类型
    const VOTE_TYPE = "VOTE_BLOG";
    // 阅读类型
    const READER_TYPE = "READ_BLOG";
    // 博客ID
    const blogId = $('#blog').attr("blogId");
    // 标签集合区域
    const tagList = $('#tagList');
    // 标签新建页面
    const tagCreate = $('#tagCreate');
    // 话题集合区域
    const topicList = $('#topicList');
    // 话题Modal
    const topicModal = $('#topicModal');
    // 话题收录操作区域
    const topicModalContainer = $('#topicModalContainer');
    // 评论按钮组合
    const commentAction = $('#commentGroup');
    // 评论新建页面
    const commentCreate = $('#commentCreate');
    // 话题收录：模式-插入博客
    const PATTERN_INSERT = "PATTERN_INSERT";
    // 话题收录：模式-移除博客
    const PATTERN_REMOVE = "PATTERN_REMOVE";

    // #2 函数定义区 start
    //==================================================================================================================

    // 判断当前是否有用户登陆
    function isLogin() {
        if ("anonymousUser" !== username) {
            return true;
        } else {
            toastr.warning("未登录任何用户");
            return false;
        }
    }

    // 请求博客页面
    function loadBlog() {
        $.ajax({
            type: 'GET',
            url: "/blogs/" + blogId,
            data: {
                "async": true,
            },
            success: function (data) {
                $("#blogContainer").html(data);
            }
        });
    }

    // 获取当前登陆用户的阅读状态
    function flushReaderStatus() {
        $.ajax({
            type: "GET",
            url: "/readers/status?contentId=" + blogId + "&contentType=" + READER_TYPE,
            success: function (data) {
                if (data.valid) {
                    // 获取喜欢状态
                    const liked =  data.data.liked;
                    if (1 === liked) {
                        $("#like").removeClass('text-dark').addClass('text-success');
                    }
                    // 获取不喜欢状态
                    const disliked = data.data.disliked;
                    if (1 === disliked) {
                        $("#dislike").removeClass('text-dark').addClass('text-warning')
                    }
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    // 更新阅读状态：喜欢；不喜欢
    function readerOfStatus(READER_STATUS) {
        // 阅读视图对象
        const readerVo = {
            "typeId": blogId,
            "type": READER_TYPE,
            "status": READER_STATUS
        };
        $.ajax({
            type: 'POST',
            url: '/readers/ofStatus',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(readerVo),
            success: function (data) {
                if (data.valid) {
                    toastr.success(data.message);
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        })
    }

    // 获取标签列表页面
    function listTagByBlog() {
        $.ajax({
            type: 'GET',
            url: '/blogs/' + blogId + '/tagName',
            success: function (data) {
                tagList.html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 取消标签输入框
    function cancelTagInput() {
        // 清空标签输入区域
        tagCreate.empty();
        // 确定取消标签按钮
        const cancelBtn = $('#cancel');
        // 添加按钮变为取消按钮
        cancelBtn.attr('id', "insertTag");
        cancelBtn.title = "添加标签";
        // 文字替换：添加标签->取消
        cancelBtn.find('.text-dark').text('添加标签');
        /*  方法有两种，都是jQuery中获得子元素的方法
            find查找所有的子元素，会一直查找，跨层级查找；
            children 查找直接的子元素，不会跨层级查找。*/
        // 图标颜色替换：text-info->text-danger
        cancelBtn.find('.text-danger').removeClass('text-danger').addClass('text-info');
        // 图标替换：添加图标->取消图标
        cancelBtn.find('.fa').removeClass('fa-ban').addClass('fa-plus-circle');
    }

    // 获取话题列表页面
    function listTopic() {
        $.ajax({
            type: 'GET',
            url: '/topics/inBtnByBlog',
            contentType: "application/json; charset=utf-8",
            data: {
                blogId: blogId
            },
            success: function (data) {
                $('#topicList').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 获取话题收录页面
    function loadTopicOnCollect() {
        $.ajax({
            type: 'GET',
            url: "/blogs/topics/collect",
            data: {
                "blogId": blogId
            },
            success: function (data) {
                $("#topicModalContainer").html(data);
            }
        });
    }

    // 加载新建评论页面
    function loadCreateComment() {
        $.ajax({
            type: 'GET',
            url: '/comments/create',
            success: function (data) {
                // 加载创建页面
                $('#commentCreate').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 取消评论
    function cancelComment() {
        // 取消添加评论按钮
        const cancelBtn = commentAction.children('.text-secondary');
        // 取消按钮变为添加按钮
        cancelBtn.removeClass('text-secondary').addClass('text-success');
        cancelBtn.attr('id', "addComment");
        cancelBtn.title = "添加评论";
        cancelBtn.children('.fa').removeClass('fa-ban').addClass('fa-plus-circle');
        // 清空元素
        // 其实empty()就是将元素内容置空。比方这样的一个<label>name</labe>这个时候，我们如果要替换内容，可以用$("label").empty()，和$("label").text("")类似。
        // 如果$("label").remove().就是将这个label元素直接移除了。那么审查元素的时候，就个元素就不存在了。
        // 原文：https://blog.csdn.net/sxs161028/article/details/74194202
        commentCreate.empty();
    }

    // 加载评论列表
    function listComment() {
        $.ajax({
            type: 'GET',
            url: '/comments',
            contentType: "application/json; charset=utf-8",
            data: {
                blogId: blogId
            },
            success: function (data) {
                $('#commentList').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // #3 脚本初始化~
    //==================================================================================================================

    // 加载博客
    loadBlog();
    // 刷新阅读状态
    flushReaderStatus();
    // 加载标签列表
    listTagByBlog();
    // 加载话题列表
    listTopic();
    // 加载新建评论页面
    loadCreateComment();
    // 加载评论别表
    listComment();

    // #4 事件定义区 start
    //==================================================================================================================

    // 编辑博客
    $(".blog-update").click(function () {
        window.location.href = "/blogs/update" + "?blogId=" + $(this).attr("blogId");
    });


    // 删除博客
    $(".blog-delete").click(function () {
        let r = confirm("确认删除？");
        if (r) {
            $.ajax({
                type: 'DELETE',
                url: '/blogs/' + $(this).attr("blogId"),
                success: function (data) {
                    if (data.valid) {
                        toastr.success("删除成功！");
                        window.location.href = "/users/" + username;
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 阅读：是否喜欢
    // 1. 默认不存在喜欢状态；
    // 2. 如果表达状态了，则必须表达准确，不存在取消操作
    $('#like').click(function () {
        // 如果“已喜欢”，再此点击不取消，
        if (isLogin() && $(this).hasClass('text-dark')) {
            // 更新数据
            readerOfStatus("READER_STATUS_LIKE");
            $(this).removeClass('text-dark').addClass('text-success');
            // 确认“不喜欢”图标
            const dislike = $('#dislike');
            if (dislike.hasClass('text-warning')) {
                dislike.removeClass('text-warning').addClass('text-dark');
            }
        }
    });

    $('#dislike').click(function () {
        // 如果表示“不喜欢”，再此点击不取消，
        if (isLogin() && $(this).hasClass('text-dark')) {
            readerOfStatus("READER_STATUS_DISLIKE");
            $(this).removeClass('text-dark').addClass('text-warning');
            // 确认“喜欢”图标
            const like = $('#like');
            if (like.hasClass('text-success')) {
                like.removeClass('text-success').addClass('text-dark');
            }
        }
    });

    // 投票的使用与取消
    $("#vote").click(function () {
        //判断，使用投票还是取消投票
        // bootstrap
        // text-info: 已投票，点击取消
        // text-dark: 未投票，点击投票
        // * 取消投票
        if (isLogin() && $(this).hasClass("text-info")) {
            // 博客ID
            const blogId = $(this).attr("blogId");
            // 投票图标
            const voteBlog = $(this);
            // 投票文本
            const voteSizeText = $(this).children(".voteSize");
            // 投票数据
            let voteSize = parseInt(voteSizeText.html());
            $.ajax({
                type: 'DELETE',
                url: '/votes/' + blogId,
                success: function (data) {
                    if (data.valid) {
                        // 取消投票成功后，修改class，和数字，呈现可使用状态
                        voteBlog.removeClass("text-info").addClass("text-dark");
                        voteSize = voteSize - 1;
                        voteSizeText.html(voteSize);
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
        // * 使用投票
        if (isLogin() && $(this).hasClass("text-dark")) {
            const vote = {
                "blog": {
                    id: blogId
                },
            };
            $.ajax({
                type: 'POST',
                url: '/votes',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(vote),
                success: function (data) {
                    if (data.valid) {
                        // 使用标记成功后，修改class，和数字，呈现可取消状态
                        voteBlog.removeClass("text-dark").addClass("text-info");
                        voteSize = voteSize + 1;
                        voteSizeText.html(voteSize);
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 添加标签
    tagList.on('click', '#insertTag', function () {
        // 加载标签输入页面
        $.ajax({
            type: 'GET',
            url: '/tagName/createInInput',
            success: function (data) {
                // 加载创建页面
                $('#tagCreate').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
        // 添加按钮变为取消按钮
        $(this).attr('id', "cancel");
        $(this).title = "取消";
        // 文字替换：添加标签->取消
        $(this).find('.text-dark').text('取消');
        /*  方法有两种，都是jQuery中获得子元素的方法
            find查找所有的子元素，会一直查找，跨层级查找；
            children 查找直接的子元素，不会跨层级查找。*/
        // 图标颜色替换：text-info->text-danger
        $(this).find('.text-info').removeClass('text-info').addClass('text-danger');
        // 图标替换：添加图标->取消图标
        $(this).find('.fa').removeClass('fa-plus-circle').addClass('fa-ban');

    });

    // 取消，隐藏标签输入框【1】
    tagList.on('click', '#cancel', function () {
        cancelTagInput();
    });

    // 取消，隐藏标签输入框【2】
    tagCreate.on('click', '.btn-cancel-tagName', function () {
        cancelTagInput();
    });

    // 标签标记创建与移除
    tagList.on('click', '.blog-tag', function () {
        // 判断，插入标记还是删除标记
        // bootstrap
        // btn-info:已使用，点击删除;
        // btn-secondary: 未使用，点击标记
        // 标签ID
        const tagName = $(this).attr("tagName");
        // * 移除标记
        if ($(this).hasClass("btn-info")) {
            // 标签更新的目的 0：标记标签，添加该博客 1：取消标签，移除该博客
            const purpose = 1;
            // 构建标签视图数据
            const tagView = {
                "name": tagName,
                "purpose": purpose,
                "blog": {
                    "id": blogId
                }
            };
            $.ajax({
                type: 'POST',
                url: '/blogs/tagName/markup',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(tagView),
                success: function (data) {
                    if (data.valid) {
                        listTagByBlog();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
        // * 创建标记
        if ($(this).hasClass("btn-outline-info")) {
            // 标签更新的目的 0：标记标签，添加该博客 1：取消标签，移除该博客
            const purpose = 0;
            // 构建标签视图数据
            const tagView = {
                "name": tagName,
                "purpose": purpose,
                "blog": {
                    "id": blogId
                }
            };
            $.ajax({
                type: 'POST',
                url: '/blogs/tagName/markup',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(tagView),
                success: function (data) {
                    if (data.valid) {
                        listTagByBlog();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 插入标签
    tagCreate.on('click', '#submitTagBtn', function () {
        const tags = $('#tagName').val();
        if ("" === tags) {
            alert("请确定输入标签");
        } else {
            const tagView = {
                "tags": tags,
                "blog": {
                    "id": $('#blog').attr("blogId")
                }
            };
            $.ajax({
                type: 'POST',
                url: '/blogs/tagName/inTags',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(tagView),
                success: function (data) {
                    if (data.valid) {
                        // 加载标签集合
                        listTagByBlog();
                        // 取消添加标签
                        cancelTagInput();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });

    // 清空所有标签
    tagCreate.on('click', '.btn-remove-tagName', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });

    // 话题modal打开时，加载话题收录页面
    topicModal.on('show.bs.modal', function () {
        loadTopicOnCollect();
    });

    // 话题modal关闭时，刷新话题按钮集合
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    topicModal.on('hidden.bs.modal', function () {
        listTopic();
    });

    // 话题收录：添加博客
    topicModalContainer.on('click', '#insertCollection', function () {
        const topicId = $(this).attr("topicId");
        $.ajax({
            type: 'POST',
            url: '/blogs/topics/?topicId=' + topicId + '&blogId=' + blogId,
            success: function () {
                loadTopicOnCollect();
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 话题收录：移除博客
    topicModalContainer.on('click', '#removeCollection', function () {
        const topicId = $(this).attr("topicId");
        $.ajax({
            type: 'DELETE',
            url: '/blogs/topics/?blogId=' + blogId + "&topicId=" + topicId,
            success: function () {
                loadTopicOnCollect();
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 添加评论
    commentAction.on('click', '#addComment', function () {
        // 添加按钮变为取消按钮
        $(this).removeClass('text-success').addClass('text-secondary');
        $(this).attr('id', "cancelComment");
        $(this).title = "取消评论";
        $(this).children('.fa').removeClass('fa-plus-circle').addClass('fa-ban');
        // 加载新建评论页面
        loadCreateComment();
    });

    // 取消评论
    commentAction.on('click', '#cancelComment', function () {
        cancelComment();
    });
    // 取消评论，新建页面的快捷取消按钮
    commentCreate.on('click', '#cancelCommentBtn', function () {
        cancelComment();
    });
    // 提交评论
    commentCreate.on('click', '#submitCommentBtn', function () {
        const content = $('#commentContent').val().trim();
        if ("" === content) {
            alert("请确定输入评论");
        } else {
            const comment = {
                "content": content,
                "blog": {
                    "id": $('#blog').attr("blogId")
                }
            };
            $.ajax({
                type: 'POST',
                url: '/comments',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(comment),
                success: function (data) {
                    if (data.valid) {
                        // 加载评论
                        listComment();
                        // 取消评论
                        cancelComment();
                        toastr.success(data.message);
                    } else {
                        toastr.warning(data.message);
                    }
                },
                error: function () {
                    toastr.error("ERROR!");
                }
            });
        }
    });
});
