package com.hukacode.hktool.goalkickerdownloader;

import com.hukacode.hktool.goalkickerdownloader.infrastructure.GoalkickerDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GoalkickerApplication implements CommandLineRunner {
  @Autowired
  private GoalkickerDownloader goalkickerDownloader;
  @Autowired
  private ConfigurableApplicationContext context;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(GoalkickerApplication.class, args);
  }

  /**
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   */
  @Override
  public void run(String... args) throws Exception {
    goalkickerDownloader.download();
    SpringApplication.exit(context);
//    System.exit(SpringApplication.exit(context));
  }

}
