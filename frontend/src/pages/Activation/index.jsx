import { activateUser } from "./api";
import { Alert } from "@/utils/components/Alert";
import { Spinner } from "@/utils/components/Spinner";
import { useRouteParamApiRequest } from "@/utils/hooks/useRouteParamApiRequest";

export function Activation() {
  const {
    isProcessing,
    data,
    error,
  } = useRouteParamApiRequest("token", activateUser);

  return (
    <>
      {isProcessing && (
        <Alert styleType="secondary" center>
          <Spinner />
        </Alert>
      )}
      {data?.message && <Alert>{data.message}</Alert>}
      {error && <Alert styleType="danger">{error}</Alert>}
    </>
  );
}
