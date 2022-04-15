import authHeader from './auth-header';
const API_URL = 'api/test/';
class UserService {
    getPublicContent() {
        return fetch(API_URL + "all", { headers: authHeader() });
    }
    getUserBoard() {
        return fetch(API_URL + 'user', { headers: authHeader() });
    }
    getModeratorBoard() {
        return fetch(API_URL + 'mod', { headers: authHeader() });
    }
    getAdminBoard() {
        return fetch(API_URL + 'admin', { headers: authHeader() });
    }
}
export default new UserService();