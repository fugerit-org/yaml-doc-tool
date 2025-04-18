package org.fugerit.java.yaml.doc.config;

import org.fugerit.java.core.cfg.xml.BasicIdConfigType;

public class OpenapiConfig extends BasicIdConfigType {

    /**
     *
     */
    private static final long serialVersionUID = 4008211268410549288L;

    private String inputYaml;

    private String outputFile;

    private String language;

    private String labelsOverride;

    private String excelTryAutoresize;

    private String excludePaths;

    private String excludeSchemas;

    private String useOpenapiTitle;

    private String version;

    public String getInputYaml() {
        return inputYaml;
    }

    public void setInputYaml(String inputYaml) {
        this.inputYaml = inputYaml;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLabelsOverride() {
        return labelsOverride;
    }

    public void setLabelsOverride(String labelsOverride) {
        this.labelsOverride = labelsOverride;
    }

    public String getExcelTryAutoresize() {
        return excelTryAutoresize;
    }

    public void setExcelTryAutoresize(String excelTryAutoresize) {
        this.excelTryAutoresize = excelTryAutoresize;
    }

    public String getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String excludePaths) {
        this.excludePaths = excludePaths;
    }

    public String getExcludeSchemas() {
        return excludeSchemas;
    }

    public void setExcludeSchemas(String excludeSchemas) {
        this.excludeSchemas = excludeSchemas;
    }

    public String getUseOpenapiTitle() {
        return useOpenapiTitle;
    }

    public void setUseOpenapiTitle(String useOpenapiTitle) {
        this.useOpenapiTitle = useOpenapiTitle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
