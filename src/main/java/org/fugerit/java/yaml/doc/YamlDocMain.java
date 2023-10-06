package org.fugerit.java.yaml.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.GenericListCatalogConfig;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.core.util.collection.ListMapStringKey;
import org.fugerit.java.doc.base.typehelper.excel.ExcelHelperConsts;
import org.fugerit.java.yaml.doc.config.OpenapiConfig;
import org.fugerit.java.yaml.doc.config.YamlDocCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlDocMain {

	private static final Logger logger = LoggerFactory.getLogger( YamlDocMain.class );
	
	public static final String ARG_EXCLUDE_PATHS = "exclude-paths";
	
	public static final String ARG_EXCLUDE_SCHEMAS = "exclude-schemas";
	
	public static final String ARG_INPUT_YAML = "input-yaml";
	
	public static final String ARG_OUTPUT_FILE = "output-file";
	
	public static final String ARG_LANGUAGE = "language";
	
	public static final String ARG_LABEL_OVVERRIDE = "labels-override";
	
	public static final String ARG_EXCEL_TRY_AUTORESIZE = ExcelHelperConsts.PROP_XLS_TRY_AUTORESIZE;
	
	public static final String ARG_MODE = "mode";
	public static final String ARG_MODE_SINGLE = "single";
	public static final String ARG_MODE_CONFIG = "config";
	public static final String ARG_MODE_CHECK_MODEL = "check-model";
	public static final String ARG_MODE_DEFAULT = ARG_MODE_SINGLE;
	
	public static final String ARG_CONFIG_PATH = "config-path";
	
	public static final String ARG_ID_CATALOG = "id-catalog";
	
	public static final String ARG_USE_OPENAPI_TITLE = "use-openapi-title";
	
    private static void addIfNotEmpty( Properties props, String key, String value ) {
    	if ( StringUtils.isNotEmpty( value ) ) {
    		props.setProperty( key , value );
    	}
    }
	
    private static void setup( YamlDocConfig config, Properties props ) throws IOException {
    	String language = props.getProperty( ARG_LANGUAGE );
		String labelOverride = props.getProperty( ARG_LABEL_OVVERRIDE );
		String excludePaths = props.getProperty( ARG_EXCLUDE_PATHS );
		String excludeSchemas = props.getProperty( ARG_EXCLUDE_SCHEMAS );
		String useOpenapiTitle = props.getProperty( ARG_USE_OPENAPI_TITLE );
		if ( StringUtils.isNotEmpty( language ) ) {
			config.setLocale( Locale.forLanguageTag( language ) );
		}
		if ( StringUtils.isNotEmpty( labelOverride ) ) {
			config.setLabelsOverride( PropsIO.loadFromFile( labelOverride ) );
		}
		if ( StringUtils.isNotEmpty( excludePaths ) ) {
			config.setExcludePaths( BooleanUtils.isTrue( excludePaths ) );
		}
		if ( StringUtils.isNotEmpty( excludeSchemas ) ) {
			config.setExcludeSchemas( BooleanUtils.isTrue( excludeSchemas ) );
		}
		if ( StringUtils.isNotEmpty( useOpenapiTitle ) ) {
			config.setUseOpenapiTitle( BooleanUtils.isTrue( useOpenapiTitle ) );
		}
    }
    
    private static void handleSingleMode( Properties props ) throws ConfigException {
    	ConfigException.apply( () -> {
    		String inputYaml = props.getProperty( ARG_INPUT_YAML );
    		String outputPath = props.getProperty( ARG_OUTPUT_FILE );
    		if ( StringUtils.isEmpty( inputYaml ) || StringUtils.isEmpty( outputPath ) ) {
    			throw new ConfigException( "Required params : "+ARG_INPUT_YAML+", "+ARG_OUTPUT_FILE );
    		} else {
    			File inputFile = new File( inputYaml );
    			File outputFile = new File( outputPath );
    			String fileName = outputFile.getName();
    			String outputFormat = fileName.substring( fileName.lastIndexOf( '.' )+1 );
    			try ( Reader reader = new FileReader( inputFile );
    					FileOutputStream fos = new FileOutputStream( outputFile ) ) {
    				YamlDocConfig config = new YamlDocConfig( outputFormat );
    				setup(config, props);
    				config.setExcelTryAutoresize( BooleanUtils.isTrue( props.getProperty( ARG_EXCEL_TRY_AUTORESIZE, ExcelHelperConsts.PROP_XLS_TRY_AUTORESIZE_DEFAULT ) ) );
    				YamlDocFacade facade = new YamlDocFacade();
    				facade.handle(reader, fos, config);
    			}
    		}    		
    	} );
    }
    
    private static void handleMultiMode( Properties props ) throws ConfigException {
    	ConfigException.apply( () -> {
        	String configPath = props.getProperty( ARG_CONFIG_PATH );
    		String idCatalog = props.getProperty( ARG_ID_CATALOG );
    		if ( StringUtils.isEmpty( configPath ) || StringUtils.isEmpty( idCatalog ) ) {
    			throw new ConfigException( "Required params : "+ARG_CONFIG_PATH+", "+ARG_ID_CATALOG );
    		} else {
    			logger.info( "configPath:{}, idCatalog:{}", configPath, idCatalog );
    			YamlDocCatalog config = new YamlDocCatalog();
    			try ( FileInputStream fis = new FileInputStream( new File( configPath ) ) ) {
    				config = (YamlDocCatalog)GenericListCatalogConfig.load( fis , config );
            		logger.info( "keys : {}", config.getIdSet() );
            		ListMapStringKey<OpenapiConfig> catalog = config.getListMap( idCatalog );
            		for ( OpenapiConfig current : catalog ) {
            			Properties propsCurrent = new Properties();
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_INPUT_YAML, current.getInputYaml() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_OUTPUT_FILE, current.getOutputFile() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_LANGUAGE, current.getLanguage() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_LABEL_OVVERRIDE, current.getLabelsOverride() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_EXCEL_TRY_AUTORESIZE, current.getExcelTryAutoresize() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_EXCLUDE_PATHS, current.getExcludePaths() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_EXCLUDE_SCHEMAS, current.getExcludeSchemas() );
                    	addIfNotEmpty(propsCurrent, YamlDocMain.ARG_USE_OPENAPI_TITLE, current.getUseOpenapiTitle() );
                    	logger.info( "using parameters -> {}", props );
                    	YamlDocMain.worker( propsCurrent );
            		}
    			}
    		}
    	} );
    }
    
	public static void worker( Properties props ) throws ConfigException {
		String mode = props.getProperty( ARG_MODE , ARG_MODE_DEFAULT );
		if ( ARG_MODE_SINGLE.equalsIgnoreCase( mode ) ) {
			handleSingleMode(props);
		} else if ( ARG_MODE_CHECK_MODEL.equalsIgnoreCase( mode ) ) {
			YamlDocCheckModel.handleModelCheck(props);
		} else {
			handleMultiMode(props);
		}
	}
	
	public static void main( String[] args ) {
		try {
			Properties props = ArgUtils.getArgs( args );
			worker(props);
		} catch (Exception e) {
			logger.error( "Error : "+e ,e );
		}
	}
	
}
