package com.hukacode.hktool.goalkickerdownloader.infrastructure;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GoalkickerDownloader {
  private final AHCDownloader ahcDownloader;

  public void download() throws ExecutionException, InterruptedException, IOException {
    String urlFormat = "https://goalkicker.com/{0}Book/{0}NotesForProfessionals.pdf";
    String location = "R:/book/goalkicker";
    String fileSavePath = "{0}/{1}NotesForProfessionals.pdf";
    FileUtils.forceMkdir(new File(location));

    Document document = Jsoup.connect("https://goalkicker.com").get();
    Elements elements = document.body().getElementsByClass("bookContainer grow");

    for (Element element : elements) {
      String rawName = element.child(0).attr("href");
      String bookTitle = rawName.substring(0, rawName.indexOf("Book"));

      if (!StringUtils.hasText(bookTitle)) {
        ahcDownloader.downloadWithAHC(MessageFormat.format(urlFormat, bookTitle),
            MessageFormat.format(fileSavePath, location, bookTitle));
      }
    }
  }
}
