function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.url = '/api/user';
    this.login_url = '/api/login';
    this.register_url = '/api/register';
    var self = this;

    function login(user) {
        return fetch(self.login_url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function updateUser(user) {
        return fetch(self.url, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                return response.json();
            });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function register(user) {
        return fetch(self.register_url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }
}