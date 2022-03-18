# yaml-doc-tool

Tool for auto documentation of yaml / openapi

**Usage:**  

java -jar dist-yaml-doc-tool-X.X.X.jar --input-yaml [path-to-openapi]\
										--output-file [output-file]\
										--language [language]\
										--labels-override [path-to-labels-properties]
										
**example :**  

java -jar dist-yaml-doc-tool-0.1.0.jar --input-yaml sample.yaml --output-file sample.pdf --language it

**output-file**   
currently supported extensions : pdf, xlsx, xml

**language**  
currently supported languages : it, en

**labels-overrides**   
path to alternate labels properties
currently supported labels are available in : src/main/resources/lang/label.properties