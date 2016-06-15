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
								key="home" /> </a></li>
					<li class="active"><fmt:message key="search" /></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--prdt-starts-->
	<div class="prdt">
		<div class="container">
			<div class="prdt-top">
				<div id="showProducts" class="product-one">

					<c:choose>
						<c:when
							test="${requestScope['products'] != null && requestScope['products'].size() > 0}">
							<c:forEach var="item" items="${requestScope['products'] }">
								<div class="col-md-4 product-left p-left">
									<div class="product-main simpleCart_shelfItem">
										<a
											href="<%=getServletConfig().getServletContext().getContextPath()%>/product.html?id=${item.getId()}"
											class="mask"><img class="img-responsive zoom-img"
											src="assets/images/${item.getImage() }.png" alt="" /></a>
										<div class="product-bottom">
											<h3>${item.getName() }</h3>
											<p>
												<fmt:message key="exploreNow" />
											</p>
											<h4>
												<a class="item_add" href="#"><i></i></a> <span
													class=" item_price">$ ${item.getPrice() }</span> <input
													type="hidden" value="${item.getId() }">
											</h4>
										</div>
									</div>
								</div>
							</c:forEach>
							<div class="col-md-6">
								<c:set var="query" value="<%=request.getQueryString()%>"></c:set>
								<p:pagination count="${requestScope['count']}" url="search"
									query="${query }" />
							</div>
						</c:when>
						<c:otherwise>
							<h1>
								<fmt:message key="notfound" />
							</h1>
						</c:otherwise>

					</c:choose>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--user-end-->
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>