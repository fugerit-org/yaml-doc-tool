package org.fugerit.java.yaml.doc;

import java.util.Locale;
import java.util.Properties;

public class YamlDocConfig {

    public static final Integer VERSION_0 = 0;
    public static final Integer VERSION_1 = 1;
    public static final Integer VERSION_DEFAULT = VERSION_1; // default version is last version

    public YamlDocConfig() {
        this.locale = Locale.getDefault();
        if (this.locale == null) {
            this.locale = Locale.ENGLISH;
        }
        this.version = VERSION_DEFAULT;
        this.labelsOverride = new Properties();
    }

    private String outputFormat;

    private Integer version;

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public YamlDocConfig(String outputFormat) {
        this();
        this.outputFormat = outputFormat;
    }

    private Locale locale;

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguageCode() {
        return this.getLocale().getLanguage();
    }

    private Properties labelsOverride;

    public Properties getLabelsOverride() {
        return labelsOverride;
    }

    public void setLabelsOverride(Properties labelsOverride) {
        this.labelsOverride = labelsOverride;
    }

    private boolean excelTryAutoresize;

    public boolean getExcelTryAutoresize() {
        return excelTryAutoresize;
    }

    public void setExcelTryAutoresize(boolean excelTryAutoresize) {
        this.excelTryAutoresize = excelTryAutoresize;
    }

    private boolean excludePaths;

    private boolean excludeSchemas;

    public boolean isExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(boolean excludePaths) {
        this.excludePaths = excludePaths;
    }

    public boolean isExcludeSchemas() {
        return excludeSchemas;
    }

    public void setExcludeSchemas(boolean excludeSchemas) {
        this.excludeSchemas = excludeSchemas;
    }

    private boolean useOpenapiTitle;

    public boolean isUseOpenapiTitle() {
        return useOpenapiTitle;
    }

    public void setUseOpenapiTitle(boolean useOpenapiTitle) {
        this.useOpenapiTitle = useOpenapiTitle;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
