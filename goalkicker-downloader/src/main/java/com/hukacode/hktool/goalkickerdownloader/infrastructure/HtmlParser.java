package com.hukacode.hktool.goalkickerdownloader.infrastructure;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlParser {
  public void parse() throws IOException {
    System.out.println("start parse");
    Document document = Jsoup.connect("https://www.baeldung.com/spring-events").get();
    System.out.println(document.title());
    Elements elements = document.body().select("*");

    for (Element element : elements) {
      System.out.println(element.ownText());
    }
  }
}
