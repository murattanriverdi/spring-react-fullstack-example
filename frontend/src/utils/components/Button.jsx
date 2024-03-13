import { Spinner } from "./Spinner";
import PropTypes from "prop-types";

export function Button({ isProcessing, disabled, children }) {
  return (
    <button className="btn btn-primary" disabled={isProcessing || disabled}>
      {isProcessing && <Spinner sm={true} />}
      {children}
    </button>
  );
}

Button.propTypes = {
  isProcessing: PropTypes.bool,
  disabled: PropTypes.bool,
  children: PropTypes.any,
};
