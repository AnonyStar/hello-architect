package com.export.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: zhoucx
 * @time: 2021/3/17 16:26
 */
public class PlaceholderUtils {


    public static String escapeToMark(String xmlStr) {
        if (StringUtil.isBlank(xmlStr)) return xmlStr;
        xmlStr = xmlStr.replaceAll("\\\\\\\\", "~0~");
        xmlStr = xmlStr.replaceAll("\\\\\\{", "~1~");
        xmlStr = xmlStr.replaceAll("\\\\\\}", "~2~");
        xmlStr = xmlStr.replaceAll("\\\\\\[", "~3~");
        xmlStr = xmlStr.replaceAll("\\\\\\]", "~4~");
        xmlStr = xmlStr.replaceAll("\\\\#", "~5~");
        xmlStr = xmlStr.replaceAll("\\\\\\*", "~6~");
        xmlStr = xmlStr.replaceAll("\\\\@", "~7~");
        xmlStr = xmlStr.replaceAll("\\\\\\$", "~8~");
        xmlStr = xmlStr.replaceAll("\\\\\\^", "~9~");
        return xmlStr;
    }


    public static String markToEscape(String xmlStr) {
        if (StringUtils.isBlank(xmlStr)) return xmlStr;
        xmlStr = xmlStr.replaceAll("~0~", "\\\\\\\\");
        xmlStr = xmlStr.replaceAll("~1~", "\\{");
        xmlStr = xmlStr.replaceAll("~2~", "}");
        xmlStr = xmlStr.replaceAll("~3~", "\\[");
        xmlStr = xmlStr.replaceAll("~4~", "]");
        xmlStr = xmlStr.replaceAll("~5~", "#");
        xmlStr = xmlStr.replaceAll("~6~", "\\*");
        xmlStr = xmlStr.replaceAll("~7~", "@");
        xmlStr = xmlStr.replaceAll("~8~", "\\$");
        xmlStr = xmlStr.replaceAll("~9~", "\\^");
        return xmlStr;
    }


    public static String IfTagHandle(String xmlStr) {
        String xmlStrTemp = "";
        if (xmlStr == null) return null;
        if (xmlStr.length() == 0) return "";
        while (xmlStr.contains("<#if")) {
            xmlStrTemp += StringUtil.substringBefore(xmlStr, "<#if");
            xmlStrTemp += "<#if ";
            String xmlStrSubfix = StringUtil.substringAfter(xmlStr, "<#if");
            String tagContent = StringUtil.substringBefore(xmlStrSubfix, ">");
            String cont = StringUtil.substringBetween(tagContent, "content=\"", "\"");
            xmlStrTemp += StringUtils.substringBefore(cont, "@") + ")?? >";
            xmlStr = StringUtil.substringAfter(xmlStrSubfix, ">");
        }
        xmlStrTemp += xmlStr;
        return xmlStrTemp;
    }

    public static String ListTagHandle(String xmlStrTemp) {
        String xmlStrNew = "";
        if (xmlStrTemp == null) return null;
        if (xmlStrTemp.length() == 0) return "";
        while (xmlStrTemp.contains("<#list")) {
            xmlStrNew += com.kmood.utils.StringUtil.substringBefore(xmlStrTemp, "<#list");
            xmlStrNew += "<#list ";
            String xmlStrSubfix = com.kmood.utils.StringUtil.substringAfter(xmlStrTemp, "<#list");
            String tagContent = com.kmood.utils.StringUtil.substringBefore(xmlStrSubfix, ">");
            String cont = com.kmood.utils.StringUtil.substringBetween(tagContent, "content=\"", "\"");
            xmlStrNew += cont.replaceAll("@", " as ") + " >";
            xmlStrTemp = com.kmood.utils.StringUtil.substringAfter(xmlStrSubfix, ">");
        }
        xmlStrNew += xmlStrTemp;
        return xmlStrNew;
    }


    public static String BraceTagHandle(String xmlStr) {
        if (xmlStr == null) return null;
        if (xmlStr.length() == 0) return "";
        String[] arr = com.kmood.utils.StringUtil.substringsBetween(xmlStr, "{", "}");
        if (arr == null) return xmlStr;
        for (String str : arr) {
            String replaceStr = "{" + str + "}";
            //去除不显示字符
            str = str.replaceAll("[\\x00-\\x1F | \\x7F ]", "");
            String s = "${(" + str + ")!\"\"}";
            if (!str.contains(".")) {
                xmlStr = xmlStr.replace(replaceStr, s);
                continue;
            }
            String cond = "";
            int length = str.length();
            for (int one = str.indexOf('.'); one < length - 1 && one != -1; one = str.indexOf('.', one + 1)) {
                cond += " (";
                cond += str.substring(0, one);
                cond += ")?? &&";
            }
            cond = cond.substring(0, cond.length() - 3);

            xmlStr = xmlStr.replace(replaceStr, "<#if " + cond + " >" + s + "</#if>");
        }
        return xmlStr;
    }
}
