package org.fugerit.java.yaml.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlDocMain {

	private static final Logger logger = LoggerFactory.getLogger( YamlDocMain.class );
	
	public static final String ARG_INPUT_YAML = "input-yaml";
	
	public static final String ARG_OUTPUT_FILE = "output-file";
	
	public static void main( String[] args ) {
		try {
			Properties props = ArgUtils.getArgs( args );
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
					YamlDocFacade facade = new YamlDocFacade();
					facade.handle(reader, fos, config);
				}
			}
		} catch (Exception e) {
			logger.error( "Error : "+e ,e );
		}
	}
	
}
