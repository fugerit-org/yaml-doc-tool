package org.fugerit.java.yaml.doc;

public class YamlDocConfig {

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
	
}
