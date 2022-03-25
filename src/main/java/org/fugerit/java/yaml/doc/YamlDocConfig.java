package org.fugerit.java.yaml.doc;

import java.util.Locale;
import java.util.Properties;

public class YamlDocConfig {

	public YamlDocConfig() {
		this.locale = Locale.getDefault();
		if ( this.locale == null ) {
			this.locale = Locale.ENGLISH;
		}
		this.labelsOverride = new Properties();
	}
	
	private String outputFormat;

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
	
}
