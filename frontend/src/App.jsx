import { Outlet } from "react-router-dom";
import { LanguageSelector } from "./utils/components/LanguageSelector";
import { NavBar } from "./utils/components/navbar";
import { StrictMode } from "react";

function App() {
  return (
    <>
    <StrictMode>
    <NavBar />
      <div className="container mt-3">
        <Outlet />
        <LanguageSelector />
      </div>
      </StrictMode>
    </>
  );
}

export default App;
