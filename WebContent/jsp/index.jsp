<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title><s:text name="application.title"/></title>
	 <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	<sj:head jqueryui="true" jquerytheme="pepper-grinder"/>
</head>

<body>
<div class="titleDiv" style="margin-top:10px;"><s:text name="label.seccion.main"/></div>
<br/><br/>
<div style="width:50%;">
	<sj:menu id="menuWithItems" title="Administración">
		<sj:menuItem title="%{getText('label.seccion.manage.juegos')}" href="games!listing.action"/>
		<sj:menuItem title="%{getText('label.seccion.manage.clients')}" href="clients.action"/>
	</sj:menu>
</div>

</body>
</html>
