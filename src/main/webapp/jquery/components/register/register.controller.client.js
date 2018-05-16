//IIFE
(function () {

    jQuery(main);

    var userService = new UserServiceClient()
    var $usernameFld;
    var $passwordFld;
    var $verifyPasswordFld;
    var $registerBtn;
    var $dangerAlert;
    var $dangerClose;
    var $message;

    function main() {
        $registerBtn = $("#registerBtn")
            .click(register);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');

        $dangerAlert = $(".alert-danger");
        $dangerClose = $(".dangerClose")
            .click(dangerHide);
        $message = $("#message");
        dangerHide();
    }

    function dangerHide() {
        $dangerAlert.hide();
    }

    function register() {
        dangerHide();
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var verifyPassword = $verifyPasswordFld.val();

        if(username === '') {
            $message.text("Please enter a username");
            $dangerAlert.show();
        }
        else if(password === '') {
            $message.text("Please enter a password");
            $dangerAlert.show();
        }
        else if(verifyPassword === '') {
            $message.text("Please verify the password");
            $dangerAlert.show();
        }
        else if(password != verifyPassword) {
            $message.text("Passwords do not match");
            $dangerAlert.show();
        }
        else {
            var user = new User(username, password);
            userService
                .register(user)
                .then(success);
        }
    }

    function success(response) {
        if(response === null) {
            $message.text("This username has already been taken");
            $dangerAlert.show();
        } else {
            window.location.href = "../profile/profile.template.client.html?userId="+response.id;
        }
    }

})();