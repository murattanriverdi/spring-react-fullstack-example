import React from "react";
import UserSignupPage from "../pages/UserSignupPage";
import UserLoginPage from "../pages/UserLoginPage";
import ChangeLanguageComponent from "../components/ChangeLanguageComponent";

function App() {
  return (
    <div className="container">
      <div className="row">
        <div className="col">
          <UserSignupPage />
        </div>
        <div className="col">
          <UserLoginPage />
        </div>
        <ChangeLanguageComponent />
      </div>
    </div>
  );
}

export default App;
