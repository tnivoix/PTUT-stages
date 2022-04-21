import authHeader from './auth-header';
const API_URL = '/api/test/';
class UserService {
    getPublicContent() {
        return fetch(API_URL + "all", { headers: authHeader() });
    }
    getStudentBoard() {
        return fetch(API_URL + 'etudiant', { headers: authHeader() });
    }
    getTutorBoard() {
        return fetch(API_URL + 'tuteur', { headers: authHeader() });
    }
    getRespBoard() {
        return fetch(API_URL + 'responsableDesStages', { headers: authHeader() });
    }
}
export default new UserService();