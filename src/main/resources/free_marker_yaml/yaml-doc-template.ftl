<?xml version="1.0" encoding="utf-8"?>
<doc
	xmlns="http://javacoredoc.fugerit.org"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://javacoredoc.fugerit.org https://www.fugerit.org/data/java/doc/xsd/doc-1-9.xsd" > 

  <!-- 
  	Sample Apache FreeMarker template for Fugerit Doc.
  	Note : this example has no intention of being a guidr to FreeMarker
  			(In case check FreeMarker documentation https://freemarker.apache.org/docs/index.html)
   -->

  <#assign labels=yamlModel.labels>

  <meta>
  
  	<!-- specific properties for PDF -->
  	<info name="margins">10;10;10;10</info>
  	<info name="page-width">29.7cm</info>
  	<info name="page-height">21cm</info>
  	
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
  		<h head-level="1" style="bold" size="16">${messageFormat(labels['title.schema.list'])}</h>
		<#list yamlModel.schemas?keys as currentSchemaKey>
			<h id="${currentSchemaKey}" head-level="2" style="bold" size="14" space-before="20">${messageFormat(labels['title.schema.current'])} : ${currentSchemaKey}</h>
			<#assign currentSchemaValue=yamlModel.schemas[currentSchemaKey]>
			<table id="table_${currentSchemaKey}" columns="4" colwidths="30;30;20;20"  width="100" padding="2">
	    		<row>
	    			<cell header="true"><para style="bold">${messageFormat(labels['table.field.name'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labels['table.field.type'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labels['table.field.example'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labels['table.field.description'])}</para></cell>
	    		</row>
	    		<#list currentSchemaValue['properties']?keys as currentFieldKey>
	    			<#assign currentFieldValue=currentSchemaValue['properties'][currentFieldKey]>
					<row>
						<cell><para>${currentFieldKey}</para></cell>
	    			<#if currentFieldValue['$ref']?? >
	    				<cell><para>${currentFieldValue['$ref']}</para></cell>
		    		<#elseif currentFieldValue['type']??>
		    			<#assign currentType=currentFieldValue['type']>
		    			<#if currentType = 'array' >
		    				<#if currentFieldValue['items']['$ref']?? >
		    					<#assign arrayType=currentFieldValue['items']['$ref']/>
		    				</#if>			
		    				<#assign currentType>${currentType}[${arrayType!''}]</#assign>
		    			</#if>
	    				<cell><para>${currentType}</para></cell>
		    		<#else>		
		    			<cell><para></para></cell>
					</#if>		 
					<cell><para>${currentFieldValue['example']!''}</para></cell>
					<cell><para>${currentFieldValue['description']!''}</para></cell>
		    		</row>	    		
	    		</#list>  
	    	</table>
		</#list>  
  </body>
  
</doc>