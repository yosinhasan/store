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
					<li class="active"><fmt:message key="signUp" /></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--register-starts-->
	<div class="register">
		<div class="container">
			<div class="register-top heading">
				<h2>
					<fmt:message key="signUp" />
				</h2>
				<div>${errorMsg}</div>
			</div>
			<form class="signupForm form"
				action="<%=getServletConfig().getServletContext().getContextPath()%>/signup.html"
				method="POST" enctype="multipart/form-data">
				<div class="register-main">
					<div class="col-md-6 account-left">
						<div class="form-group">
							<input class="form-control" name="firstName"
								placeholder="<fmt:message key="firstName" />" type="text"
								value="${data.get('firstName')}" tabindex="1"
								required="required" aria-describedby="helpBlock" /> <span
								class="help-block"></span>
						</div>
						<div class="form-group">
							<input class="form-control" name="lastName"
								placeholder="<fmt:message key="lastName" />" type="text"
								tabindex="2" value="${data.get('lastName')}" required="required"
								aria-describedby="helpBlock" /> <span class="help-block"></span>
						</div>
						<div class="form-group">
							<input class="form-control" name="email"
								value="${data.get('email')}"
								placeholder="<fmt:message key="emailAddress" />" type="text"
								tabindex="3" required="required" /><span class="help-block"></span>
						</div>

						<div class="form-group">
							<input class="form-control" name="phone"
								placeholder="<fmt:message key="phone" />"
								value="${data.get('phone')}" type="text" tabindex="3"
								required="required" /><span class="help-block"></span>

						</div>
						<div class="form-group">
							<label><fmt:message key="avatar" /></label> <input type="file"
								name="avatar">
						</div>
						<ul>
							<li><label class="radio left"> <input type="radio"
									name="sex" value="0" checked="checked"><i></i> <fmt:message
										key="male" />
							</label></li>
							<li><label class="radio"> <input type="radio"
									name="sex" value="1"><i></i> <fmt:message key="female" />
							</label></li>
							<div class="clearfix"></div>
						</ul>
					</div>
					<div class="col-md-6 account-left">
						<div class="form-group">
							<input class="form-control" name="password"
								placeholder="<fmt:message key="password" />" type="password"
								tabindex="4" required="required" /><span class="help-block"></span>
						</div>
						<div class="form-group">
							<input class="form-control" name="repeatPassword"
								placeholder="<fmt:message key="rePassword" />" type="password"
								tabindex="4" required="required" /><span class="help-block"></span>
						</div>
						<div class="form-group">
							<my:captcha />
						</div>
						<div class="form-group">
							<input class="form-control" name="captcha"
								placeholder="Input captcha" type="number" tabindex="4"
								required="required" /><span class="help-block"></span>
						</div>

					</div>
					<div class="clearfix"></div>
				</div>
				<div class="address submit">
					<input id="submit" type="submit"
						value="<fmt:message key="submit" />">
				</div>
			</form>
		</div>
	</div>
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>