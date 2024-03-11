import {  Outlet } from "react-router-dom";
import { LanguageSelector } from "./utils/components/LanguageSelector";
import { NavBar } from "./utils/components/navbar";

function App() {
  return (
    <>
    <NavBar />
      <div className="container mt-3">
        <Outlet />
        <LanguageSelector />
      </div>
    </>
  );
}

export default App;
