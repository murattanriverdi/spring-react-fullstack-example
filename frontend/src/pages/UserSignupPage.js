import React, { Component } from "react";
import { signupCalls } from "../api/apiCalls";
import Input from "../components/InputComponent";
import ButtonWithProgress from '../components/ButtonWithProgressComponent'
import { withTranslation } from 'react-i18next';
import { withApiProgress } from "../util/ApiProgress";

class UserSignupPage extends Component {

    state = {
        username: null,
        name: null,
        password: null,
        passwordRepeat: null,
        errors: {}
    }

    onChangeInput = event => {
        const { t } = this.props;
        const { name, value } = event.target
        const errors = { ...this.state.errors }
        errors[name] = undefined

        if (name === 'password' || name === 'passwordRepeat') {
            if (name === 'password' && value !== this.state.passwordRepeat) {
                errors.passwordRepeat = t('passwordMismatch')
            } else if (name === 'passwordRepeat' && value !== this.state.password) {
                errors.passwordRepeat = t('passwordMismatch')
            } else {
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
        try {
            await signupCalls(body);
        } catch (error) {
            if (error.response.data.validationErrors) {
                this.setState({ errors: error.response.data.validationErrors })
            }
        }
    }

    render() {
        const { errors } = this.state
        const { username, name, password, passwordRepeat } = errors
        const { t, isApiCallWaiting } = this.props;

        return (
            <div className="container">
                <form>
                    <h1 className="text-center">{t('signUp')}</h1>
                    <Input name="username" label={t('username')} error={username} onChange={this.onChangeInput} />
                    <Input name="name" label={t('name')} error={name} onChange={this.onChangeInput} />
                    <Input name="password" label={t('password')} error={password} onChange={this.onChangeInput} type="password" />
                    <Input name="passwordRepeat" label={t('passwordRepeat')} error={passwordRepeat} onChange={this.onChangeInput} type="password" />
                    <div className="text-center">
                        <ButtonWithProgress
                            disabled={isApiCallWaiting || passwordRepeat !== undefined}
                            onClick={this.onClickSignupButton}
                            isApiCallWaiting={isApiCallWaiting}
                            text={t('signUp')} />
                    </div>
                </form >
            </div>
        );
    }
}
const UserSignupPageWithApiProgress = withApiProgress(UserSignupPage, '/api/v1.0/signup')

const UserSignupPageWithTranslation = withTranslation()(UserSignupPageWithApiProgress);

export default UserSignupPageWithTranslation;