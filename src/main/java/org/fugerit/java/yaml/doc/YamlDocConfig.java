package org.fugerit.java.yaml.doc;

import java.util.Locale;
import java.util.Properties;

public class YamlDocConfig {

	public YamlDocConfig() {
		this.locale = Locale.getDefault();
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
		super();
		this.outputFormat = outputFormat;
	}
	
	private Locale locale;

	public Locale getLocale() {
		return locale;
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
	
}
