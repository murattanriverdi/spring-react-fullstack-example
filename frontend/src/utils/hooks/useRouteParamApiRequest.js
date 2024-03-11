import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";


export function useRouteParamApiRequest(param, httpFunction){
    
    const params = useParams();
    const pathParam = params[param];

    const [isProcessing, setProcessing] = useState();
    const [data, setData] = useState();
    const [error, setError] = useState();
  
    useEffect(() => {
      async function sendApiRequest() {
        setProcessing(true);
        try {
          const response = await httpFunction(pathParam);
          setData(response.data);
        } catch (axiosError) {
          setError(axiosError.response.data.message);
        } finally {
          setProcessing(false);
        }
      }
  
      sendApiRequest();
    }, [pathParam, httpFunction]);



    return { isProcessing, data, error}
}