package com.hukacode.hktool.goalkickerdownloader.infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.asynchttpclient.AsyncHandler.State;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AHCDownloader {
  @Async
  public void display(int i) throws InterruptedException {
    Thread.sleep(1000);
    System.out.println(i);
  }

  @Async
  public void downloadWithAHC(String url, String localFilename)
      throws ExecutionException, InterruptedException, IOException {

    FileOutputStream stream = new FileOutputStream(localFilename);
    AsyncHttpClient client = Dsl.asyncHttpClient();

    client.prepareGet(url).execute(new AsyncCompletionHandler<FileOutputStream>() {

      @Override
      public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
        stream.getChannel().write(bodyPart.getBodyByteBuffer());
        return State.CONTINUE;
      }

      @Override
      public FileOutputStream onCompleted(Response response) throws Exception {
        return stream;
      }
    }).get();

    stream.getChannel().close();
    client.close();
  }
}
