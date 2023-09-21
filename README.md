# yaml-doc-tool

Tool for auto documentation of yaml / openapi

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](CHANGELOG.md) 
[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/yaml-doc-tool.svg)](https://mvnrepository.com/artifact/org.fugerit.java/yaml-doc-tool)
[![license](https://img.shields.io/badge/License-Apache%20License%202.0-teal.svg)](https://opensource.org/licenses/Apache-2.0)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_yaml-doc-tool&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit-org_yaml-doc-tool)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_yaml-doc-tool&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit-org_yaml-doc-tool)

**Usage:**  

```
java -jar dist-yaml-doc-tool-X.X.X.jar --input-yaml [path-to-openapi]\
										--output-file [output-file]\
										--language [language]\
										--labels-override [path-to-labels-properties]
```
										
**example :**  

java -jar dist-yaml-doc-tool-X.X.X.jar --input-yaml sample.yaml --output-file sample.pdf --language it

**output-file**   
currently supported extensions : pdf, xlsx, xml, fo

**language**  
currently supported languages : it, en

**labels-overrides**   
path to alternate labels properties
currently supported labels are available in : src/main/resources/lang/label.properties

**maven plugin**  
A [Maven Plugin](https://github.com/fugerit-org/yaml-doc-maven-plugin) is also available.
