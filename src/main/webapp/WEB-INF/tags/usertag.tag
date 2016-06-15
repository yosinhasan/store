<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="user" value="${sessionScope['user'] }" />
<c:set var="path"
	value="<%=request.getServletContext().getContextPath()%>" />
<c:choose>
	<c:when test="${user == null }">
		<form class="form-inline" action="${path }/signin.html" method='post'>
			<div class="form-group col-md-3">
				<input type="email" name="email" class="form-control"
					placeholder="<fmt:message key="emailAddress" /> ">
			</div>
			<div class="form-group col-md-3">
				<input type="password" name="password" class="form-control"
					placeholder="<fmt:message key="password" />">
			</div>
			<div class="form-group col-md-3">
				<button type="submit" class="btn btn-default"><fmt:message key="signIn" /></button>
			</div>
		</form>
	</c:when>
	<c:otherwise>
		<img src="${path }/images/avatar${user.getId()}.jpg"
			class="avatar img-rounded">
		<form action="${path }/logout.html" method='post'>
			<ul class="avatarDropdown displayNone">
				<li><button class='btn btn-primary' type='submit'><fmt:message key="logOut" /> </button></li>
			</ul>
		</form>
	</c:otherwise>
</c:choose>


