package org.fugerit.java.yaml.doc;

import java.util.Map;

public class YamlModel {
	
	public static final String ATT_NAME = "yamlModel";

	private Map<String, Object> schemas;

	public Map<String, Object> getSchemas() {
		return schemas;
	}

	public void setSchemas(Map<String, Object> schemas) {
		this.schemas = schemas;
	}

}
