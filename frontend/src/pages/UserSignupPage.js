import React from "react";
import { signup, changeLanguage } from "../api/apiCalls";
import Input from "../components/InputComponent";
import { withTranslation } from 'react-i18next';

class UserSignupPage extends React.Component {

    state = {
        username: null,
        name: null,
        password: null,
        passwordRepeat: null,
        isApiCallWaiting: false,
        errors: {}
    }

    onChangeInput = event => {
        const { t } = this.props;
        const { name, value } = event.target
        const errors = {...this.state.errors}
        errors[name] = undefined

        if(name === 'password' || name === 'passwordRepeat'){
            if(name === 'password' && value !== this.state.passwordRepeat ){
                errors.passwordRepeat  = t('passwordMismatch')
            }else if (name === 'passwordRepeat' && value !== this.state.password){
                errors.passwordRepeat  = t('passwordMismatch')
            }else{
                errors.passwordRepeat = undefined;
            }
        }
        this.setState({
            [name]: value,
            errors
        })

    }

    onClickSignupButton = async event => {
        event.preventDefault();

        const { username, name, password } = this.state;

        const body = {
            username, name, password
        }
        this.setState({ isApiCallWaiting: true })
        try {
            const response = await signup(body);
            console.log(response);
        } catch (error) {
            if(error.response.data.validationErrors){
                this.setState({ errors: error.response.data.validationErrors })
            }
        }
        this.setState({ isApiCallWaiting: false })
    }

    onChangeLanguage = language  => {
        const { i18n } = this.props;
        i18n.changeLanguage(language);
        changeLanguage(language);
    }


    render() {
        const { isApiCallWaiting, errors } = this.state
        const { username, name, password, passwordRepeat } = errors
        const { t } = this.props;


        return (
            <div className="container">
                <form>
                    <h1 className="text-center">{t('signUp')}</h1>
                    <Input name="username" label={t('username')} error ={username} onChange={this.onChangeInput} />
                    <Input name="name" label={t('name')} error ={name} onChange={this.onChangeInput} />
                    <Input name="password" label={t('password')} error={password} onChange={this.onChangeInput} type="password" />
                    <Input name="passwordRepeat" label={t('passwordRepeat')} error={passwordRepeat} onChange={this.onChangeInput} type="password" />
                    <div className="text-center">
                        <button className="btn btn-primary text-white" onClick={this.onClickSignupButton} disabled={isApiCallWaiting} >
                            {isApiCallWaiting && <span className="spinner-border spinner-border-sm"></span>}
                            {t('signUp')}
                        </button>
                    </div>
                    <div>
                    <img src="https://flagsapi.com/TR/flat/24.png" className="pe-auto" role="button" alt="Turkish Flag" onClick={() => this.onChangeLanguage('tr')}/>
                    <img src="https://flagsapi.com/US/flat/24.png" className="pe-auto" role="button" alt="USA Flag" onClick={() => this.onChangeLanguage('en')}/>
                    </div>
                </form >
            </div>
        );
    }
}

const userSignupPageWithTranslation = withTranslation()(UserSignupPage);

export default userSignupPageWithTranslation;