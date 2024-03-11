import PropTypes from "prop-types";
import { getUserDetail } from "./api";
import { Alert } from "@/utils/components/Alert";
import { Spinner } from "@/utils/components/Spinner";
import { useRouteParamApiRequest } from "@/utils/hooks/useRouteParamApiRequest";
import { ProfileCard } from "./components/ProfileCard";

export function User() {
  const {
    isProcessing,
    data: user,
    error: errorMessage,
  } = useRouteParamApiRequest("id", getUserDetail);

  return (
    <>
      {isProcessing && (
        <Alert styleType="secondary" center>
          <Spinner />
        </Alert>
      )}

      {user && <ProfileCard user={user}/>}
      {errorMessage && <Alert styleType="danger">{errorMessage}</Alert>}
    </>
  );
}

User.propTypes = {
  id: PropTypes.string,
};
