package com.qyzmode.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkDownUtil {

    //普通默认的把markdown转换Html
    public static String MarkdownToHtml(String markdownContent) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"

    }
    //拓展功能
    //把markdown转换Html
    // 转换markdown的格式为自定义格式
    public static  String MarkdownToHtmlAndMarkdownTable(String markdownContent)
    {
        //给<H>标题自动生成id
        Set<Extension> headingAnchorExtension= Collections.singleton(HeadingAnchorExtension.create());
        //转换table的html
        List<Extension> tableExtensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtensions)
                .build();
        Node document=parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtension)
                .extensions(tableExtensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAtrributeProvider();
                    }
                })
                .build();
          return  renderer.render(document);
    }
     //自定义的格式
    static class CustomAtrributeProvider implements AttributeProvider
     {

         @Override
         public void setAttributes(Node node, String s, Map<String, String> map) {
             //如果为连接就添加一个
             if(node instanceof  Link)
               map.put("target","_blank");
             if(node instanceof TableBlock)
                 map.put("class","ui celled table");
         }
     }

}
