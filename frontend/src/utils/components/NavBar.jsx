import { Link } from "react-router-dom";
import logo from "@/assets/app.png";
import { useTranslation } from "react-i18next";

export function NavBar() {
  const { t } = useTranslation();
  return (
    <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img src={logo} width={50} />
          MyApp
        </Link>
        <ul className="navbar-nav">
          <li className="nav-item">
            <Link className="nav-link" to="/signup">
              {t("signUp")}
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/login">
              {t("login")}
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}
