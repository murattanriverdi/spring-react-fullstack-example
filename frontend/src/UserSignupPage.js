import { render } from "@testing-library/react";
import React from "react";

class UserSignupPage extends React.Component {

    state = {
        username: null,
        name: null,
        password: null,
        passwordRepeat: null
    }

    onChangeInput = event => {
        const { name, value } = event.target
        this.setState({
            [name]: value
        })

    }


    render() {
        return (
            <form>
                <h1>Kayıt Ol</h1>
                <div>
                    <label>Kullanıcı Adı</label>
                    <input name="username" onChange={this.onChangeInput} />
                </div>

                <div>
                    <label>Ad Soyad</label>
                    <input name="name" onChange={this.onChangeInput} />
                </div>

                <div>
                    <label>Parola</label>
                    <input name="password" type="password" onChange={this.onChangeInput} />
                </div>

                <div>
                    <label>Parola(Tekrar)</label>
                    <input name="passwordRepeat" type="password" onChange={this.onChangeInput} />
                </div>

                <button>Kayıt Ol</button>
            </form>
        );
    }

}

export default UserSignupPage;