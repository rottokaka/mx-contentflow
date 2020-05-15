/**
 * related html: blog/detail.html
 */
$(function () {

    // #1 变量定义区 start
    //==================================================================================================================

    // 投票类型
    const VOTE_TYPE = "VOTE_BLOG";
    // 产品类型
    const PRODUCT_TYPE = "PRODUCT_BLOG";
    // 博客ID
    const blogId = $('#blogId').val();
    // 标签集合区域
    const tagList = $('#tagList');
    // 标签新建页面
    const tagCreate = $('#tagCreate');
    // 标签模态框
    const tagModal = $('#tagModal');
    // 标签模态框容器
    const tagModalContainer = $('#tagModalContainer');
    // 项目集合区域
    const projectList = $('#projectList');
    // 项目模态框
    const projectModal = $('#projectModal');
    // 项目收录操作区域
    const projectModalContainer = $('#projectModalContainer');
    // 评论按钮组合
    const commentAction = $('#commentGroup');
    // 评论新建页面
    const commentCreate = $('#commentCreate');
    // 项目收录：模式-添加博客
    const PATTERN_INSERT = "PATTERN_INSERT";
    // 项目收录：模式-移除博客
    const PATTERN_REMOVE = "PATTERN_REMOVE";

    // #2 函数定义区 start
    //==================================================================================================================

    // 判断当前是否有用户登陆
    function isLogin() {
        return "anonymousUser" !== username;
    }

    // 判断当前是否有用户登陆，如果未登录，警告
    function isLoginWarning() {
        if ("anonymousUser" !== username) {
            return true;
        } else {
            toastr.warning("未登录任何用户~");
            return false;
        }
    }

    // 请求博客页面
    function loadBlog() {
        $.ajax({
            type: "GET",
            url: "/blog/" + blogId + "/content",
            success: function (data) {
                $("#blogContainer").html(data);
            }
        });
    }

    // 获取当前登陆用户的阅读状态
    function flushReading() {
        // 包括两部分产品：
        // 1.产品（博客）阅读概要；
        flushReadingAtProduct();
        // 2，用户的阅读详情，如果用户已登录则获取用户阅读详情，未登录，不处理
        flushReadingOfReader();
    }

    // 刷新博客阅读状态
    function flushReadingAtProduct() {
        $.ajax({
            url: "/readings/product?productId=" + blogId + "&productType=" + PRODUCT_TYPE,
            success: function (data) {
                if (data.valid) {
                    $('#counterSum').text(data.data.counterSum);
                    $('#likePercent').text(data.data.likePercent);
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("Error");
            }
        });
    }

    // 刷新用户阅读状态
    function flushReadingOfReader() {
        if (isLogin()) {
            $.ajax({
                type: "GET",
                url: "/readings?productId=" + blogId + "&productType=" + PRODUCT_TYPE,
                success: function (data) {
                    if (data.valid) {
                        if (data.data != null) {
                            // 获取喜欢状态
                            const liked = data.data.liked;
                            if (1 === liked) {
                                $("#like").removeClass('text-dark').addClass('text-success');
                            } else {
                                $("#like").removeClass('text-success').addClass('text-dark');
                            }
                            // 获取不喜欢状态
                            const disliked = data.data.disliked;
                            if (1 === disliked) {
                                $("#dislike").removeClass('text-dark').addClass('text-warning')
                            } else {
                                $("#dislike").removeClass('text-warning').addClass('text-dark')
                            }
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
    }

    // 更新阅读状态：喜欢；不喜欢
    function updateReaderOfStatus(READER_STATUS) {
        // 阅读视图对象
        const readerVo = {
            "productId": blogId,
            "productType": PRODUCT_TYPE,
            "status": READER_STATUS
        };
        $.ajax({
            type: 'PUT',
            url: '/readings',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(readerVo),
            success: function (data) {
                if (data.valid) {
                    flushReading();
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function () {
                toastr.error("ERROR!");
            }
        })
    }

    // 获取标签列表页面
    function listTagByBlog() {
        $.ajax({
            type: 'GET',
            url: '/blog/' + blogId + '/tags',
            success: function (data) {
                tagList.html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 获取项目列表页面
    function listProject() {
        $.ajax({
            type: 'GET',
            url: '/axis/product/' + blogId + '/project',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $('#projectList').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    }

    // 获取项目收录页面
    function loadProjectOnCollect() {
        $.ajax({
            type: 'GET',
            url: "/axis/product/collect?blogId=" + blogId,
            success: function (data) {
                $("#projectModalContainer").html(data);
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
        // 其实empty()就是将元素产品置空。比方这样的一个<label>name</labe>这个时候，我们如果要替换产品，可以用$("label").empty()，和$("label").text("")类似。
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
    // 配置博客预览
    editormd.markdownToHTML("content-view", {
        htmlDecode: "style,script,iframe",  // you can filter tags decode
        tocStartLevel: 2,
        tocTitle: "目录",
        tocm: true,    // Using [TOCM]
        tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
        emoji: true,
        taskList: true,
        tex: true,  // 默认不解析
        flowChart: true,  // 默认不解析
        sequenceDiagram: true,  // 默认不解析
    });

    // 加载博客
    // loadBlog();
    // 刷新阅读状态
    flushReading();
    // 加载标签列表
    listTagByBlog();
    // 加载项目列表
    listProject();
    // 加载新建评论页面
    //loadCreateComment();
    // 加载评论别表
    //listComment();

    // #4 事件定义区 start
    //==================================================================================================================

    // 修改博客
    $(".blog-update").click(function () {
        window.location.href = "/blog/" + $(this).attr("blogId") + "/modify";
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
                        window.location.href = "/user/" + username;
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
    // 1. 默认不存在“喜欢”状态；
    // 2. 如果表达状态了，则必须表达准确，不存在取消操作
    $('#like').click(function () {
        // 如果“已喜欢”，再此点击不取消，
        if (isLoginWarning() && $(this).hasClass('text-dark')) {
            updateReaderOfStatus("READER_STATUS_LIKE");
        }
    });
    // 阅读：是否不喜欢
    // 1. 默认不存在“不喜欢”状态；
    // 2. 如果表达状态了，则必须表达准确，不存在取消操作
    $('#dislike').click(function () {
        // 如果表示“不喜欢”，再此点击不取消，
        if (isLoginWarning() && $(this).hasClass('text-dark')) {
            updateReaderOfStatus("READER_STATUS_DISLIKE");
        }
    });

    // 投票的使用与取消
    $("#vote").click(function () {
        //判断，使用投票还是取消投票
        // bootstrap
        // text-info: 已投票，点击取消
        // text-dark: 未投票，点击投票
        // 博客ID
        const blogId = $(this).attr("blogId");
        // 投票图标
        const voteBlog = $(this);
        // 投票文本
        const voteSizeText = $(this).children(".voteSize");
        // 投票数据
        let voteSize = parseInt(voteSizeText.html());
        // * 取消投票
        if (isLoginWarning() && $(this).hasClass("text-info")) {

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
    /*tagList.on('click', '#insertTag', function () {
        // 加载标签输入页面
        $.ajax({
            type: 'GET',
            url: '/tags/createInInput',
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
        /!*  方法有两种，都是jQuery中获得子元素的方法
            find查找所有的子元素，会一直查找，跨层级查找；
            children 查找直接的子元素，不会跨层级查找。*!/
        // 图标颜色替换：text-info->text-danger
        $(this).find('.text-info').removeClass('text-info').addClass('text-danger');
        // 图标替换：添加图标->取消图标
        $(this).find('.fa').removeClass('fa-plus-circle').addClass('fa-ban');

    });*/

    /*// 取消，隐藏标签输入框【1】
    tagList.on('click', '#cancel', function () {
        cancelTagInput();
    });

    // 取消，隐藏标签输入框【2】
    tagCreate.on('click', '.btn-cancel-tag', function () {
        cancelTagInput();
    });*/

    // 标签标记创建与移除
    tagList.on('click', '.blog-tag', function () {
        // 判断，插入标记还是删除标记
        // bootstrap
        // btn-info:已使用，点击删除;
        // btn-secondary: 未使用，点击标记
        // 标签ID
        const tagId = $(this).attr("tagId");
        // * 移除标记
        if ($(this).hasClass("btn-info")) {
            $.ajax({
                type: 'DELETE',
                url: '/tags/product/' + blogId + "?productType=" + PRODUCT_TYPE + "&tagId=" + tagId,
                contentType: "application/json; charset=utf-8",
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
            const productTagForm = {
                "productType": PRODUCT_TYPE,
                "tagId": tagId
            };
            $.ajax({
                type: 'POST',
                url: '/tags/product/' + blogId,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(productTagForm),
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

    // 新建标签
    tagModalContainer.on('click', '#submitTagBtn', function () {
        const tags = $('#tags').val();
        if ("" === tags) {
            alert("请确定输入标签");
        } else {
            const tagsOfBlogVO = {
                "tags": tags,
                "productId": blogId,
                "productType": "PRODUCT_BLOG"
            };
            $.ajax({
                type: 'POST',
                url: '/tags/products',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(tagsOfBlogVO),
                success: function (data) {
                    if (data.valid) {
                        tagModal.modal('hide');
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
    tagModalContainer.on('click', '.btn-remove-tags', function () {
        $('.form-control-tag').next('.tag-editor').find('.tag-editor-delete').click();
    });

    // 标签模态框打开时，加载标签新建页面
    tagModal.on('show.bs.modal', function () {
        // 加载标签输入页面
        $.ajax({
            type: 'GET',
            url: '/tag/createInInput',
            success: function (data) {
                // 加载创建页面
                $('#tagModalContainer').html(data);
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 项目模态框打开时，加载项目收录页面
    projectModal.on('show.bs.modal', function () {
        loadProjectOnCollect();
    });

    // 标签模态框关闭时，刷新项目按钮集合
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    tagModal.on('hidden.bs.modal', function () {
        listTagByBlog();
    });

    // 项目modal关闭时，刷新项目按钮集合
    // hidden.bs.modal	This event is fired when the modal has finished being hidden from the user (will wait for CSS transitions to complete).
    projectModal.on('hidden.bs.modal', function () {
        listProject();
    });

    // 项目收录：添加博客
    projectModalContainer.on('click', '#insertCollection', function () {
        const projectId = $(this).attr("projectId");
        $.ajax({
            type: 'POST',
            url: '/axes/' + blogId,
            data: {
                "projectId": projectId
            },
            success: function () {
                loadProjectOnCollect();
            },
            error: function () {
                toastr.error("ERROR!");
            }
        });
    });

    // 项目收录：移除博客
    projectModalContainer.on('click', '#removeCollection', function () {
        const projectId = $(this).attr("projectId");
        $.ajax({
            type: 'DELETE',
            url: '/axes/' + blogId,
            data: {
                "projectId": projectId
            },
            success: function () {
                loadProjectOnCollect();
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
        if (isLoginWarning() || "" === content) {
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
