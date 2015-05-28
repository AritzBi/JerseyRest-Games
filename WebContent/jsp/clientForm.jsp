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
<div class="titleDiv" style="margin-top:10px;"><s:text name="label.seccion.manage.clients"/></div>
<br/><br/>
<s:actionerror/>
<s:actionmessage/>		
<s:form theme="bootstrap" cssClass="well form-horizontal" label="Cliente" action="%{action}" method="post" validate="true">
<s:fielderror/>
	
	<s:hidden name="client.id" value="%{client.id}" />
	
	<s:textfield
            label="%{getText('label.client.dni')}"
            name="client.dni" value="%{client.dni}" disabled="%{action==editClient.action}" />
	
    <s:textfield
            label="%{getText('label.client.name')}"
            name="client.name" value="%{client.name}" />

    <s:textfield
            label="%{getText('label.client.surname')}"
            name="client.surname" value="%{client.surname}" />

    <s:textfield
            label="%{getText('label.client.address')}"
            name="client.address" value="%{client.address}" />
            
    <s:textfield
            label="%{getText('label.client.telnumber')}"
            name="client.tel_number" value="%{client.tel_number}" />
            
    <s:submit cssClass="btn btn-primary" value="%{getText('button.label.submit')}" />
</s:form>

</body>
</html>