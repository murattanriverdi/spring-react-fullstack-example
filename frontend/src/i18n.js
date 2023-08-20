import i18n from 'i18next';
import { initReactI18next }  from 'react-i18next';


i18n.use(initReactI18next).init({
    resources : {
        en : {
            translations : {
                'signUp' : 'Sign Up',
                'username' : 'Username',
                'name' : 'Name Surname',
                'password' : 'Password',
                'passwordRepeat' : 'Password Repeat',
                'passwordMismatch' : 'Password Mismatch',
                'login' : 'Login',
                'turkishFlag' : 'Turkish Flag',
                'usaFlag' : 'USA Flag'
            }
        },
        tr : {
            translations : {
                'signUp' : 'Kayıt Ol',
                'username' : 'Kullanıcı Adı',
                'name' : 'Ad Soyad',
                'password' : 'Parola',
                'passwordRepeat' : 'Parola (Tekrar)',
                'passwordMismatch' : 'Parolalar uyuşmuyor',
                'login' : 'Giriş Yap',
                'turkishFlag' : 'Türk Bayrağı',
                'usaFlag' : 'Amerikan Bayrağı'
            }
        }
    },
    fallbackLng : 'tr',
    ns : ['translations'],
    defaultNS : 'translations',
    keySeperator: false,
    interpolation : {
        escapeValue : false,
        formatSeperator : ','
    },
    react : {
        wait : true
    }
});

export default i18n;