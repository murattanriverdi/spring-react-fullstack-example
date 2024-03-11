import { createBrowserRouter } from "react-router-dom";
import { Home } from "@/pages/Home";
import { SignUp } from "@/pages/SignUp";
import  App  from "@/App";
import { Activation } from "@/pages/Activation";
import { User } from "@/pages/User";

export default  createBrowserRouter([
    {
      path : "/",
      Component : App,
      children : 
      [
        {
          index : true,
          Component: Home,
        },
        {
          path: "/signup",
          Component: SignUp,
        },
        {
          path: "/activation/:token",
          Component: Activation,
        },
        {
          path: "/user/:id",
          Component: User,
        }
      ]
    }
  ]);
