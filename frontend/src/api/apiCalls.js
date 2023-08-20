import axios  from 'axios'

axios.defaults.headers['accept-language'] = 'tr';

export const signup = (body) => {
    return axios.post('/api/v1.0/users', body);
}

export const changeLanguage = language => {
    axios.defaults.headers['accept-language'] = language;
}