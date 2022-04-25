<#macro printProperties propsMap labelMap>
	    		<row>
	    			<cell header="true"><para style="bold">${messageFormat(labelMap['table.field.name'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labelMap['table.field.type'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labelMap['table.field.example'])}</para></cell>
	    			<cell header="true"><para style="bold">${messageFormat(labelMap['table.field.description'])}</para></cell>
	    		</row>
				<#list propsMap?keys as currentFieldKey>
	    			<#assign currentFieldValue=propsMap[currentFieldKey]>
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
</#macro>