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
    var $updateBtn;

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        $('#createUser').click(createUser);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
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
            clone.find('.wbdv-edit').click(selectUser);

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
        var editBtn = $(event.currentTarget);
        var $tr = editBtn
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
    }

    function updateUser(event) {

        var user = new User();
        user.setFirstName($firstNameFld.val());
        user.setLastName($lastNameFld.val());
        user.setRole($roleFld.val());

        userService
            .updateUser(updateId, user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            findAllUsers();
        }
    }

})();