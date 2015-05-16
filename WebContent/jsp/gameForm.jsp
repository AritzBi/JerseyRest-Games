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

<s:form theme="bootstrap" cssClass="well form-horizontal" label="Juego" action="insertGame.action">
    <s:textfield
            label="Name"
            name="game.name" />

    <s:textfield
            label="Description"
            name="game.description"/>

    <s:textfield
            label="Type"
            name="game.type"/>
            
    <s:textfield
            label="Age"
            prepend="0-99"
            name="game.age"/>
            
    <s:submit cssClass="btn btn-primary" value="%{getText('button.label.submit')}" />
</s:form>

</body>
</html>