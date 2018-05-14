//IIFE
(function () {

    jQuery(main);

    var userService = new UserServiceClient()
    var $usernameFld;
    var $passwordFld;
    var $loginBtn;

    function main() {
        $loginBtn = $("#loginBtn")
            .click(login);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
    }

    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        var user = new User(username,password);

        userService
            .login(user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to log in')
        } else {
            window.location.href = "../profile/profile.template.client.html?userId="+response.id;
        }
    }

})();