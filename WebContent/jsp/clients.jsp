<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.title"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="label.seccion.manage.clients"/></div>
		<br/><br/>
		<b>
		<s:url id="urlClients" action="clients" escapeAmp="false"/>
		<a href="<s:property value="#urlClients"/>"><s:text name="button.label.find.all"/></a>
		
		| <s:url id="urlAnyadir" action="insertClient" escapeAmp="false"/>
		<a href="<s:property value="#urlAnyadir"/>"><s:text name="button.label.anadir"/></a>
		</b>
		<br/><br/>
		<h1> Búsqueda de Cliente </h1>
		<s:actionerror/>
		<s:form action="clients!doGetClient" method="post">
			<s:textfield name="dni" label="DNI" />
		    <s:submit value="%{getText('button.label.submit')}" />
		</s:form>

		<h1> <s:text name="label.client.lista" /></h1>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.client.id"/></th>
		        <th><s:text name="label.client.dni"/></th>
		        <th><s:text name="label.client.name"/></th>
		        <th><s:text name="label.client.surname"/></th>
		        <th><s:text name="label.client.address"/></th>
		        <th><s:text name="label.client.telnumber"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="clients" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="dni"/></td>
		            <td class="nowrap"><s:property value="name"/></td>
		            <td class="nowrap"><s:property value="surname"/></td>
		            <td class="nowrap"><s:property value="address"/></td>
		            <td class="nowrap"><s:property value="tel_number"/></td>
		            <td>
		            
		            <s:url id="URLeditClient" action="editClient!goToEdit.action">
    					<s:param name="editedClient" value="id" />
					</s:url>
					<a href="<s:property value="#URLeditClient"/>">
						<img src="img/edit.png" alt="edit" class="icon" />
					</a>
		            
		            <s:url id="URLdeleteClient" action="deleteClient.action">
    					<s:param name="deletedClient" value="id" />
					</s:url>
		            <a href="<s:property value="#URLdeleteClient"/>">
		            	<img src="img/delete-icon.png" alt="delete" class="icon" />
		            </a>
		            </td>
		        </tr>
		    </s:iterator>
		</table>

	</body>
</html>
