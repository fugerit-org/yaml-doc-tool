package org.fugerit.java.yaml.doc.fun;

import java.util.List;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class PrintExampleFun implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
		String res = "";
		if ( arguments.size() > 0 && arguments.get( 0 ) != null ) {
			Object current = arguments.get( 0 );
			if ( current.getClass().getName().contains( "TrueTemplateBooleanModel" ) ) {
				res = "true";
			} else if ( current.getClass().getName().contains( "TrueTemplateBooleanModel" ) ) {
				res = "false";
			} else {
				res = current.toString();
			}
		}
		return new SimpleScalar( res );
	}
}
