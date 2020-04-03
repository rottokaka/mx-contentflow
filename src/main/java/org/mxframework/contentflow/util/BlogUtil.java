package org.mxframework.contentflow.util;

import org.mxframework.contentflow.constant.ccp.BlogConstant;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BlogUtil: 博客工具类
 *
 * @author mx
 */
public class BlogUtil {

    /**
     * 详细提交
     * 前提：标题可选，概要为空，内容非空
     * 结果：标题可选，概要可选，内容非空
     *
     * @param blog 博客
     */
    public static void handleContent(Blog blog) {
        // 标题处理
        if (null == blog.title() || "".equals(blog.title().trim())) {
            blog.setTitle(BlogUtil.getRegexResult(BlogConstant.BLOG_PATTERN_TITLE, blog.content()));
        }
        // 概要处理
        blog.setSummary(BlogUtil.getRegexResult(BlogConstant.BLOG_PATTERN_SUMMARY, blog.content()));
    }

    /**
     * 对于markdown文档的自动拆分，现有两种匹配结果：标题和概览
     *
     * @param regex   正则表达式
     * @param content 内容
     * @return String
     */
    public static String getRegexResult(String regex, String content) {

        String regexResult = null;
        Pattern equal = Pattern.compile(regex);
        Matcher equalOfMatcher = equal.matcher(content);
        // 确认唯一值，通过工具Regex Match Tracer 2.1
        /*if (equalOfMatcher.matches()) {

        }*/
        while (regexResult == null && equalOfMatcher.find()) {
            regexResult = equalOfMatcher.group(0).trim();
        }
        return regexResult;
    }

    public static void main(String[] args) {
        String content = "\n" +
                "\n" +
                "This is an H1\n" +
                "> asdfsdfsdfsdfasdf \n" +
                "> 第二 \n" +
                "> 第三 \n" +
                "sdf  \n" +
                "sdf sadf sadf  \n" +
                "asdf asdf asdfas d  \n" +
                "sdfsdf asdf asdfasd f\n" +
                "\n" +
                "# sfasdfa s\n" +
                "asdfasdfasdfasdf\n" +
                "### sgfasdfasdf";
        System.out.println(getRegexResult(BlogConstant.BLOG_PATTERN_SUMMARY, content));
    }
}
