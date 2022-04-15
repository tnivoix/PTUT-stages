export default class User {
    constructor(username, email, password, name, firstName, numTel, roles) {
        // vérifier que la réponse du back envoi bien dans le bon ordre
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.firstName = firstName;
        this.numTel = numTel;
        this.roles = roles;
    }
}