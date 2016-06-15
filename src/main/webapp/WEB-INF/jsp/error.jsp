<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<!-- header-starts -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!-- header-ends -->
	<div class="container">
		<div class="md-12">
			<%-- CONTENT --%>
			<div class="well text-uppercase">
				<fmt:message key="errorMsg" />
				:
			</div>
			<c:set var="code"
				value="${requestScope['javax.servlet.error.status_code']}" />
			<c:set var="message"
				value="${requestScope['javax.servlet.error.message']}" />
			<c:set var="exception"
				value="${requestScope['javax.servlet.error.exception']}" />
			<c:if test="${not empty code}">
				<div class="alert alert-danger text-uppercase">
					<fmt:message key="errorCode" />
					: ${code}
				</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="alert alert-danger text-uppercase">${message}</div>
			</c:if>
			<c:if test="${not empty exception}">
				<%
					exception.printStackTrace(new PrintWriter(out));
				%>
			</c:if>
			<%-- if we get this page using forward --%>
			<c:if test="${not empty requestScope.errorMessage}">
				<div class="alert alert-danger">${requestScope.errorMessage}</div>
			</c:if>
			<%-- CONTENT --%>
		</div>
		<div class="clearfix"></div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>