import PropTypes from 'prop-types';

export function Spinner(props) {
  const { sm } = props;

  return (
    <span
      className={`spinner-grow ${sm ? "spinner-grow-sm" : ""}`}
      aria-hidden="true"
    ></span>
  );
}

Spinner.propTypes = {
    sm : PropTypes.bool
}
