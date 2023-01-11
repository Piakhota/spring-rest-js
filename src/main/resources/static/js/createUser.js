async function createUser() {
    $('#addUser').click(async () =>  {
        let addUserForm = $('#addForm')
        let name = addUserForm.find('#nameCreate').val().trim();
        let lastName = addUserForm.find('#lastNameCreate').val().trim();
        let age = addUserForm.find('#ageCreate').val().trim();
        let username = addUserForm.find('#usernameCreate').val().trim();
        let password = addUserForm.find('#passwordCreate').val().trim();
        let checkedRoles = () => {
            let array = []
            let options = document.querySelector('#rolesCreate').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    array.push(roleList[i])
                }
            }
            return array;
        }
        let data = {
            username: username,
            password: password,
            name: name,
            lastName: lastName,
            age: age,
            roles: checkedRoles()
        }

        await userFetch.addNewUser(data);

        await getUsers();
        addUserForm.find('#nameCreate').val('');
        addUserForm.find('#lastNameCreate').val('');
        addUserForm.find('#ageCreate').val('');
        addUserForm.find('#usernameCreate').val('');
        addUserForm.find('#passwordCreate').val('');
        addUserForm.find(checkedRoles()).val('');
        $('.nav-tabs a[href="#adminTable"]').tab('show');
    });
}