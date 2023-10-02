export class UserDetailsClass {

    constructor() {
    }

    setFirstName(firstName) {
        this.firstName = firstName;
    }

    setLastName(lastName) {
        this.lastName = lastName;
    }

    setUserName(username) {
        this.username = username;
    }

    setAuthToken(authToken) {
        this.authToken = authToken;
    }

    setUserType(userType) {
        this.userType = userType;
    }

    getUserLoginObj() {
        return {
            firstName: this.firstName,
            lastName: this.lastName,
            username: this.username,
            authToken: this.authToken,
            userType: this.userType
        }
    }

}