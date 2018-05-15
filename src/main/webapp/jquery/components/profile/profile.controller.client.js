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
    var $successAlert;
    var $successClose;
    var $dangerAlert;
    var $dangerClose;
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
        $successAlert = $(".alert-success");
        $successClose = $(".successClose")
            .click(successHide);
        $dangerAlert = $(".alert-danger");
        $dangerClose = $(".dangerClose")
            .click(dangerHide);
        findUserById(getUrlVars()["userId"]);
        successHide();
        dangerHide();

        $('#dateOfBirthFld')
            .datepicker({
                autoclose: true,
                format: 'yyyy-mm-dd',
                endDate: new Date(),
                todayHighlight: true
            });
    }

    function successHide() {
        $successAlert.hide();
    }

    function dangerHide() {
        $dangerAlert.hide();
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

        successHide();
        dangerHide();

        var user = new User();
        user.setUsername($usernameFld.val());
        user.setFirstName($firstNameFld.val());
        user.setLastName($lastNameFld.val());
        user.setPhone($phoneFld.val());
        user.setEmail($emailFld.val());
        user.setRole($roleFld.val());
        user.setDateOfBirth($dateOfBirthFld.val());

        userService
            .updateProfile(user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            $dangerAlert.show();
        } else {
            $successAlert.show();
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
        var dob = user.dateOfBirth;
        if(dob != '' && dob != null)
            $dateOfBirthFld.val(dob.substr(0,10));
        else
            $dateOfBirthFld.val('');
    }

    function logoutUser() {
        userService
            .logout();
    }

})();