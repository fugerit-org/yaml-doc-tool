package org.fugerit.java.yaml.doc;

import java.util.Locale;

public class YamlDocConfig {

	public YamlDocConfig() {
		this.locale = Locale.getDefault();
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
	
}
