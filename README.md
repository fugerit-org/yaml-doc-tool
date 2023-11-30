# yaml-doc-tool

Tool for auto documentation of yaml / openapi

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](CHANGELOG.md) 
[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/yaml-doc-tool.svg)](https://mvnrepository.com/artifact/org.fugerit.java/yaml-doc-tool)
[![license](https://img.shields.io/badge/License-Apache%20License%202.0-teal.svg)](https://opensource.org/licenses/Apache-2.0)
[![code of conduct](https://img.shields.io/badge/conduct-Contributor%20Covenant-purple.svg)](https://github.com/fugerit-org/fj-universe/blob/main/CODE_OF_CONDUCT.md)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_yaml-doc-tool&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit-org_yaml-doc-tool)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_yaml-doc-tool&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit-org_yaml-doc-tool)

[![Java runtime version](https://img.shields.io/badge/run%20on-java%208+-%23113366.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Java build version](https://img.shields.io/badge/build%20on-java%2011+-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-3.9.0+-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)](https://universe.fugerit.org/src/docs/versions/maven3_9.html)

## Quickstart (build)

```
mvn clean install -P singlepackage		
```

## Quickstart (mode: single):

```
java -jar dist-yaml-doc-tool-*.jar --input-yaml [path-to-openapi] \
										--output-file [output-file] \
										--language [language] \
										--labels-override [path-to-labels-properties]
```
										
**example :**  

```
java -jar dist-yaml-doc-tool-*.jar --input-yaml sample.yaml --output-file sample.pdf --language it
```

**output-file**   
currently supported extensions : pdf, xlsx, xml, fo

**language**  
currently supported languages : it, en

**labels-overrides**   
path to alternate labels properties
currently supported labels are available in : src/main/resources/lang/label.properties

**maven plugin**  
A [Maven Plugin](https://github.com/fugerit-org/yaml-doc-maven-plugin) is also available.


## Quickstart (mode: config):

```
java -jar dist-yaml-doc-tool-*.jar 		--mode [config] \
										--config-path [path-to-config] \
										--id-catalog [id-catalog-in-config]
```

**config-path**   
path to configuration (see [sample](src/test/resources/yaml-doc-config.xml))

**id-catalog**   
id of the catalog in config to use


## Quickstart (mode: check-model):

Node : the type to check need to be in classpath.

```
java -jar dist-yaml-doc-tool-*.jar 		--mode [check-model] \
										--input-yaml [path-to-openapi] \
										--output-file [output-file] \
										--check-type [java type to check] \
										--check-schema [openapi schema to check]
										--version [model-version]
```

## Model version info (param "version")

### Version 0 - legacy version (up to 1.0.0 excluded)

### Version 1

handling handling of constraints [OpenAPI data types](https://swagger.io/docs/specification/data-models/data-types/)
 - minLength
 - maxLength
 - minimum
 - maximum 
