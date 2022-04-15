const API_URL = 'api/auth/';
class AuthService {
    login(user) {
        const options = {
            method: "POST",
            body: JSON.stringify({ username: user.name, password: user.password }),
            headers: {
                "Content-Type": "application/json",
            },
        };
        return fetch(API_URL + "signin", options)
            .then((response) => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch((error) => {
                console.log(error)
                alert(error)
            });
    }
    logout() {
        localStorage.removeItem('user');
    }
    register(user) {
        const options = {
            method: "POST",
            body: JSON.stringify({
                username: user.name,
                password: user.password,
                email: user.email,
                name: user.name,
                firstName: user.firstName,
                numTel: user.numTel,
                roles: user.roles
            }),
            headers: {
                "Content-Type": "application/json",
            },
        };
        return fetch(API_URL + "signup", options)
            .catch((error) => {
                console.log(error)
                alert(error)
            });
    }
}
export default new AuthService();