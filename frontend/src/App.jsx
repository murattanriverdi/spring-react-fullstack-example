import { Link, Outlet } from "react-router-dom";
import logo from "./assets/app.png";
import { LanguageSelector } from "./utils/components/languageSelector"
import { useTranslation } from "react-i18next";

function App() {
  const { t } = useTranslation();
  return (
    <>
      <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            <img src={logo} width={50} />
            Navbar
          </Link>
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/signup">{t('signUp')}</Link>
            </li>
          </ul>
        </div>
      </nav>
      <div className="container mt-3">
      <Outlet />
      <LanguageSelector />
      </div>
      
    </>
  );
}

export default App;