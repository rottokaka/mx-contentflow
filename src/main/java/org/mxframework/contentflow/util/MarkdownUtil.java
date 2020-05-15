package org.mxframework.contentflow.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mxframework.contentflow.constant.ccp.BlogConstant.*;
import static org.mxframework.contentflow.util.BlogUtil.getResults;

/**
 * @author mx
 */
public class MarkdownUtil {

    /**
     * Markdown 片段切分
     *
     * @return 片段集合
     */
    public static LinkedList<String> getSnippets(String markdown) {
        // 构造索引和标题级别字符串 TreeMap
        // 目标字符串的索引 确定了拆分后的文档顺序
        TreeMap<Integer, String> indexFormattedTitleMap = new TreeMap<>();
        // 标题一
        List<String> h1Style1Results = getResults(BLOG_PATTERN_H1_STYLE_1, markdown);
        h1Style1Results.forEach(h1Result -> indexFormattedTitleMap.put(markdown.indexOf(h1Result), formattedH1(h1Result, BLOG_PATTERN_H1_STYLE_1)));
        // System.lineSeparator() 使得 BLOG_PATTERN_H1_STYLE_2 正则表达式生效
        List<String> h1Style2Results = getResults(BLOG_PATTERN_H1_STYLE_2, System.lineSeparator() + markdown);
        h1Style2Results.forEach(h1Result -> indexFormattedTitleMap.put(markdown.indexOf(h1Result), formattedH1(h1Result, BLOG_PATTERN_H1_STYLE_2)));
        // 标题二
        List<String> h2Style1Results = getResults(BLOG_PATTERN_H2_STYLE_1, markdown);
        h2Style1Results.forEach(h2Result -> indexFormattedTitleMap.put(markdown.indexOf(h2Result), formattedH2(h2Result, BLOG_PATTERN_H2_STYLE_1)));
        List<String> h2Style2Results = getResults(BLOG_PATTERN_H2_STYLE_2, markdown);
        h2Style2Results.forEach(h2Result -> indexFormattedTitleMap.put(markdown.indexOf(h2Result), formattedH2(h2Result, BLOG_PATTERN_H2_STYLE_2)));
        // 确保片段顺序，拿数据的时候可以正确获取
        // 期待（理想）构造顺序：h1 片段（1） -> h2 片段（n）
        LinkedList<String> snippets = new LinkedList<>();
        List<Integer> indexList = new ArrayList<>(indexFormattedTitleMap.keySet());
        indexList.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < indexList.size(); i++) {
            Integer currentIndex = indexList.get(i);
            Integer nextIndex;
            if (i == indexList.size() - 1) {
                nextIndex = markdown.length();
            } else {
                nextIndex = indexList.get(i + 1);
            }
            // 示例：h1_title|markdown
            snippets.add(indexFormattedTitleMap.get(currentIndex) + "|" + markdown.substring(currentIndex, nextIndex));
        }
        return snippets;
    }

    /**
     * 获取匹配内容，通过正则表达式
     *
     * @param regex   正则表达式
     * @param content 内容
     * @return 返回匹配结果，允许返回<tt>null</tt>
     */
    public static String getRegexResultNullable(String regex, String content) {

        String regexResult = null;
        Pattern equal = Pattern.compile(regex);
        Matcher equalOfMatcher = equal.matcher(content);
        while (regexResult == null && equalOfMatcher.find()) {
            regexResult = equalOfMatcher.group(0).trim();
        }
        return regexResult;
    }

    public static String formattedH1(String h1, String regex) {
        String formattedH1 = null;
        if (BLOG_PATTERN_H1_STYLE_1.equals(regex)) {
            formattedH1 = "H1_" + h1.trim();
        }
        if (BLOG_PATTERN_H1_STYLE_2.equals(regex)) {
            formattedH1 = "H1_" + h1.substring(h1.lastIndexOf('#') + 1).trim();
        }
        return formattedH1;
    }

    public static String getPlainH2Title(String snippet, String regex) {
        return null;
    }

    public static String formattedH2(String h1, String regex) {
        String formattedH2 = null;
        if (BLOG_PATTERN_H2_STYLE_1.equals(regex)) {
            formattedH2 = "H2_" + h1.trim();
        }

        if (BLOG_PATTERN_H2_STYLE_2.equals(regex)) {
            formattedH2 = "H2_" + h1.substring(h1.lastIndexOf('#') + 1).trim();
        }
        return formattedH2;
    }

}
