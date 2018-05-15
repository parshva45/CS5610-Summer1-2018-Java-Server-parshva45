(function() {
    $(init);

    var $usernameFld;
    var $firstNameFld;
    var $lastNameFld;
    var $phoneFld;
    var $emailFld;
    var $roleFld;
    var $dateOfBirthFld;
    var $updateBtn;
    var $logoutBtn;
    var userService = new UserServiceClient();

    function init() {
        $usernameFld = $("#usernameFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $phoneFld = $("#phoneFld");
        $emailFld = $("#emailFld");
        $roleFld = $('#roleFld');
        $dateOfBirthFld = $('#dateOfBirthFld');
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $logoutBtn = $("#logoutBtn")
            .click(logoutUser);
        findUserById(getUrlVars()["userId"]);

        $('#dateOfBirthFld')
            .datepicker({
                autoclose: true,
                format: 'yyyy-mm-dd',
                endDate: new Date(),
                todayHighlight: true
            });
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

    function updateProfile() {

        var user = new User();
        user.setUsername($usernameFld.val());
        user.setFirstName($firstNameFld.val());
        user.setLastName($lastNameFld.val());
        user.setPhone($phoneFld.val());
        user.setEmail($emailFld.val());
        user.setRole($roleFld.val());
        user.setDateOfBirth($dateOfBirthFld.val());
        console.log(JSON.stringify(user));

        userService
            .updateProfile(user)
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
        $phoneFld.val(user.phone);
        $emailFld.val(user.email);
        $roleFld.val(user.role);
        $dateOfBirthFld.val(user.dateOfBirth.substr(0,10));
    }

    function logoutUser() {
        userService
            .logout();
    }

})();