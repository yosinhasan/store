/**
 * Jquery validation implementation.
 * @author Yosin_Hasan
 * @version 1.0
 */
$(document)
		.ready(
				function() {
					var signupForm = $(".signupForm");
					var firstName = signupForm.find("input[name='firstName']");
					var lastName = signupForm.find("input[name='lastName']");
					var email = signupForm.find("input[name='email']");
					var phone = signupForm.find("input[name='phone']");
					var password = signupForm.find("input[name='password']");
					var repeatPassword = signupForm
							.find("input[name='repeatPassword']");
					var submit = signupForm.find("#submit");

					firstName
							.blur(function(event) {
								var text = $(this).val();
								if (validate.string(text, 6)) {
									$(this).parent().removeClass("has-error");
									$(this).parent().addClass("has-success");
									$(this).next("span.help-block").text("");
								} else {
									$(this).parent().removeClass("has-success");
									$(this).parent().addClass("has-error");
									$(this)
											.next("span.help-block")
											.text(
													"The length of string must be greater or equal to 6");
								}
							});
					lastName
							.blur(function(event) {
								var text = $(this).val();
								if (validate.string(text, 6)) {
									$(this).parent().removeClass("has-error");
									$(this).parent().addClass("has-success");
									$(this).next("span.help-block").text("");
								} else {
									$(this).parent().removeClass("has-success");
									$(this).parent().addClass("has-error");
									$(this)
											.next("span.help-block")
											.text(
													"The length of string must be greater or equal to 6");
								}
							});
					email
							.blur(function(event) {
								var text = $(this).val();
								if (validate.email(text)) {
									$(this).parent().removeClass("has-error");
									$(this).parent().addClass("has-success");
									$(this).next("span.help-block").text("");
								} else {
									$(this).parent().removeClass("has-success");
									$(this).parent().addClass("has-error");
									$(this)
											.next("span.help-block")
											.text(
													"Incorrect email format. (example@com.com)");
								}
							});
					phone
							.blur(function(event) {
								var text = $(this).val();
								if (validate.phone(text)) {
									$(this).parent().removeClass("has-error");
									$(this).parent().addClass("has-success");
									$(this).next("span.help-block").text("");
								} else {
									$(this).parent().removeClass("has-success");
									$(this).parent().addClass("has-error");
									$(this)
											.next("span.help-block")
											.text(
													"Incorrect phone format (+38(0xx) xxx xxxx | 0xx xxx xxxx)");
								}
							});
					password
							.blur(function(event) {
								var text = $(this).val();
								if (validate.password(text)) {
									$(this).parent().removeClass("has-error");
									$(this).parent().addClass("has-success");
									$(this).next("span.help-block").text("");
								} else {
									$(this).parent().removeClass("has-success");
									$(this).parent().addClass("has-error");
									$(this)
											.next("span.help-block")
											.text(
													"The length of password must be more than 5 and less than 21");
								}
							});
					repeatPassword.blur(function(event) {
						var text = $(this).val();
						var passwordVal = password.val();
						if (validate.password(text, 0, passwordVal)) {
							$(this).parent().removeClass("has-error");
							$(this).parent().addClass("has-success");
							$(this).next("span.help-block").text("");
						} else {
							$(this).parent().removeClass("has-success");
							$(this).parent().addClass("has-error");
							$(this).next("span.help-block").text(
									"Passwords do not match");

						}

					});
					submit
							.click(function(event) {
								var count = 0;
								var text = firstName.val();
								if (!validate.string(text, 6)) {
									count++;
									$(firstName).parent().removeClass(
											"has-success");
									$(firstName).parent().addClass("has-error");
									$(firstName)
											.next("span.help-block")
											.text(
													"The length of string must be greater or equal to 6");
								}
								text = $(lastName).val();
								if (!validate.string(text, 6)) {
									count++;
									$(lastName).parent().removeClass(
											"has-success");
									$(lastName).parent().addClass("has-error");
									$(lastName)
											.next("span.help-block")
											.text(
													"The length of string must be greater or equal to 6");
								}
								text = $(email).val();
								if (!validate.email(text)) {
									count++;
									$(email).parent()
											.removeClass("has-success");
									$(email).parent().addClass("has-error");
									$(email)
											.next("span.help-block")
											.text(
													"Incorrect email format. (example@com.com)");
								}
								text = $(phone).val();
								if (!validate.phone(text)) {
									count++;
									$(phone).parent()
											.removeClass("has-success");
									$(phone).parent().addClass("has-error");
									$(phone)
											.next("span.help-block")
											.text(
													"Incorrect phone format (+38(0xx) xxx xxxx | 0xx xxx xxxx)");
								}
								text = $(password).val();
								if (!validate.password(text)) {
									count++;
									$(password).parent().removeClass(
											"has-success");
									$(password).parent().addClass("has-error");
									$(password)
											.next("span.help-block")
											.text(
													"The length of password must be more than 5 and less than 21");
								}
								text = $(repeatPassword).val();
								var passwordVal = password.val();
								if (!validate.password(text, 0, passwordVal)) {
									count++;
									$(repeatPassword).parent().removeClass(
											"has-success");
									$(repeatPassword).parent().addClass(
											"has-error");
									$(repeatPassword).next("span.help-block")
											.text("Passwords do not match");
								}
								if (count > 0) {
									return false;
								}
							});
					submit.dblclick(function() {
						return false;
					});
				});

var validate = {
	string : function validateString(input, length) {
		var len = input.length;
		if (len < length) {
			return false;
		}
		return true;
	},
	email : function validateEmail(input) {
		var str = input;
		var reg = /^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,4}$/;
		if (str.match(reg)) {
			return true;
		}
		return false;
	},
	phone : function validatePhone(input) {
		var str = input;
		var reg = /(\+)*(\s)*(\-)*(\()*(\))*/g;
		var result = str.replace(reg, "");
		if (result.length == 10 || result.length == 12) {
			var reg2 = /^(380(\d){9})|(0\d{9})$/;
			if (result.match(reg2)) {
				return true;
			}
		}
		return false;
	},
	password : function validatePassword(input, check, password) {
		if (check != 0) {
			var len = input.length;
			if (len < 6 || len > 20) {
				return false;
			}
			return true;
		} else {
			if (input.length > 5 && input.length < 20) {
				if (input == password) {
					return true;
				}
			}
		}
		return false;
	}
}
