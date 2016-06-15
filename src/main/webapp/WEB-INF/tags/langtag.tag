<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path"
	value="<%=request.getServletContext().getContextPath()%>" />
<c:choose>
	<c:when test="${query == null }">
		<c:set var="url" value="?lang=" />
	</c:when>
	<c:otherwise>
		<c:set var="url" value="?${query }&lang=" />
	</c:otherwise>
</c:choose>
<div class="dropdown">
	<button class="dropBtn btn dropdown-toggle" type="button"
		id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
		aria-expanded="true">
		<fmt:message key="lang"/> <span class="caret"></span>
	</button>
	<ul class="dropdown-menu dropdown drpDropdown"
		aria-labelledby="dropdownMenu1">
		<li><a href="${path }/changeLocale.html?lang=ru"><fmt:message key="ru"/></a></li>
		<li><a href="${path  }/changeLocale.html?lang=en"><fmt:message key="en" /></a></li>
	</ul>
</div>

