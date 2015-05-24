<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.title"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="label.seccion.manage.subscriptors"/></div>
		<br/><br/>
		<b>
		<s:url id="urlAnyadir" action="insertSubscriptor!goToInsert" escapeAmp="false">
			<s:param name="subscription.idGame" value="idGame" />
		</s:url>
		<a href="<s:property value="#urlAnyadir"/>"><s:text name="button.label.anadir"/></a>
		</b>
		<br/><br/>
		<h1> <s:text name="label.subscriptor.lista" /> </h1>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subscriptor.id"/></th>
		         <th><s:text name="label.subscriptor.dni"/></th>
		        <th><s:text name="label.subscriptor.name"/></th>
		        <th><s:text name="label.subscriptor.surname"/></th>
		        <th><s:text name="label.subscriptor.address"/></th>
		         <th><s:text name="label.subscriptor.telephone"/></th>
		        <th><s:text name="label.subscriptor.fechaSus"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="subscriptors" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="cliente.id"/></td>
		            <td class="nowrap"><s:property value="cliente.dni"/></td>
		            <td class="nowrap"><s:property value="cliente.name"/></td>
		            <td class="nowrap"><s:property value="cliente.surname"/></td>
		            <td class="nowrap"><s:property value="cliente.address"/></td>
		            <td class="nowrap"><s:property value="cliente.tel_number"/></td>
		            <td class="nowrap"><s:property value="fechaSuscripcion"/></td>
		            <td>
		            
		            <s:url id="URLeditSubscription" action="editSubscription!goToEditSubscription.action" escapeAmp="false">
    					<s:param name="editedSubscription" value="id" />
					</s:url>
					<a href="<s:property value="#URLeditSubscription"/>">
						<img src="img/edit.png" alt="edit" class="icon" />
					</a>
		            
		            <s:url id="URLDeleteSubscription" action="deleteSubscription.action" escapeAmp="false">
    					<s:param name="deletedSubscription" value="id" />
    					<s:param name="idGame" value="idGame" />
					</s:url>
		            <a href="<s:property value="#URLDeleteSubscription"/>">
		            	<img src="img/delete-icon.png" alt="delete" class="icon" />
		            </a>
		            </td>
		        </tr>
		    </s:iterator>
		</table>

	</body>
</html>
