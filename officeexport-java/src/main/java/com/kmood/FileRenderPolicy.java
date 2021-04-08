package com.kmood;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author: zhoucx
 * @time: 2021/3/15 14:19
 */
public class FileRenderPolicy implements RenderPolicy {
    @Override
    public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        final NiceXWPFDocument xwpfDocument = template.getXWPFDocument();
        final XWPFDocument document = xwpfDocument.getXWPFDocument();
       // document.create
    }
}
