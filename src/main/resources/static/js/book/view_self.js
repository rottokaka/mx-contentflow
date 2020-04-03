/**
 * user/view.html
 */
$(function () {
    // 配置博客预览
    const blogEditor = editormd.markdownToHTML("content", {
        //markdown : "[TOC]\n### Hello world!\n## Heading 2", // Also, you can dynamic set Markdown text
        // htmlDecode : true,  // Enable / disable HTML tag encode.
        // htmlDecode : "style,script,iframe",  // Note: If enabled, you should filter some dangerous HTML tagName for website security.
        //toc: true,           // Table of contents
        //tocm: false,          // Using [TOCM], auto create ToC dropdown menu
        //tocTitle: "目录",          // for ToC dropdown menu button
        //tocDropdown: false,          // Enable/disable Table Of Contents dropdown menu
        //tocContainer: "#custom-toc-container",             // Custom Table Of Contents Container Selector
        //tocStartLevel: 2,              // Said from H1 to create ToC
    });
});
