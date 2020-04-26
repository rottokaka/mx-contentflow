/**
 *  related html: blog/content-self.html
 */
$(function () {
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
});
