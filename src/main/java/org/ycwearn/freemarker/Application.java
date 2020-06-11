package org.ycwearn.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.ycwearn.freemarker.model.Gender;
import org.ycwearn.freemarker.model.Person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Application {

  public static void main(String[] args) {
    log.info("Executing FreeMarker Demo to auto generate README.md");
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("README.md"), StandardCharsets.UTF_8)) {
      TemplateUtil.writeToFile(writer, "README.ftl", Application.populateDataModel());
    } catch (FileNotFoundException e) {
      log.error(e.getMessage(), e);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    log.info("Done...");
  }

  public static Map<String, Object> populateDataModel() {
    final Map<String, Object> dataModel = new HashMap<>();

    final List<Person> personList = Arrays.asList(
        Person.builder().name("Olivia").age(15).gender(Gender.FEMALE).build(),
        Person.builder().name("Oliver").age(15).gender(Gender.MALE).build(),
        Person.builder().name("Amelia").age(25).gender(Gender.FEMALE).build(),
        Person.builder().name("George").age(25).gender(Gender.MALE).build(),
        Person.builder().name("Isla").age(35).gender(Gender.FEMALE).build(),
        Person.builder().name("Harry").age(35).gender(Gender.MALE).build()
    );

    dataModel.put("templateEngine", "FreeMarker");

    dataModel.put("personList", personList);

    return dataModel;
  }

}
