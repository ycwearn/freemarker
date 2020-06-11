
# Demo of Template Engine - FreeMarker

[Apache FreeMarker™](https://freemarker.apache.org/) is a template engine: a Java library to generate text output (HTML web pages, e-mails, configuration files, source code, etc.)

![FreeMarker Overview](https://freemarker.apache.org/images/overview.png)

Another alternative templating engine is [Thymeleaf](https://www.thymeleaf.org/) which is more commonly used for HTML DOM templating.

## Basic Usage:

Create a configuration for FreeMarker (Refer to [Configuration](https://freemarker.apache.org/docs/pgui_config_basics.html))
```java
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
```

Load the FreeMarker template
```java
  final Template template = configuration.getTemplate(templateName, StandardCharsets.UTF_8.name());
```

Combine the template and data model and write it to file
```java
  template.process(dataModel, writer);
```

## Template Syntax

### Definite the output format
Use the **#ftl output_format** to specify the output file format.

```
<#ftl output_format="plainText">
```

### Looping though a collections
```
<#list personList as person>
${person.name} | ${person.gender} | ${person.age}
</#list>
```

Output:
```
Name           | Gender           | Age
-------------- | ---------------- | -------------
Olivia         | FEMALE           | 15
Oliver         | MALE             | 15
Amelia         | FEMALE           | 25
George         | MALE             | 25
Isla           | FEMALE           | 35
Harry          | MALE             | 35
```

### Conditional if
```
<#list personList as person>
<#if person.gender == 'FEMALE'>
${person.name?right_pad(14)} | ${person.age}
</#if>
</#list>
```

Output :
```
Name           | Age
-------------- | -------------
Olivia         | 15
Amelia         | 25
Isla           | 35
```

### Build-in Function
FreeMarker also support build-in formatting functions. Refer to [this](https://freemarker.apache.org/docs/ref_builtins.html)

Below is an example to format the text using **lower_case**:

```
<#list personList as person>
${person.name?right_pad(14)} | ${person.gender?lower_case}
</#list>
```

Output:
```
Name           | Gender
-------------- | ----------------
Olivia         | female
Oliver         | male
Amelia         | female
George         | male
Isla           | female
Harry          | male
```
