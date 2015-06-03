<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.title"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="label.seccion.manage.subscriptions"/></div>
		<br/><br/>
		<b>
		<s:url id="urlAnyadir" action="insertSubscription!goToInsert" escapeAmp="false">
			<s:param name="subscription.idGame" value="idGame" />
		</s:url>
		<a href="<s:property value="#urlAnyadir"/>"><s:text name="button.label.anadir"/></a>
		</b>
		<br/><br/>
		<h1> <s:text name="label.subscripcion.lista" /> </h1>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subscription.id"/></th>
		        <th><s:text name="label.subscription.name"/></th>
		        <th><s:text name="label.subscription.description"/></th>
		        <th><s:text name="label.subscription.price"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="subscriptions" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="name"/></td>
		            <td class="nowrap"><s:property value="description"/></td>
		            <td class="nowrap"><s:property value="price"/></td>
		            <td>
		           	<s:url id="URLClientesSubscription" action="subscriptors.action">
    					<s:param name="idSubscription" value="id" />
					</s:url>
					<a href="<s:property value="#URLClientesSubscription"/>">
						<img src="img/clientes.png" alt="subscriptions" class="icon" />
					</a>
		            
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
		
		<div class="footer">
			<s:a action="games"><s:text name="back.games" /></s:a>
		</div>

	</body>
</html>
