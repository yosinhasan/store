/**
 * Work script.
 * @author Yosin_Hasan
 * @type {*|jQuery|HTMLElement}
 */
var form = $(".ajaxHandler");
var settings = $(".settings");
var showProducts = $("#showProducts");
var pagination = $(".pagination");
$(function () {
    $(".avatar").click(function () {
        var dropdown = $(".avatarDropdown");
        if (dropdown.hasClass("displayNone")) {
            dropdown.removeClass("displayNone");
        } else {
            dropdown.addClass("displayNone");
        }
    });
    settings.find("button").click(function (event) {
        var data = form.serialize();
        var params = settings.serialize();
        var query = data + "&" + params;
        ajaxHandler(query);
    });
    form.find("input").click(function (event) {
        var data = form.serialize();
        var params = settings.serialize();
        var query = data + "&" + params;
        ajaxHandler(query);
    });
});
function ajaxHandler(query) {
    $.ajax({
        url: "products.html",
        type: 'POST',
        data: query,
        success: function (response) {
            count = response['count'];
            limit = response['limit'];
            page = response['page'];
            drawProducts(response['items']);
            drawPagination(count, limit, page);
            addPaginationListerner();
            addAddItemListener();
        }
    });
}
function drawProducts(objects) {
    var html = "";
    var mainDiv = "<div class='col-md-4 product-left p-left'><div class='product-main simpleCart_shelfItem'>";
    for (var i in objects) {
        var a = "<a href='product?id=" + objects[i]['id']
            + "' class='mask'><img class='img-responsive zoom-img'"
            + "src='assets/images/" + objects[i]['image']
            + ".png' alt=''/></a>";
        var h3 = " <h3>" + objects[i]['name'] + "</h3>"
        var price = "<h4><a class='item_add' href='#'><i></i></a><span class='item_price'>$ "
            + objects[i]['price']
            + "</span><input type='hidden' value='"
            + objects[i]['id'] + "'> </h4>";
        html += mainDiv + a + "<div class='product-bottom'>" + h3
            + "<p>Explore Now</p>" + price
            + "</div></div></div>";
    }
    if (html == "") {
        html = "<h1>Products not found</h1>";
    }
    showProducts.hide().html($(html)).fadeIn(300);
}
function drawPagination(count, limit, page) {
    var pages = Math.ceil(count / limit);
    var objects = "";
    if (page > pages) {
        page = pages;
    }
    if (pages > 1) {
        if (page - 2 >= 1) {
            objects += "<li><a href='#' link='" + (page - 1)
                + "'>&Lt;</a></li>";
            objects += "<li><a href='#' link='" + (page - 2) + "'>"
                + (page - 2) + "</a></li>";
            objects += "<li><a href='#' link='" + (page - 1) + "'>"
                + (page - 1) + "</a></li>";
        } else if (page - 1 >= 1) {
            objects += "<li><a href='#' link='" + (page - 1)
                + "'>&Lt;</a></li>";
            objects += "<li><a href='#' link='" + (page - 1) + "'>"
                + (page - 1) + "</a></li>";
        }
        objects += "<li class='active'><a href='#' link='" + page + "'>" + page
            + "</a></li>";
        if (page + 2 <= pages) {
            objects += "<li><a href='#' link='" + (page + 1) + "'>"
                + (page + 1) + "</a></li>";
            objects += "<li><a href='#' link='" + (page + 2) + "'>"
                + (page + 2) + "</a></li>";
            objects += "<li><a href='#' link='" + (page + 1)
                + "'>&Gt;</a></li>";
        } else if (page < pages) {
            objects += "<li><a href='#' link='" + (page + 1) + "'>"
                + (page + 1) + "</a></li>";
            objects += "<li><a href='#' link='" + (page + 1)
                + "'>&Gt;</a></li>";
        }
    }
    pagination.hide().html($(objects)).show();
}

function addPaginationListerner() {
    pagination.find("a").on("click", function (event) {
        var page = $(this).attr("link");
        var data = form.serialize();
        var params = settings.serialize();
        var query = data + "&" + params + "&page=" + page;
        ajaxHandler(query);
        return false;
    });
}
function addAddItemListener() {
    $(".item_add").click(function () {
        var id = $(this).parent().find("input").val();
        $.ajax({
            url: "basket.html",
            type: 'POST',
            data: {
                action: "addItem",
                productId: id
            },
            success: function (response) {
                var object = JSON.parse(response);
                $(".simpleCart_total").text(object['summ'] + " $");
            }
        });
        return false;
    });
}
