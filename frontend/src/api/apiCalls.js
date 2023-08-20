import axios  from 'axios'

export const signup = (body) => {
    return axios.post('/api/v1.0/users', body, { headers : { 'Accept-Language': 'en-US'}});
}