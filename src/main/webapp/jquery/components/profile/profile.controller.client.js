(function() {
    $(init);

    var $usernameFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;
    var $updateBtn;
    var userService = new UserServiceClient();

    function init() {
        $usernameFld = $("#usernameFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $('#roleFld');
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        findUserById(getUrlVars()["userId"]);
    }

    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

    function updateUser() {

        var user = new User();
        user.setUsername($usernameFld.val());
        user.setFirstName($firstNameFld.val());
        user.setLastName($lastNameFld.val());
        user.setRole($roleFld.val());

        userService
            .updateUser(user)
            .then(success);
    }

    function success(response) {
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
        $usernameFld.val(user.username);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
    }
})();