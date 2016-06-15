/**
 * Plain js validation implementation.
 * @author Yosin_Hasan
 * @version 1.0
 */
var signupForm = document.querySelector(".signupForm");
var firstName = signupForm.querySelector("input[name='firstName']");
var lastName = signupForm.querySelector("input[name='lastName']");
var email = signupForm.querySelector("input[name='email']");
var phone = signupForm.querySelector("input[name='phone']");
var password = signupForm.querySelector("input[name='password']");
var repeatPassword = signupForm.querySelector("input[name='repeatPassword']");
var submit = signupForm.querySelector("#submit");

firstName.addEventListener("blur", function (event) {
    var text = firstName.value;
    if (validate.string(text, 6)) {
        success(firstName);
    } else {
        error(firstName, "The length of string must be greater or equal to 6");
    }
});
lastName.addEventListener("blur", function (event) {
    var text = lastName.value;
    if (validate.string(text, 6)) {
        success(lastName);
    } else {
        error(lastName, "The length of string must be greater or equal to 6");
    }
});
email.addEventListener("blur", function (event) {
    var text = email.value;
    if (validate.email(text)) {
        success(email);
    } else {
        error(email, "Incorrect email format. (example@com.com)");
    }
})

phone.addEventListener("blur", function (event) {
    var text = phone.value;
    if (validate.phone(text)) {
        success(phone);
    } else {
        error(phone,
            "Incorrect phone format (+38(0xx) xxx xxxx | 0xx xxx xxxx)");
    }
});
password.addEventListener("blur", function (event) {
    var text = password.value;
    if (validate.password(text)) {
        success(password);
    } else {
        error(password,
            "The length of password must be more than 5 and less than 21");
    }
})
repeatPassword.addEventListener("blur", function (event) {
    var text = repeatPassword.value;
    if (validate.password(text, 0, password.value)) {
        success(repeatPassword);
    } else {
        error(repeatPassword, "Passwords do not match");
    }
});
submit.addEventListener("click", function (event) {
    var count = 0;
    var text = firstName.value;
    if (!validate.string(text, 6)) {
        count++;
        error(firstName, "The length of string must be greater or equal to 6");
    }
    text = lastName.value;
    if (!validate.string(text, 6)) {
        count++;
        error(lastName, "The length of string must be greater or equal to 6");
    }
    text = email.value;
    if (!validate.email(text)) {
        count++;
        error(email, "Incorrect email format. (example@com.com)");
    }
    text = phone.value;
    if (!validate.phone(text)) {
        count++;
        error(phone,
            "Incorrect phone format (+38(0xx) xxx xxxx | 0xx xxx xxxx)");
    }
    text = password.value;
    if (!validate.password(text)) {
        count++;
        error(password,
            "The length of password must be more than 5 and less than 21");
    }
    text = repeatPassword.value;
    if (!validate.password(text, 0, password.value)) {
        count++;
        error(repeatPassword, "Passwords do not match");
    }
    if (count != 0) {
        event.stopPropagation();
        event.preventDefault();
    }
})

function success(input) {
    input.parentNode.classList.remove("has-error");
    input.parentNode.classList.add("has-success");
    addMessage(input, "");
}
function error(input, msg) {
    input.parentNode.classList.remove("has-success");
    input.parentNode.classList.add("has-error");
    addMessage(input, msg);

}
function addMessage(object, message) {
    var span = object.parentNode.querySelector("span");
    span.innerText = message;
}
var validate = {
    string: function validateString(input, length) {
        var len = input.length;
        if (len < length) {
            return false;
        }
        return true;
    },
    email: function validateEmail(input) {
        var str = input;
        var reg = /^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,4}$/;
        if (str.match(reg)) {
            return true;
        }
        return false;
    },
    phone: function validatePhone(input) {
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
    password: function validatePassword(input, check, password) {
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
