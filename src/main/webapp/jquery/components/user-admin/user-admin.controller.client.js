//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient()
    var updateId;
    var $usernameFld;
    var $password;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;
    var $updateBtn;

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        $('#createUser').click(createUser);
        //$('.wbdv-update').click(updateUser);
        $usernameFld = $('#usernameFld');
        $password = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $updateBtn = $("#updateBtn")
            .click(updateUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        var username = $usernameFld.val();
        var password = $password.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.wbdv-delete').click(deleteUser);
            clone.find('.wbdv-edit').click(editUser);

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

    function editUser(event) {
        var editBtn = $(event.currentTarget);
        var $tr = editBtn
            .parent()
            .parent();
        updateId = $tr.attr('id');
        var username = $tr.find('.wbdv-username').text();
        $usernameFld.val(username);
        var firstName = $tr.find('.wbdv-firstName').text();
        $firstNameFld.val(firstName);
        var lastName = $tr.find('.wbdv-lastName').text();
        $lastNameFld.val(lastName);
        var role = $tr.find('.wbdv-role').text();
        $roleFld.val(role);
    }

    function updateUser(event) {
        var user = {
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val()
        };

        userService
            .updateUser(updateId, user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            findAllUsers();
            $usernameFld.val('');
            $firstNameFld.val('');
            $lastNameFld.val('');
            $roleFld.val('Student');
        }
    }

})();