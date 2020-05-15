package org.mxframework.contentflow.util;

import org.mxframework.contentflow.constant.ccp.BlogConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mxframework.contentflow.constant.ccp.BlogConstant.BLOG_PATTERN_H1_STYLE_1;
import static org.mxframework.contentflow.constant.ccp.BlogConstant.BLOG_PATTERN_H1_STYLE_2;

/**
 * BlogUtil: 博客工具类
 *
 * @author mx
 */
public class BlogUtil {

    /**
     * 获取文本的标题，通过标题正则表达式
     *
     * @param snippet 片段
     * @return 返回标题，如果没有匹配结果，返回<tt>null</tt>
     */
    public static String getTitleNullable(String snippet) {
        snippet = snippet.substring(snippet.indexOf("|") + 1);
        String title = BlogUtil.getRegexResultNullable(BLOG_PATTERN_H1_STYLE_1, snippet);
        if (title == null) {
            // System.lineSeparator() 使得 BLOG_PATTERN_H1_STYLE_2 正则表达式生效
            title = BlogUtil.getRegexResultNullable(BLOG_PATTERN_H1_STYLE_2, System.lineSeparator() + snippet);
            if (title != null) {
                title = title.substring(title.lastIndexOf("#") + 1).trim();
            }
        }
        return title;
    }

    /**
     * 对于markdown文档的自动拆分，现有两种匹配结果：标题和概览
     *
     * @param regex   正则表达式
     * @param content 内容
     * @return String
     */
    public static String getRegexResultNullable(String regex, String content) {

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

    public static List<String> getResults(String regex, String content) {
        List<String> h2String = new ArrayList<>();
        Pattern equal = Pattern.compile(regex);
        Matcher equalOfMatcher = equal.matcher(content);
        while (equalOfMatcher.find()) {
            String regexResult = equalOfMatcher.group();
            h2String.add(regexResult.trim());
        }
        return h2String;
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
        System.out.println(getRegexResultNullable(BlogConstant.BLOG_PATTERN_SUMMARY, content));
    }
}
