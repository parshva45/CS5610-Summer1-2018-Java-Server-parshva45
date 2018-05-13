(function() {
    $(init);

    var $staticEmail;
    var $firstName;
    var $lastName;
    var $role;
    var $updateBtn;
    var userService = new UserServiceClient();

    function init() {
        $staticEmail = $("#staticEmail");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role = $('#role');
        $updateBtn = $("#updateBtn")
            .click(updateUser);

        findUserById(32);
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val()
        };

        userService
            .updateUser(32, user)
            .then(success);
    }

    function success(response) {
        console.log(response)
        if(response === null) {
            alert('unable to update')
        } else {
            alert('success');
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $staticEmail.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
    }
})();