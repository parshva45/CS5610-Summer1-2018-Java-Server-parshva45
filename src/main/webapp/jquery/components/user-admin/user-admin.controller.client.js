//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient()
    var updateId;
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;
    var $createBtn;
    var $updateBtn;
    var $cancelBtn;

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        $createBtn = $("#createBtn")
            .click(createUser);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $cancelBtn = $("#cancelBtn")
            .click(findAllUsers);

        findAllUsers();
    }

    function findAllUsers() {
        $usernameFld.prop('disabled', false);
        $passwordFld.prop('disabled', false);
        $createBtn.show();
        $updateBtn.hide();
        $cancelBtn.hide();
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();

        var user = new User(username,password,firstName,lastName,role);

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        $usernameFld.val('');
        $passwordFld.val('');
        $firstNameFld.val('');
        $lastNameFld.val('');
        $roleFld.val('Student');
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.wbdv-delete').click(deleteUser);
            clone.find('.wbdv-select').click(selectUser);

            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-firstName')
                .html(user.firstName);
            clone.find('.wbdv-lastName')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            tbody.append(clone);
        }
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function selectUser(event) {
        var selectBtn = $(event.currentTarget);
        var $tr = selectBtn
            .parent()
            .parent();
        updateId = $tr.attr('id');
        var username = $tr.find('.wbdv-username').text();
        var firstName = $tr.find('.wbdv-firstName').text();
        var lastName = $tr.find('.wbdv-lastName').text();
        var role = $tr.find('.wbdv-role').text();
        var user = new User(username,'',firstName,lastName,role);
        renderUser(user);
    }

    function renderUser(user){
        $usernameFld.val(user.getUsername());
        $firstNameFld.val(user.getFirstName());
        $lastNameFld.val(user.getLastName());
        $roleFld.val(user.getRole());
        $updateBtn.show();
        $cancelBtn.show();
        $createBtn.hide();
        $usernameFld.prop('disabled', true);
        $passwordFld.prop('disabled', true);
    }

    function updateProfile(event) {

        var user = new User();
        user.setFirstName($firstNameFld.val());
        user.setLastName($lastNameFld.val());
        user.setRole($roleFld.val());

        userService
            .updateProfile(updateId, user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            findAllUsers();
            $updateBtn.hide();
            $cancelBtn.hide();
            $createBtn.show();
            $usernameFld.prop('disabled', false);
            $passwordFld.prop('disabled', false);
        }
    }

})();