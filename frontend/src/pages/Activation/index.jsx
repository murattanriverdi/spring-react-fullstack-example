import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { activateUser } from "./api";
import { Alert } from "@/utils/components/Alert";
import { Spinner } from "@/utils/components/Spinner";

export function Activation() {
  const { token } = useParams();
  const [isProcessing, setProcessing] = useState();
  const [successMessage, setSuccessMessage] = useState();
  const [errorMessage, setErrorMessage] = useState();

  useEffect(() => {
    async function activate() {
      setProcessing(true);
      try {
        const response = await activateUser(token);
        setSuccessMessage(response.data.message);
      } catch (axiosError) {       
        setErrorMessage(axiosError.response.data.message);
      } finally {
        setProcessing(false);
      }
    }
    activate();
  }, [token]);

  return (
    <>
      {isProcessing && (
        <Alert styleType="secondary" center>
          <Spinner />
        </Alert>
      )}

      {successMessage && <Alert>{successMessage}</Alert>}
      {errorMessage && <Alert styleType="danger">{errorMessage}</Alert>}
    </>
  );
}
