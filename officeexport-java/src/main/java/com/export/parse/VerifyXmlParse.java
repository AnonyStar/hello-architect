package com.export.parse;

import com.export.exception.ExportWordException;
import com.export.model.XmlModel;
import com.export.model.XpathEnum;
import com.export.utils.PlaceholderUtils;
import com.export.utils.StringUtil;
import com.kmood.basic.PlaceHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/17 16:06
 */
@Slf4j
public class VerifyXmlParse extends AbstractWordParser {

    private VerifyXmlParse() {
    }

    @Override
    protected void handler(XmlModel xmlModel) {
        Element element = xmlModel.getDocumentPart();

        final List<Element> wtNodeList = xmlModel.getNoteList4Element(element, XpathEnum.W_T);
        if (CollectionUtils.isEmpty(wtNodeList)) {
            //log.info(" document module does not exist wt tag......");
            return;
        }
        processEscape(wtNodeList);

        //校验段落即语法校验
        processParg(xmlModel, element);

    }

    /**
     * 处理段落
     *
     * @param xmlModel
     * @param element
     */
    private void processParg(XmlModel xmlModel, Element element) {
        final List<Element> paragList = xmlModel.getNoteList4Element(element, XpathEnum.W_P);
        StringBuilder wpStr = new StringBuilder();
        for (int i = 0; i < paragList.size(); i++) {
            Element node = paragList.get(i);
            List<Element> textNodeList = xmlModel.getNoteList4Element(node, XpathEnum.W_T);

            for (int j = 0; j < textNodeList.size(); j++) {
                Node TextNode = textNodeList.get(j);
                wpStr.append(StringUtil.removeInvisibleChar(TextNode.getText()));
            }
        }
        varifySyntax(wpStr.toString());
    }

    /**
     * 处理标记为转义字符的内容.
     *
     * @param wtNodeList
     */
    private void processEscape(List<Element> wtNodeList) {
        int s = wtNodeList.size();
        for (int i = 0; i < s; i++) {
            Node node = wtNodeList.get(i);
            String text = PlaceholderUtils.escapeToMark(node.getText());
            node.setText(text);

            // 处理 转义符 \\ 分离情况
            if ((s - 1) != i && text.endsWith("\\")) {
                Node nextNode = wtNodeList.get(i + 1);
                String nextText = nextNode.getText();
                int len = nextText.length();
                if (len == 1) {
                    nextNode.setText("");
                    node.setText(PlaceholderUtils.escapeToMark(text + nextText));
                } else if (len > 1) {
                    nextNode.setText(nextText.substring(1));
                    node.setText(PlaceholderUtils.escapeToMark(text + nextText.substring(0, 1)));
                }
            }
        }
    }


    private void varifySyntax(String data) {
        data = com.kmood.utils.StringUtil.removeInvisibleChar(data);
        String errorInfor = "";
        Character errorChar = null;
        int errorIndex = 0;
        int length = data.length();
        if (length == 0) return;
        char[] chars = data.toCharArray();
        ArrayList<Character> stack = new ArrayList<Character>();
        ArrayList<Character> charArr = new ArrayList<Character>();
        ArrayList<Integer> indexArr = new ArrayList<Integer>();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (PlaceHolder.PHSTR.indexOf(c) != -1) {
                charArr.add(c);
                indexArr.add(i);
            }
        }
        int dl = charArr.size();
        for (int i = 0; i < dl; i++) {
            Character c = charArr.get(i);
            // 第一次循环时碰到 *#@ ]} 错误跳出
            if (i == 0 && (c == PlaceHolder.AC ||
                    c == PlaceHolder.BRACE_RC ||
                    c == PlaceHolder.BRACKET_RC ||
                    c == PlaceHolder.XC ||
                    c == PlaceHolder.POUNDC ||
                    c == PlaceHolder.DC ||
                    c == PlaceHolder.PC)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            //栈为空时，直接入栈
            int s = stack.size();
            if (s == 0) {
                stack.add(c);
                continue;
            }
            //判断错误情况
            if (c == '}' && !PlaceHolder.BELIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == ']' && !PlaceHolder.BLIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '{' && !PlaceHolder.BERIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '[' && !PlaceHolder.BRIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '@' && !PlaceHolder.AIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '*' && !PlaceHolder.XJIsEffective(charArr, stack, i, '*')) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '#' && !PlaceHolder.XJIsEffective(charArr, stack, i, '#')) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '$' && !PlaceHolder.DIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            if (c == '^' && !PlaceHolder.PIsEffective(charArr, stack, i)) {
                errorChar = c;
                errorIndex = i;
                break;
            }
            //进栈
            if (c == '[' || c == '{'
                    || (c == '*' && stack.get(s - 1) != '*')
                    || (c == '#' && stack.get(s - 1) != '#')
                    || (c == '$' && stack.get(s - 1) != '$')
                    || (c == '^' && stack.get(s - 1) != '^')
            )
                stack.add(c);
            //出栈
            if (c == ']' || c == '}'
                    || (c == '*' && stack.get(s - 1) == '*')
                    || (c == '#' && stack.get(s - 1) == '#')
                    || (c == '$' && stack.get(s - 1) == '$')
                    || (c == '^' && stack.get(s - 1) == '^')
            )
                stack.remove(s - 1);
        }
        if (errorChar != null){
            errorInfor += StringUtil.substringBeforeAfterSize(data, indexArr.get(errorIndex), 10) + "------'" + errorChar + "' 存在语法错误,注意将特殊字符进行转义";
            throw new ExportWordException(errorInfor);
        }
    }

    private static class Singleton {
        private static VerifyXmlParse instance;

        static {
            instance = new VerifyXmlParse();
        }

        public static VerifyXmlParse getInstance() {
            return instance;
        }
    }

    public static VerifyXmlParse getInstance() {
        return Singleton.getInstance();
    }
}
