import React from 'react';

const ButtonWithProgressComponent = (props) => {
    const { onClick, isApiCallWaiting, disabled, text } = props
    return (
        <div>
            <button className="btn btn-primary text-white" onClick={onClick} disabled={disabled} >
                {isApiCallWaiting && <span className="spinner-border spinner-border-sm"></span>}
                {text}
            </button>
        </div>
    );
};

export default ButtonWithProgressComponent;