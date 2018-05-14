//IIFE
(function () {

    jQuery(main);

    var userService = new UserServiceClient()
    var $usernameFld;
    var $passwordFld;
    var $verifyPasswordFld;
    var $registerBtn;

    function main() {
        $registerBtn = $("#registerBtn")
            .click(register);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
    }

    function register() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        var user = new User(username,password);

        userService
            .register(user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            window.location.href = "../profile/profile.template.client.html?userId="+response.id;
        }
    }

})();