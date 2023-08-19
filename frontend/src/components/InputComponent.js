import React from "react";

const InputComponent = (props) => {
    const { label, error, name, onChange, type } = props;
    const className = error ? "form-control is-invalid" : "form-control"
    return (
        <div className="mb-3">
            <label for={name} className="form-label">{label}</label>
            <input name={name} id={name} className={className} onChange={onChange}  type={type}/>
            <div className="invalid-feedback">
                {error}
            </div>
        </div>
    )
}

export default InputComponent; 