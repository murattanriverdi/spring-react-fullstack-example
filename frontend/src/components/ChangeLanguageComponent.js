import React from 'react';
import { withTranslation } from 'react-i18next';
import { changeAxiosLanguage } from "../api/apiCalls";



const ChangeLanguageComponent = (props) => {

    const onChangeLanguage = language  => {
        const { i18n } = props;
        i18n.changeLanguage(language);
        changeAxiosLanguage(language);
    }

    return (
        <div className='container'>
        <img src="https://flagsapi.com/TR/flat/24.png" className="pe-auto" role="button" alt="Turkish Flag" onClick={() => onChangeLanguage('tr')}/>
        <img src="https://flagsapi.com/US/flat/24.png" className="pe-auto" role="button" alt="Usa Flag" onClick={() => onChangeLanguage('en')}/>
        </div>
    );
};

export default withTranslation()(ChangeLanguageComponent);