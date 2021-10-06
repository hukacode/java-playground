/*
 * Copyright 2022 huka.dev
 */
package dev.huka.tool;

import com.google.common.base.Strings;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

public class GoalkickerDownloader {
  public static void main(String[] args) throws IOException {
    var urlFormat = "https://goalkicker.com/{0}Book/{0}NotesForProfessionals.pdf";
    var location = "goalkicker-downloader/downloaded";
    var fileSavePath = "{0}/{1}NotesForProfessionals.pdf";
    FileUtils.forceMkdir(new File(location));

    var document = Jsoup.connect("https://goalkicker.com").get();
    var elements = document.body().getElementsByClass("bookContainer grow");

    var ahcDownloader = new AHCDownloader();
    elements.forEach(
        element -> {
          var rawName = element.child(0).attr("href");
          var bookTitle = rawName.substring(0, rawName.indexOf("Book"));

          if (!Strings.isNullOrEmpty(bookTitle)) {
            try {
              ahcDownloader.downloadWithAHC(
                  MessageFormat.format(urlFormat, bookTitle),
                  MessageFormat.format(fileSavePath, location, bookTitle));
            } catch (ExecutionException | InterruptedException | IOException e) {
              throw new RuntimeException(e);
            }
          }
        });
  }
}
