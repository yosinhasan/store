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
					<li><a
						href="<%=getServletConfig().getServletContext().getContextPath()%>/products.html"><fmt:message
								key="products" /></a></li>
					<li class="active"><c:if
							test="${requestScope['product'] != null}">
                        ${requestScope['product'].getName() }
                    </c:if></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--start-single-->
	<div class="single contact">
		<div class="container">
			<div class="single-main">
				<c:set var="product" value="${requestScope['product'] }" />
				<c:choose>
					<c:when test="${product != null}">
						<div class="sngl-top">
							<div class="col-md-5 single-top-left">
								<div class="flexslider">
									<ul class="slides">
										<c:forEach var="item" items="${product.getImages()}">
											<li data-thumb="assets/images/${item.getImage()}.jpg">
												<div class="thumb-image">
													<img src="assets/images/${item.getImage()}.jpg"
														data-imagezoom="true" class="img-responsive" alt="" />
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<!-- FlexSlider -->
								<script src="assets/js/imagezoom.js" type="text/javascript"></script>
								<script defer src="assets/js/jquery.flexslider.js"
									type="text/javascript"></script>
								<link rel="stylesheet" href="assets/css/flexslider.css"
									type="text/css" media="screen" />

								<script>
									// Can also be used with $(document).ready()
									$(window).load(function() {
										$('.flexslider').flexslider({
											animation : "slide",
											controlNav : "thumbnails"
										});
									});
								</script>
							</div>
							<div class="col-md-7 single-top-right">
								<div class="single-para simpleCart_shelfItem">
									<h2>${requestScope['product'].getName()}</h2>
									<h5 class="item_price">$
										${requestScope['product'].getPrice()}</h5>
									<p>${requestScope['product'].getDescription()}</p>
									<div class="available">
										<ul>
											<li><fmt:message key="color" /> <select>
													<c:forEach var="item" items="${product.getColor()}">
														<option>${item.getName()}</option>
													</c:forEach>
											</select></li>
											<li class="size-in"><fmt:message key="size" /><select>
													<c:forEach var="item" items="${product.getSize()}">
														<option>${item.getName()}</option>
													</c:forEach>
											</select></li>
											<div class="clearfix"></div>
										</ul>
									</div>
									<ul class="tag-men">
										<li><span><fmt:message key="category" /></span> <span
											class="women1">:
												${requestScope['category'].getName()},</span></li>
										<li><span><fmt:message key="brand" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
											<span class="women1">:
												${requestScope['brand'].getName()}</span></li>
									</ul>
									<br /> <input type="hidden" value="${product.getId() }" />
									<button class="black add-cart item_add btn btn-primary btn-lg">
										<fmt:message key="addToCart" />
									</button>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="latestproducts">
							<div class="product-one">
								<c:forEach var="item" items="${requestScope['relatedProducts']}">
									<div class="col-md-4 product-left p-left">
										<div class="product-main simpleCart_shelfItem">
											<a
												href="<%=getServletConfig().getServletContext().getContextPath()%>/product.html?id=${item.getId()}"
												class="mask"><img class="img-responsive zoom-img"
												src="assets/images/${item.getImage()}.png" alt="" /></a>
											<div class="product-bottom">
												<h3>${item.getName()}</h3>
												<p>
													<fmt:message key="exploreNow" />
												</p>
												<h4>
													<a class="item_add" href="#"><i></i></a> <span
														class=" item_price">$ ${item.getPrice()}</span>
												</h4>
											</div>
										</div>
									</div>
								</c:forEach>
								<div class="clearfix"></div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<h1>
							<fmt:message key="notfound" />
						</h1>
					</c:otherwise>
				</c:choose>
				<!-- side bar starts -->
			</div>
			<!-- side bar ends -->
		</div>
	</div>
	<!--end-single-->

	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>