import PropTypes from 'prop-types'

export function Alert(props) {
  const { children, styleType, center } = props;
  return (
    <div className={`alert alert-${styleType || "success"} ${center ? 'text-center' : ''}`} role="alert">
      {children}
    </div>
  );

}

Alert.propTypes = {
    children : PropTypes.any,
    styleType : PropTypes.string,
    center : PropTypes.bool
    
}
