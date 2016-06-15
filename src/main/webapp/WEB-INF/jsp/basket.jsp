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
					<li class="active"><fmt:message key="completeOrder" /></li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--start-ckeckout-->
	<div class="ckeckout">
		<div class="container">
			<div class="ckeck-top heading">
				<h2>
					<fmt:message key="checkout" />
				</h2>
			</div>
			<div class="ckeckout-top">
				<div class="cart-items">
					<h3 class="bag">
						<fmt:message key="bag" />
						(${sessionScope['basketService'].countAmount() })
					</h3>
					<div class="in-check">
						<div class="table-responsive basketDiv">
							<c:choose>
								<c:when
									test="${sessionScope['basketService'].countAmount() > 0}">
									<table class="basketTable table table-striped">
										<thead>
											<tr>
												<th><fmt:message key="item" /></th>
												<th><fmt:message key="name" /></th>
												<th><fmt:message key="price" /></th>
												<th><fmt:message key="amount" /></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item"
												items="${sessionScope['basketService'].findAll() }">
												<tr>
													<td><img
														src="assets/images/${item['key'].getImage() }.png"
														class="img-responsive" alt=""></td>
													<td>${item['key'].getName() }</td>
													<td>${item['key'].getPrice() }$</td>
													<td><input min="1" class="form-control" type="number"
														name="amount" value="${item['value'] }" /></td>
													<td><input type="hidden" name="productId"
														value="${item['key'].getId() }" />
														<button type="button" class="productDelete btn btn-danger">
															<fmt:message key="delete" />
														</button></td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="4" class="text-right">
													<button type="button"
														class="simpleCart_empty btn btn-danger btn-lg">
														<fmt:message key="emptyCart" />
													</button>
												</td>
												<td colspan="1" class="text-right">
													<form action="order.html" method="post">
														<!-- Large modal -->
														<!-- Button trigger modal -->
														<button type="button" class="btn btn-primary btn-lg"
															data-toggle="modal" data-target="#myModal">
															<fmt:message key="completeProcedure" />
														</button>
													</form>
												</td>
											</tr>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<fmt:message key="basketNotFound" />
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade  bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<fmt:message key="completeOrder" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="paymentId"><fmt:message key="paymentMethod" />:
						</label> <select id="paymentId" class="form-control">
							<option selected="selected" disabled="disabled"><fmt:message
									key="choose" /></option>
							<option value="0"><fmt:message key="courier" /></option>
							<option value="1"><fmt:message key="creditCard" /></option>
						</select>
					</div>
					<div class="form-group hidden localaddress">
						<label for="localaddress"><fmt:message key="localAddress" />:</label>
						<input type="text" class="form-control" id="localaddress"
							placeholder="input local address" required="required">
					</div>
					<div class="form-group hidden creditcardnumber">
						<label for="creditcardnumber"><fmt:message
								key="creditCardNumber" /> </label> <input type="number"
							class="form-control" id="creditcardnumber"
							placeholder="input credit card number" required="required">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
								key="close" /></button>
					<button id="submitOrder" type="button" class="btn btn-primary"><fmt:message
								key="submit" /></button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!--end-ckeckout-->
	<!--user-end-->
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>