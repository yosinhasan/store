<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<!-- header-starts -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!-- header-ends -->

	<!--banner-starts-->
	<div class="bnr" id="home">
		<div id="top" class="callbacks_container">
			<ul class="rslides" id="slider4">
				<li><img src="assets/images/bnr-1.jpg" alt="" /></li>
				<li><img src="assets/images/bnr-2.jpg" alt="" /></li>
				<li><img src="assets/images/bnr-3.jpg" alt="" /></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	<!--banner-ends-->
	<!--Slider-Starts-Here-->
	<script src="assets/js/responsiveslides.min.js"></script>
	<script>
		// You can also use "$(window).load(function() {"
		$(function() {
			// Slideshow 4
			$("#slider4").responsiveSlides({
				auto : true,
				pager : true,
				nav : true,
				speed : 500,
				namespace : "callbacks",
				before : function() {
					$('.events').append("<li>before event fired.</li>");
				},
				after : function() {
					$('.events').append("<li>after event fired.</li>");
				}
			});

		});
	</script>
	<!--End-slider-script-->
	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="about-top grid-1">
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="assets/images/abt-1.jpg" alt="" />
						<figcaption>
							<h2>Smart watches</h2>
							<p>BULGARI: OCTO FINISSIMO RÉPÉTITION MINUTES</p>
						</figcaption>
					</figure>
				</div>
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="assets/images/abt-2.jpg" alt="" />
						<figcaption>
							<h4>Smart watches</h4>
							<p>CHANEL: THE MONSIEUR DE CHANEL</p>
						</figcaption>
					</figure>
				</div>
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="assets/images/abt-3.jpg" alt="" />
						<figcaption>
							<h4>Smart watches</h4>
							<p>JACOB &amp; CO.: ASTRONOMIA SKY</p>
						</figcaption>
					</figure>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--about-end-->
	<!--user-starts-->
	<div class="product">
		<div class="container">
			<div class="product-top">
				<div class="product-one">
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
			</div>
		</div>
	</div>
	<!--user-end-->
	<!--footer-starts-->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<!--footer-end-->
</body>
</html>