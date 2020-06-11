package org.ycwearn.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TemplateUtil {

  private static final Configuration configuration;

  static {
    configuration = new Configuration(Configuration.VERSION_2_3_29);
    configuration.setClassForTemplateLoading(TemplateUtil.class, "/template");
    configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    configuration.setLogTemplateExceptions(false);
    configuration.setWrapUncheckedExceptions(true);
    configuration.setFallbackOnNullLoopVariable(false);
  }

  private TemplateUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static void writeToFile(Writer writer, String templateName, Object dataModel) {
    try {
      final Template template = configuration.getTemplate(templateName, StandardCharsets.UTF_8.name());
      template.process(dataModel, writer);
    } catch (IOException e) {
      log.error("Fail to configure template {} due to error {}", templateName, e.getMessage());
    } catch (TemplateException e) {
      log.error("Fail to write to file template {} due to error {}", templateName, e.getMessage());
    }
  }

}
