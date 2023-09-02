import axios  from 'axios'

//axios.defaults.headers['accept-language'] = 'tr';

export const signupCalls = (body) => {
    return axios.post('/api/v1.0/users', body);
}

export const loginCalls = credential => {
    return axios.post('/api/v1.0/auth',{},{auth : credential})
}

export const changeAxiosLanguage = language => {
    axios.defaults.headers['accept-language'] = language;
}