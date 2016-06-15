/**
 *
 * Script for basket implementation.
 *
 * @author Yosin_Hasan
 * @version 1.0
 */
$(function () {
    updateBasket();
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
    $(".simpleCart_empty").click(function () {
        $.ajax({
            url: "basket.html",
            type: 'POST',
            data: {
                action: "clear",
            },
            success: function (response) {
                var object = JSON.parse(response);
                $(".simpleCart_total").text(object['summ'] + " $");
                $(".basketDiv").html("No products in basket")
                $(".bag").text("My Shopping Bag(0)");
            }
        });
        return false;
    });
    $(".productDelete").click(function () {
        var id = $(this).parent().find("input[name='productId']").val();
        var delTag = $(this).parent().parent();
        $.ajax({
            url: "basket.html",
            type: 'POST',
            data: {
                action: "delete",
                productId: id
            },
            success: function (response) {
                var object = JSON.parse(response);
                $(".simpleCart_total").text(object['summ'] + " $");
                var amount = object['amount'];
                if (amount == 0) {
                    $(".basketDiv").html("No products in basket")
                } else {
                    delTag.remove();
                }
                $(".bag").text("My Shopping Bag(" + amount + ")");
            }
        });
        return false;
    });
    $(".basketDiv").find("input[name='amount']").change(
        function () {
            var amount = $(this).val();
            var id = $(this).parent().parent().find(
                "input[name='productId']").val();
            $.ajax({
                url: "basket.html",
                type: 'POST',
                data: {
                    action: "update",
                    productId: id,
                    amount: amount
                },
                success: function (response) {
                    var object = JSON.parse(response);
                    $(".simpleCart_total").text(object['summ'] + " $");
                }
            });
        });
    $("#submitOrder").click(
        function () {
            var val = $("#paymentId").val();
            var value;
            var creditNumber;
            var name;
            if (val == 0) {
                name = "localaddress";
                value = $("#localaddress").val();
            } else {
                name = "creditcardnumber";
                value = $("#creditcardnumber").val();
            }
            if (value == "") {
                alert("error occured, empty field");
                return false;
            } else {
                $(".basketDiv").find("form").append(
                    $("<input type='text' name='method' value='" + val
                        + "'/>"));

                $(".basketDiv").find("form").append(
                    $("<input type='hidden' name='" + name
                        + "' value='" + value + "'/>"));
                $(".basketDiv").find("form").submit();
            }

        });
    $("#paymentId").change(function () {
        var val = $(this).val();
        if (val == 0) {
            $(".creditcardnumber").addClass("hidden");
            $(".localaddress").removeClass("hidden");
        } else {
            $(".creditcardnumber").removeClass("hidden");
            $(".localaddress").addClass("hidden");
        }

    });

});
function updateBasket() {
    $.ajax({
        url: "basket.html",
        data: {
            action: "load",
            type: 'POST',
        },
        success: function (response) {
            var object = JSON.parse(response);
            $(".simpleCart_total").text(object['summ'] + " $");
        }
    });
    return false;
}
