import React, { Component } from 'react'
import Input from '../components/InputComponent'
import ButtonWithProgress from '../components/ButtonWithProgressComponent'
import { withTranslation } from 'react-i18next'
import { loginCalls } from '../api/apiCalls'
import axios from 'axios'

class UserLoginPage extends Component {

    state = {
        username: null,
        password: null,
        error: null,
        isApiCallWaiting: false
    }

    // component ilk oluştuğunda çalışan life cycle
    componentDidMount() {
        axios.interceptors.request.use((request) => {
            this.setState({ isApiCallWaiting: true })
            return request;
        })

        axios.interceptors.response.use((response) => {
            this.setState({ isApiCallWaiting: false })
            return response;
        }, (error) => {
            this.setState({ isApiCallWaiting: false })
            throw error;
        })
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
        const { t } = this.props
        const { username, password, error, isApiCallWaiting } = this.state
        const buttonEnabled = username && password

        return (
            <div className="container">

                {
                    this.state.error &&
                    <div className="alert alert-danger">
                        {error}
                    </div>
                }

                <form>
                    <h1 className="text-center">{t('login')}</h1>
                    <Input name="username" label={t('username')} onChange={this.onChangeInput} />
                    <Input name="password" label={t('password')} onChange={this.onChangeInput} type="password" />

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

export default withTranslation()(UserLoginPage)
