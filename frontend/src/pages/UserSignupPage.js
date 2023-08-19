import React from "react";
import { signup } from "../api/apiCalls";
import Input from "../components/InputComponent";
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
        const { name, value } = event.target
        const errors = {...this.state.errors}
        errors[name] = undefined
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


    render() {
        const { isApiCallWaiting, errors } = this.state
        const { username, name, password, passwordRepeat } = errors
        return (
            <div className="container">
                <form>
                    <h1 className="text-center">Kayıt Ol</h1>
                    <Input name="username" label="Kullanıcı Adı" error ={username} onChange={this.onChangeInput} />
                    <Input name="name" label="Ad Soyad" error ={name} onChange={this.onChangeInput} />
                    <Input name="password" label="Parola" error={password} onChange={this.onChangeInput} type="password" />
                    <Input name="passwordRepeat" label="Parola(Tekrar)" error={passwordRepeat} onChange={this.onChangeInput} type="password" />

                    <div className="text-center">
                        <button className="btn btn-primary text-white" onClick={this.onClickSignupButton} disabled={isApiCallWaiting} >
                            {isApiCallWaiting && <span className="spinner-border spinner-border-sm"></span>}
                            Kayıt Ol</button>
                    </div>
                </form >
            </div>
        );
    }
}

export default UserSignupPage;