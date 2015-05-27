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
<s:form theme="bootstrap" cssClass="well form-horizontal" label="Subscripción" action="%{action}" method="post" validate="true">
<s:fielderror/>
	
	<s:hidden name="subscription.id" value="%{subscription.id}" />
     <s:hidden name="idSubscription" value="%{idSubscription}" /> 
     <s:select label="%{getText('label.client.dni')}" 
		headerValue="Seleccionar Cliente"
		list="clientes" 
		name="idClienteSeleccionado" listValue="dni" listKey="id" />
            
    <s:submit cssClass="btn btn-primary" value="%{getText('button.label.submit')}" />
</s:form>

</body>
</html>