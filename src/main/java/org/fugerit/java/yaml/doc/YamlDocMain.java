package org.fugerit.java.yaml.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.doc.base.typehelper.excel.ExcelHelperConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlDocMain {

	private static final Logger logger = LoggerFactory.getLogger( YamlDocMain.class );
	
	public static final String ARG_INPUT_YAML = "input-yaml";
	
	public static final String ARG_OUTPUT_FILE = "output-file";
	
	public static final String ARG_LANGUAGE = "language";
	
	public static final String ARG_LABEL_OVVERRIDE = "labels-override";
	
	public static final String ARG_EXCEL_TRY_AUTORESIZE = ExcelHelperConsts.PROP_XLS_TRY_AUTORESIZE;
	
	public static void worker( Properties props ) throws Exception {
		String inputYaml = props.getProperty( ARG_INPUT_YAML );
		String outputPath = props.getProperty( ARG_OUTPUT_FILE );
		if ( StringUtils.isEmpty( inputYaml ) || StringUtils.isEmpty( outputPath ) ) {
			throw new ConfigException( "Required params : "+ARG_INPUT_YAML+", "+ARG_OUTPUT_FILE );
		} else {
			String language = props.getProperty( ARG_LANGUAGE );
			String labelOverride = props.getProperty( ARG_LABEL_OVVERRIDE );
			File inputFile = new File( inputYaml );
			File outputFile = new File( outputPath );
			String fileName = outputFile.getName();
			String outputFormat = fileName.substring( fileName.lastIndexOf( '.' )+1 );
			try ( Reader reader = new FileReader( inputFile );
					FileOutputStream fos = new FileOutputStream( outputFile ) ) {
				YamlDocConfig config = new YamlDocConfig( outputFormat );
				if ( StringUtils.isNotEmpty( language ) ) {
					config.setLocale( Locale.forLanguageTag( language ) );
				}
				if ( StringUtils.isNotEmpty( labelOverride ) ) {
					config.setLabelsOverride( PropsIO.loadFromFile( labelOverride ) );
				}
				config.setExcelTryAutoresize( BooleanUtils.isTrue( props.getProperty( ARG_EXCEL_TRY_AUTORESIZE, ExcelHelperConsts.PROP_XLS_TRY_AUTORESIZE_DEFAULT ) ) );
				YamlDocFacade facade = new YamlDocFacade();
				facade.handle(reader, fos, config);
			}
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
