/*
 * Copyright 2022 huka.dev
 */
package dev.huka.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;

public class AHCDownloader {
  public void downloadWithAHC(String url, String localFilename)
      throws ExecutionException, InterruptedException, IOException {

    var stream = new FileOutputStream(localFilename);
    var client = Dsl.asyncHttpClient();

    client
        .prepareGet(url)
        .execute(
            new AsyncCompletionHandler<FileOutputStream>() {

              @Override
              public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                return State.CONTINUE;
              }

              @Override
              public FileOutputStream onCompleted(Response response) throws Exception {
                return stream;
              }
            })
        .get();

    stream.getChannel().close();
    client.close();
  }
}
