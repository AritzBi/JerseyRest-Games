<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.title"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="label.seccion.manage.juegos"/></div>
		<br/><br/>
		<b>
		<s:url id="urlGames" action="games" escapeAmp="false"/>
		<a href="<s:property value="#urlGames"/>"><s:text name="button.label.find.all"/></a>
		
		| <s:url id="urlAnyadir" action="games!insertGame" escapeAmp="false"/>
		<a href="<s:property value="#urlAnyadir"/>"><s:text name="button.label.anadir"/></a>
		</b>
		<br/><br/>
		<h1> Búsqueda de juego </h1>
		<s:actionerror/>
		<s:form action="games!doGetGame" method="post">
			<s:textfield name="searchedGame" label="Searched Id Game" />
		    <s:submit value="%{getText('button.label.submit')}" />
		</s:form>

		<h1> Lista de juegos </h1>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.game.id"/></th>
		        <th><s:text name="label.game.name"/></th>
		        <th><s:text name="label.game.description"/></th>
		        <th><s:text name="label.game.age"/></th>
		        <th><s:text name="label.game.type"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="games" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="name"/></td>
		            <td class="nowrap"><s:property value="description"/></td>
		            <td class="nowrap"><s:property value="age"/></td>
		            <td class="nowrap"><s:property value="type"/></td>
		            <td>
		           	<s:url id="URLsubscriptionsGame" action="subscriptions.action">
    					<s:param name="idGame" value="id" />
					</s:url>
					<a href="<s:property value="#URLsubscriptionsGame"/>">
						<img src="img/subscriptions.png" alt="subscriptions" class="icon" />
					</a>
		            
		            <s:url id="URLeditGame" action="editGame!goToEditGame.action">
    					<s:param name="editedGame" value="id" />
					</s:url>
					<a href="<s:property value="#URLeditGame"/>">
						<img src="img/edit.png" alt="edit" class="icon" />
					</a>
		            
		            <s:url id="URLdeleteGame" action="deleteGame.action">
    					<s:param name="deletedGame" value="id" />
					</s:url>
		            <a href="<s:property value="#URLdeleteGame"/>">
		            	<img src="img/delete-icon.png" alt="delete" class="icon" />
		            </a>
		            </td>
		        </tr>
		    </s:iterator>
		</table>

	</body>
</html>
