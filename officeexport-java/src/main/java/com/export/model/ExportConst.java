package com.export.model;

/**
 * @author: zhoucx
 * @time: 2021/3/17 15:17
 */
public class ExportConst {

    public  static  final String[] PHARR = new String[]{"{","}","[","]","#","*","$","@","^"};
    public  static  final String PHSTR = "[]{}*#$@^";
    //需要转义的字符
    //左大括号
    public static final  String BRACE_L = "\\{";
    //右大括号
    public static final  String BRACE_R = "}";
    //左方括号
    public static final  String BRACKET_L = "\\[";
    //右方括号
    public static final  String BRACKET_R = "]";
    //#号
    public static final  String POUND = "#";
    //*号
    public static final  String X = "\\*";
    //@号
    public static final  String A = "@";
    //$号
    public static final  String D = "$";
    //^号
    public static final  String P = "^";

    //需要转义的字符
    //左大括号
    public static final  Character BRACE_LC = '{';
    //右括号
    public static final  Character BRACE_RC = '}';
    //左方括号
    public static final  Character BRACKET_LC = '[';
    //右方括号
    public static final  Character BRACKET_RC = ']';
    //#号
    public static final  Character POUNDC = '#';
    //*号
    public static final  Character XC = '*';
    //@号
    public static final  Character AC = '@';
    //$号
    public static final  Character DC = '$';
    //^号
    public static final  Character PC = '^';
    //转义后
    //左大括号
    // \{
    public static final  String BRACE_L_ = "\\1\\";
    //右大括号
    // \}
    public static final  String BRACE_R_ = "\\2\\";
    //左方括号
    // \[
    public static final  String BRACKET_L_ = "\\3\\";
    //右方括号
    // \]
    public static final  String BRACKET_R_ = "\\4\\";
    //#号
    // \#
    public static final  String POUND_ = "\\5\\";
    //*号
    // \*
    public static final  String X_ = "\\6\\";
    //@号
    // \@
    public static final  String A_ = "\\7\\";
    //$号
    public static final  String D_ = "\\8\\";
    //$号
    // \$
    public static final  String P_ = "\\9\\";
    //^号
    // \^
    public static final  String F_ = "\\10\\";

    //占位符匹配
    //标识表格行数组
    public static final  String ARRLIST_TABLE_L = "[#";
    public static final  String ARRLIST_TABLE_R = "#]";
    //标识文本的数组
    public static final  String ARRLIST_TEXT_R = "[*";
    public static final  String ARRLIST_TEXT_L = "*]";
    //标识对象
    public static final  String OBJECT_R = "}";
    public static final  String OBJECT_L = "{";

    //标识数组对象名
    public static final  String AS = "@";
    public static final  String ARRLIST_TEXT_NAME = "*";
    public static final  String ARRLIST_TABLE_NAME = "#";
    public static final  String MERGE_UNIT = "$";

}
