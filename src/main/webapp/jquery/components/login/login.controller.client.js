//IIFE
(function () {

    jQuery(main);

    var userService = new UserServiceClient()
    var $usernameFld;
    var $passwordFld;
    var $loginBtn;
    var $dangerAlert;
    var $dangerClose;
    var $message;

    function main() {
        $loginBtn = $("#loginBtn")
            .click(login);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');

        $dangerAlert = $(".alert-danger");
        $dangerClose = $(".dangerClose")
            .click(dangerHide);
        $message = $("#message");
        dangerHide();
    }

    function dangerHide() {
        $dangerAlert.hide();
    }

    function login() {
        dangerHide();
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        if(username === '') {
            $message.text("Please enter a username");
            $dangerAlert.show();
        }
        else if(password === '') {
            $message.text("Please enter a password");
            $dangerAlert.show();
        }
        else{
            var user = new User(username,password);
            userService
                .login(user)
                .then(success);
        }
    }

    function success(response) {
        if(response === null) {
            $message.text("Incorrect username or password");
            $dangerAlert.show();
        } else {
            window.location.href = "../profile/profile.template.client.html?userId="+response.id;
        }
    }

})();