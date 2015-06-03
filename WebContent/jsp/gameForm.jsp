<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <sb:head/>
	    <title><s:text name="application.title"/></title>
</head>
<body>
<div class="titleDiv" style="margin-top:10px;"><s:text name="label.seccion.insertar.juegos"/></div>
<br/><br/>
<s:actionerror/>
<s:actionmessage/>		
<s:form theme="bootstrap" cssClass="well form-horizontal" label="Juego" action="%{action}" method="post" validate="true">
<s:fielderror/>
	
	<s:hidden name="game.id" value="%{game.id}" />
	
    <s:textfield
            label="%{getText('label.game.name')}"
            name="game.name" value="%{game.name}" />

    <s:textfield
            label="%{getText('label.game.description')}"
            name="game.description" value="%{game.description}" />

    <s:textfield
            label="%{getText('label.game.type')}"
            name="game.type" value="%{game.type}" />
            
    <s:textfield
            label="%{getText('label.game.age')}"
            name="game.age" value="%{game.age}"/>
            
    <s:submit cssClass="btn btn-primary" value="%{getText('button.label.submit')}" />
</s:form>

</body>
</html>