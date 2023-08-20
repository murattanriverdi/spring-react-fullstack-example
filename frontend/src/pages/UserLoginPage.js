import React, { Component } from 'react'
import Input from '../components/InputComponent'
import { withTranslation } from 'react-i18next';

class UserLoginPage extends Component {

    state = {
        username: null,
        password: null
    }

    onChangeInput = event => {
        const { name, value } = event.target;
        this.setState({
            [name]: value
        })

    }



    render() {
        const { t } = this.props
        return (
            <div className="container">
                <form>
                    <h1 className="text-center">{t('login')}</h1>
                    <Input name="username" label={t('username')} onChange={this.onChangeInput} />
                    <Input name="password" label={t('password')} onChange={this.onChangeInput} type="password" />
                    <div className='text-center'>
                        <button className='btn btn-primary text-white'>{t('login')}</button>
                    </div>
                </form >
            </div>
        )
    }
}

export default withTranslation()(UserLoginPage)
