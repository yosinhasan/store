<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<!-- header-starts -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!-- header-ends -->
	<!--start-breadcrumbs-->
	<div class="breadcrumbs">
		<div class="container">
			<div class="breadcrumbs-main">
				<ol class="breadcrumb">
					<li><a
						href="<%=getServletConfig().getServletContext().getContextPath()%>/index.html"><fmt:message
								key="home" /></a></li>
					<li class="active"><fmt:message key="myAccount" /></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--account-starts-->
	<div class="account">
		<div class="container">
			<div class="account-top heading">
				<h2>
					<fmt:message key="myAccount" />
				</h2>
			</div>
			<div class="account-main">
				<form
					action="<%=getServletConfig().getServletContext().getContextPath()%>/signin.html"
					method="post">
					<div class="col-md-6 account-left">
						<h3>
							<fmt:message key="existUser" />
						</h3>
						<div class="account-bottom">
							<input placeholder="<fmt:message key="emailAddress" />" name="email" type="text"
								tabindex="3" required> <input
								placeholder="<fmt:message key="password" />" name="password" type="password"
								tabindex="4" required>
							<div class="address">
								<a class="forgot" href="#"><fmt:message key="forgotPassword" />?</a>
								<input type="submit" value="<fmt:message key="signIn" />">
							</div>
						</div>
					</div>
				</form>
				<div class="col-md-6 account-right account-left">
					<h3>
						<fmt:message key="newUser" />
						?
						<fmt:message key="createAccount" />
					</h3>
					<p>
						<fmt:message key="descriptionAccout" />
					</p>
					<a
						href="<%=getServletConfig().getServletContext().getContextPath()%>/signup.html"><fmt:message
							key="createAccount" /></a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--account-end-->
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>