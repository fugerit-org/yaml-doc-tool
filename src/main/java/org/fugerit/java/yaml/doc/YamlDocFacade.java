package org.fugerit.java.yaml.doc;

import java.io.OutputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.core.util.result.Result;
import org.fugerit.java.doc.base.facade.ProcessDocFacade;
import org.fugerit.java.doc.base.process.DocProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class YamlDocFacade {

	private static final Logger logger = LoggerFactory.getLogger( YamlDocFacade.class );
	
	private static final String BUNDLE_LABEL_PATH = "lang.label";
	
	@SuppressWarnings("unchecked")
	public int handle( Reader inputYaml, OutputStream outputData, YamlDocConfig config ) throws Exception  {
		int result = Result.RESULT_CODE_OK;
		// yaml parsing
		Yaml yaml = new Yaml();
		Map<String, Object> fullYaml = yaml.load( inputYaml );
		Map<String, Object> components = (Map<String, Object>)fullYaml.get( "components" );
		Map<String, Object> schemas = (Map<String, Object>)components.get( "schemas" );
		// bundle labels
		ResourceBundle labelsBundle = ResourceBundle.getBundle( BUNDLE_LABEL_PATH, config.getLocale() );
		Properties labels = PropsIO.loadFromBundle( labelsBundle );
		labels.putAll( config.getLabelsOverride() );
		// build model
		YamlModel yamlModel = new YamlModel();
		yamlModel.setSchemas( schemas );
		yamlModel.setLabels( labels );
		yamlModel.setConfig( config );
		// generation
		ProcessDocFacade docFacade = FjDocFacade.getInstance();
		logger.info( "docFacade -> {}", docFacade );
		DocProcessContext context = DocProcessContext.newContext( YamlModel.ATT_NAME, yamlModel );
		docFacade.process( ProcessDocFacade.CHAIN_ID_YAML_DOC_TEMPLATE, config.getOutputFormat(), context, outputData ); 
		return result;
	}
	
}
