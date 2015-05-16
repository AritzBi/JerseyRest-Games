<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
		<sb:head/>
	</head>
	<body>
		<s:form action="doLogin" method="POST" theme="bootstrap" cssClass="form-horizontal" label=" Login to GameOn Application">
			<s:actionerror theme="bootstrap" />
			<s:textfield name="username" label="%{getText('label.login.name')}" value="Admin"/>
			<s:password name="password" label="%{getText('label.login.password')}" value="Admin" />
			<s:submit value="%{getText('label.login.button')}" align="center" cssClass="btn" />
		</s:form>
	</body>

</html>