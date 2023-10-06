package org.fugerit.java.yaml.doc;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.function.SimpleValue;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.lang.helpers.reflect.MethodHelper;
import org.fugerit.java.core.util.result.Result;
import org.yaml.snakeyaml.Yaml;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YamlDocCheckModel {

	public static final String ARG_CHECK_TYPE = "check-type";
	
	public static final String ARG_CHECK_SCHEMA = "check-schema";
	
	public static final String ARG_PRINT_ONLY_KO = "print-only-ko";
	
	public static final String ARG_CHECK_ONCE = "check-once";
	
	private YamlDocCheckModel() {}
	
	@SuppressWarnings("unchecked")
	private static void checkCurrent( CheckContext context, String path, Class<?> typeModel, Map<String, Object> currentPropertyMap  ) {
		String childSchema = null;
		if ( !BooleanUtils.isTrue( context.getParams().getProperty( ARG_PRINT_ONLY_KO ) ) ) {
			context.getReport().print( path );
			context.getReport().println( " ok!" );
		}
		if ( !currentPropertyMap.containsKey( "type" ) ) {
			if ( !currentPropertyMap.containsKey( "properties" ) ) {
				Object ref = currentPropertyMap.get( "$ref" );
				if ( ref != null ) {
					childSchema = ((String)ref).substring( "#/components/schemas/".length() );
					currentPropertyMap = (Map<String, Object>)context.getSchemas().get( childSchema );
				} else {
					log.info( "cannot find : {}", currentPropertyMap );
				}
			}
			check( context, path, typeModel, childSchema, currentPropertyMap );
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void check( CheckContext context, String path, Class<?> typeModel, String schemaName, Map<String, Object> schemaModel ) {
		log.debug( "path {}, class : {}, schema : {}", path, typeModel, schemaModel );
		Map<String, Object> properties = (Map<String, Object>)schemaModel.get( "properties" );
		boolean doCheck = true;
		if ( schemaName != null && BooleanUtils.isTrue( context.getParams().getProperty( ARG_CHECK_ONCE ) ) ) {
			doCheck = !context.getChecked().contains( schemaName );
			context.getChecked().add( schemaName );
		}
		if ( properties != null && doCheck ) {
			for ( Map.Entry<String, Object> entry : properties.entrySet() ) {
				String key = entry.getKey();
				Object value = entry.getValue();
				String newPath = path+"."+key;
				log.debug( "key : {} -> {} ({})", key, value, value.getClass().getName() );
				try {
					String getterName = MethodHelper.getGetterNameForProperty( key );
					log.debug( "try {} on {}", getterName, typeModel );
					Method getter = typeModel.getMethod( getterName , MethodHelper.NO_PARAM_TYPES );
					checkCurrent(context, newPath, getter.getReturnType(), (Map<String, Object>)value );
				} catch (Exception e) {
					context.increaseKoCount();
					log.info( "no getter for '{}' -> '{}'", path, newPath );
					context.getReport().print( newPath );
					context.getReport().print( " not found in implementation!!!!! " );
					context.getReport().print( typeModel.getName() );
					context.getReport().print( " - " );
					context.getReport().println( schemaName );
				}
			}	
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int handleModelCheck( Properties props ) throws ConfigException {
		SimpleValue<Integer> res = new SimpleValue<>( Result.RESULT_CODE_OK );
		String inputYaml = props.getProperty( YamlDocMain.ARG_INPUT_YAML );
		String checkType = props.getProperty( ARG_CHECK_TYPE );
		String checkSchema = props.getProperty( ARG_CHECK_SCHEMA );
		if ( StringUtils.isEmpty( inputYaml ) || StringUtils.isEmpty( checkType ) || StringUtils.isEmpty( checkSchema ) ) {
			throw new ConfigException( "Missing required parameters : "+ARG_CHECK_TYPE+", "+YamlDocMain.ARG_INPUT_YAML+", "+ARG_CHECK_SCHEMA );
		} else {
			ConfigException.apply( () -> {
				try ( Reader reader = new FileReader( new File( inputYaml ) );
						StringWriter buffer = new StringWriter();
						PrintWriter report = new PrintWriter( buffer ) ) {
					Yaml yaml = new Yaml();
					Map<String, Object> fullYaml = yaml.load( reader );
					Map<String, Object> components = (Map<String, Object>)fullYaml.get( "components" );
					Map<String, Object> schemas = (Map<String, Object>)components.get( "schemas" );
					Map<String, Object> schemaModel = (Map<String, Object>)schemas.get( checkSchema );
					if ( schemaModel == null ) {
						throw new ConfigException( "schema not found : "+checkSchema );
					}
					Class<?> typeModel = null;
					try {
						typeModel = Class.forName( checkType );
					} catch (Exception e) {
						throw new ConfigException( "Failed to create type to check : "+checkType );
					}
					CheckContext context = new CheckContext(props, schemas, report, new LinkedHashSet<>());
					check( context, "root", typeModel, checkSchema, schemaModel );
					res.setValue( context.getCountKo() );
					log.info( "report {} -> \n{}", res.getValue(), buffer.toString() );
				} 
			} );
		}
		return res.getValue();
	}
	
}

@RequiredArgsConstructor
class CheckContext {
	@Getter @NonNull private Properties params;
	@Getter @NonNull private Map<String, Object> schemas;
	@Getter @NonNull private PrintWriter report;
	@Getter @NonNull private Set<String> checked;
	@Getter int countKo = 0;
	public void increaseKoCount() {
		this.countKo++;
	}
}
