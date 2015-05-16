<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title><s:text name="application.title"/></title>
	<sj:head jqueryui="true" jquerytheme="pepper-grinder"/>
</head>

<body>
<sj:menu id="menuWithItems" >
	<sj:menuItem title="Games" href="games.action"/>
	<sj:menuItem title="Struts2 jQuery News" menuIcon="ui-icon-extlink" href="http://www.jgeppert.com/category/java/struts2-jquery/"/>
</sj:menu>
</body>
</html>
