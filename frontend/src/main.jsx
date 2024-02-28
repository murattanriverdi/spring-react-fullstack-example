import ReactDOM from "react-dom/client";
//import App from './App.jsx'
import "./styles.scss";
import "./locales";
import router from "./router";
import { RouterProvider } from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
