/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.thymeleaf.text;

import dev.huka.playground.thymeleaf.Base;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class Application extends Base {
  public static void main(String[] args) {
    var resolver = new ClassLoaderTemplateResolver();
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".md");
    resolver.setCharacterEncoding("UTF-8");
    resolver.setTemplateMode(TemplateMode.TEXT);

    var templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(resolver);

    var htmlString = templateEngine.process("index", getContext());

    System.out.println(htmlString);
  }
}
