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
					<li class="active"><fmt:message key="products" /></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--prdt-starts-->
	<div class="prdt">
		<div class="container">
			<div class="prdt-top">
				<div class="col-md-9 prdt-left">
					<form class="form-inline settings">
						<div class="form-group">
							<label for="order"><fmt:message key="sort" /> :</label> <select
								name="orderId" class="form-control" id="order">
								<option value="0"><fmt:message key="name" />
								</option>
								<option value="1"><fmt:message key="price" />
								</option>
								<option value="2"><fmt:message key="category" />
								</option>
								<option value="3"><fmt:message key="brand" /></option>
							</select>
						</div>
						<div class="form-group">
							<select name="desc" class="form-control">
								<option value="0"><fmt:message key="ascend" /></option>
								<option value="1"><fmt:message key="descend" /></option>
							</select>
						</div>
						<div class="form-group">
							<select name="limit" class="form-control" id="sel3">
								<option value="3">3</option>
								<option value="5">5</option>
								<option value="10">10</option>
							</select>
						</div>
						<div class="form-group">
							<label for="stp"><fmt:message key="price" />:</label> <input
								name="startPrice" type="text" class="form-control" id="stp">
						</div>
						<div class="form-group">
							<input name="endPrice" type="text" class="form-control" id="ep">
						</div>
						<div class="form-group">
							<button type="button" class="btn-primary btn">
								<fmt:message key="filter" />
							</button>
						</div>

					</form>
					<div class="clearfix"></div>
					<div id="showProducts" class="product-one">
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
									<!-- 
                                <div class="srch srch1">
                                    <span>-50%</span>
                                </div>
                                 -->
								</div>
							</div>
						</c:forEach>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
					<div class="col-md-6">
						<p:pagination count="${requestScope['count']}" url="products"
							query="" />
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- side bar starts -->
				<%@include file="/WEB-INF/jspf/left.jspf"%>
				<!-- side bar ends -->

			</div>
		</div>
	</div>
	<!--user-end-->
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>