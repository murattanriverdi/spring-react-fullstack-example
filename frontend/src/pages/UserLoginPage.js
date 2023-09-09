import React, { Component } from 'react'
import Input from '../components/InputComponent'
import ButtonWithProgress from '../components/ButtonWithProgressComponent'
import { withTranslation } from 'react-i18next'
import { loginCalls } from '../api/apiCalls'
import { withApiProgress } from '../util/ApiProgress'

class UserLoginPage extends Component {

    state = {
        username: null,
        password: null,
        error: null
    }

    onChangeInput = event => {
        const { name, value } = event.target;
        this.setState({
            [name]: value,
            error: null
        })

    }

    onClickLoginButton = async event => {
        event.preventDefault();
        const { username, password } = this.state;
        const credential = {
            username, password
        }
        this.setState({ error: null })
        try {
            await loginCalls(credential)
        } catch (apiError) {
            this.setState({
                error: apiError.response.data.message
            })
        }
    }

    render() {
        const { t, isApiCallWaiting } = this.props
        const { username, password, error } = this.state
        const buttonEnabled = username && password

        return (
            <div className="container">
                <form>
                    <h1 className="text-center">{t('login')}</h1>
                    <Input name="username" label={t('username')} onChange={this.onChangeInput} />
                    <Input name="password" label={t('password')} onChange={this.onChangeInput} type="password" />
                    {
                        this.state.error &&
                        <div className="alert alert-danger my-3">
                            {error}
                        </div>
                    }
                    <div className='text-center'>
                        <ButtonWithProgress
                            disabled={!buttonEnabled || isApiCallWaiting}
                            onClick={this.onClickLoginButton}
                            isApiCallWaiting={isApiCallWaiting}
                            text={t('login')} />
                    </div>
                </form >
            </div>
        )
    }
}

const LoginPageWithTranslation = withTranslation()(UserLoginPage);

export default withApiProgress(LoginPageWithTranslation, '/api/v1.0/auth')
