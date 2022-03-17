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

  <meta>
  	<info name="margins">10;10;10;10</info>
  	<info name="page-width">29.7cm</info>
  	<info name="page-height">21cm</info>
  </meta>
 
  <body>
  
  		<h head-level="1" style="bold" size="16">Openapi Schema List</h>
  
		<#list yamlModel.schemas?keys as currentSchemaKey>
			<h head-level="2" style="bold" size="14" space-before="20">Schema : ${currentSchemaKey}</h>
			<#assign currentSchemaValue=yamlModel.schemas[currentSchemaKey]>
			<table columns="4" colwidths="30;30;20;20"  width="100" id="excel-table" padding="2">
	    		<row>
	    			<cell header="true"><para style="bold">Field</para></cell>
	    			<cell header="true"><para style="bold">Type</para></cell>
	    			<cell header="true"><para style="bold">Example</para></cell>
	    			<cell header="true"><para style="bold">Description</para></cell>
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