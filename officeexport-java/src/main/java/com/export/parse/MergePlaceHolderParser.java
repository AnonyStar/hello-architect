package com.export.parse;

import com.export.model.XmlModel;
import com.export.model.XpathEnum;
import com.kmood.utils.StringUtil;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhoucx
 * @time: 2021/3/17 17:25
 */
public class MergePlaceHolderParser extends AbstractWordParser {

    private MergePlaceHolderParser() {
    }

    @Override
    protected void handler(XmlModel xmlModel) {
        // todo 业务处理
        final List<Element> wpList = xmlModel.getNoteList4Element(xmlModel.getDocumentPart(), XpathEnum.W_P);
        wpList.forEach(p -> {
            placeHodlerHandle(p);
            specialPlaceHodlerHandle(p);
        });
        //转换[ 到list标签
        bracketToListConversion(wpList);

    }



    private static class Singleton {
        private static MergePlaceHolderParser instance;

        static {
            instance = new MergePlaceHolderParser();
        }

        public static MergePlaceHolderParser getInstance() {
            return instance;
        }
    }

    public static MergePlaceHolderParser getInstance() {
        return Singleton.getInstance();
    }
}
