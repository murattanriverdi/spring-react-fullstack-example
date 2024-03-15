import { useEffect, useState } from "react";
import { Input } from "@/utils/components/Input";
import { useTranslation } from "react-i18next";
import { Alert } from "@/utils/components/Alert";
import { Button } from "@/utils/components/Button";
import { login } from "./api";

export function Login() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [isProcessing, setProcessing] = useState(false);
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const { t } = useTranslation();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setProcessing(true);
    try {
      await login({
        email,password
      })
      /*const response = await signUp({
        username,
        email,
        password,
      });
      setSuccessMessage(response.data.message);*/
    } catch (axiosError) {
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.message);
        }
      } else {
        setGeneralError(t("genericError"));
      }
    } finally {
      setProcessing(false);
    }
  };

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-md-8 offset-md-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1>{t("login")}</h1>
          </div>
          <div className="card-body">
            <Input
              id="email"
              label={t("email")}
              error={errors.email}
              onChange={(event) => setEmail(event.target.value)}
            />

            <Input
              id="password"
              label={t("password")}
              error={errors.password}
              type="password"
              onChange={(event) => setPassword(event.target.value)}
            />

            {generalError && <Alert styleType="danger">{generalError}</Alert>}
            <div className="text-center">
              <Button
                disabled={!email || !password}
                isProcessing={isProcessing}
              >
                {t("login")}
              </Button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
