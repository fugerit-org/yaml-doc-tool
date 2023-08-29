# yaml-doc-tool

Tool for auto documentation of yaml / openapi

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/yaml-doc-tool.svg)](https://mvnrepository.com/artifact/org.fugerit.java/yaml-doc-tool)

**Usage:**  

```
java -jar dist-yaml-doc-tool-X.X.X.jar --input-yaml [path-to-openapi]\
										--output-file [output-file]\
										--language [language]\
										--labels-override [path-to-labels-properties]
```
										
**example :**  

java -jar dist-yaml-doc-tool-0.3.0.jar --input-yaml sample.yaml --output-file sample.pdf --language it

**output-file**   
currently supported extensions : pdf, xlsx, xml, fo

**language**  
currently supported languages : it, en

**labels-overrides**   
path to alternate labels properties
currently supported labels are available in : src/main/resources/lang/label.properties

**maven plugin**  
A [Maven Plugin](https://github.com/fugerit-org/yaml-doc-maven-plugin) is also available.
