<#macro printAdditionalProperty currentFieldValue labelMap key><#if (currentFieldValue[key])??>, ${messageFormat(labelMap['label.'+key])}:${currentFieldValue[key]?string["#"]}</#if></#macro>

<#macro printAdditionalProperties currentFieldValue labelMap version><#if version &gt; 0 ><@printAdditionalProperty currentFieldValue=currentFieldValue labelMap=labelMap key='minLength'/><@printAdditionalProperty currentFieldValue=currentFieldValue labelMap=labelMap key='maxLength'/><@printAdditionalProperty currentFieldValue=currentFieldValue labelMap=labelMap key='minimum'/><@printAdditionalProperty currentFieldValue=currentFieldValue labelMap=labelMap key='maximum'/></#if></#macro>

<#macro printProperties propsMap labelMap version>
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
							<#elseif currentFieldValue['items']['type']??>
								<#assign arrayType=currentFieldValue['items']['type']/>
		    				</#if>			
		    				<#assign currentType>${currentType}[${arrayType!''}]</#assign>
		    			</#if>
	    				<cell><para>${currentType}</para></cell>
		    		<#else>		
		    			<cell><para></para></cell>
					</#if>		 
					<cell><para>${printExample(currentFieldValue['example'])}</para></cell>
					<cell><para>${currentFieldValue['description']!''}<#if (currentFieldValue['deprecated']!false)> (${messageFormat(labelMap['table.field.deprecated'])})</#if><@printAdditionalProperties currentFieldValue=currentFieldValue labelMap=labelMap version=version/></para></cell>
		    		</row>	    		
	    		</#list>
</#macro>