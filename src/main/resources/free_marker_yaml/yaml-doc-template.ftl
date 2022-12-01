<#import "yaml-doc-macro.ftl" as docMacro>
<?xml version="1.0" encoding="utf-8"?>
<doc
	xmlns="http://javacoredoc.fugerit.org"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://javacoredoc.fugerit.org https://www.fugerit.org/data/java/doc/xsd/doc-1-10.xsd" >

  <!-- 
  	Sample Apache FreeMarker template for Fugerit Doc.
  	Note : this example has no intention of being a guidr to FreeMarker
  			(In case check FreeMarker documentation https://freemarker.apache.org/docs/index.html)
   -->

  <#assign labels=yamlModel.labels>

  <meta>
  
	<info name="doc-version">${messageFormat(labels['doc.def.version'])}</info>
	<info name="doc-title">${messageFormat(labels['doc.def.title'])}</info>
	<info name="doc-author">${messageFormat(labels['doc.def.author'])}</info>
	<info name="doc-subject">${messageFormat(labels['doc.def.subject'])}</info>
	<info name="doc-creator">Fugerit - Yaml Doc Tool</info>
	<info name="doc-language">${yamlModel.config.languageCode}</info>		  	
  
  	<!-- specific properties for PDF -->
  	<info name="margins">10;10;10;10</info>
  	<info name="page-width">29.7cm</info>
  	<info name="page-height">21cm</info>
  	<info name="excel-try-autoresize">${yamlModel.config.excelTryAutoresize?c}</info>
  	
  	<!-- specific properties for XLSX -->
  	<#assign excelTableId=''>
  	<#list yamlModel.schemas?keys as currentSchemaKey>
  		<#if currentSchemaKey?index != 0>
  			<#assign excelTableId>${excelTableId};</#assign>
  		</#if>
  		<#assign excelTableId>${excelTableId}table_${currentSchemaKey}=${currentSchemaKey}</#assign>
 	</#list>
   	<info name="excel-table-id">${excelTableId}</info>
	<info name="excel-width-multiplier">900</info> 
  	
 	<bookmark-tree>
 		<#list yamlModel.schemas?keys as currentSchemaKey>
 			 <bookmark ref="${currentSchemaKey}">${currentSchemaKey}</bookmark>
 		</#list>
    </bookmark-tree>
  	
  </meta>
	
  <body>
  	
  		<h head-level="1" style="bold" size="16" space-after="20">${messageFormat(labels['doc.def.title'])}</h>
  
  		<#if (yamlModel.paths)?? >
  		<#if (!yamlModel.config.excludePaths) >
  			<h space-after="20" head-level="2" style="bold" size="16">${messageFormat(labels['title.path.list'])}</h>
  			<list>
  			<#list yamlModel.paths?keys as currentPathKey>
  				<#assign currentPathValue=yamlModel.paths[currentPathKey]>
  				<li><para>${currentPathKey}<#if (yamlModel.paths[currentPathKey]['description']??)> - ${yamlModel.paths[currentPathKey]['description']}</#if></para>
  					<list list-type="ulm">
  						<#list currentPathValue?keys as currentMethod>
  							<#if (currentMethod != 'description' ) >
  								<#assign currentMethodValue=currentPathValue[currentMethod]>
  						<li><para>${currentMethod}<#if (currentMethodValue['description']??)> - ${currentMethodValue['description']}</#if></para></li>
  							</#if>
						</#list>
  					</list>
  				</li>
  			</#list>
  			</list>
  		</#if>
  		</#if>

  		<#if (yamlModel.schemas)?? >
  		<#if (!yamlModel.config.excludeSchemas) >
	  		<h space-before="20" space-after="20" head-level="2" style="bold" size="16">${messageFormat(labels['title.schema.list'])}</h>
			<#list yamlModel.schemas?keys as currentSchemaKey>
				<h id="${currentSchemaKey}" head-level="3" style="bold" size="14" space-before="20">${messageFormat(labels['title.schema.current'])} : ${currentSchemaKey}</h>
				<#assign currentSchemaValue=yamlModel.schemas[currentSchemaKey]>
				<table id="table_${currentSchemaKey}" columns="4" colwidths="30;30;20;20"  width="100" padding="2">
					<#if (currentSchemaValue['description'])?? >
						<row>
							<cell><para style="bold">${messageFormat(labels['table.field.description'])}</para></cell>
							<cell colspan="3"><para>${currentSchemaValue['description']}</para></cell>
						</row>
					</#if>
					<#if (currentSchemaValue['allOf'])?? >
						<#list currentSchemaValue['allOf'] as listAllOf>
							<#if (listAllOf['$ref'])?? >
								<row>
									<cell><para style="bold">${messageFormat(labels['type.extends'])}</para></cell>
									<cell colspan="3"><para>${listAllOf['$ref']}</para></cell>
								</row>
							<#elseif (listAllOf['type'])?? >
								<row>
									<cell><para style="bold">${messageFormat(labels['type.base'])}</para></cell>
									<cell colspan="3"><para>${listAllOf['type']}</para></cell>
								</row>
								<#if (listAllOf['properties'])?? >
									<@docMacro.printProperties propsMap=listAllOf['properties'] labelMap=labels/>
								</#if>
							</#if>
						</#list>
	
					</#if>
					<#if (currentSchemaValue['properties'])?? >
						<@docMacro.printProperties propsMap=currentSchemaValue['properties'] labelMap=labels/>
					</#if>
		    	</table>
			</#list> 
  		</#if>
  		</#if>
  					 
  </body>
  
</doc>