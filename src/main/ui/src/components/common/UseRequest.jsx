import {useState,useEffect} from 'react';

export default function useRequest(endpoint) {
    const [data, setData] = useState()
    const [isLoading, setIsLoading] = useState(true)
    const [error, setError] = useState()

    function sendRequest() {
        setIsLoading(true)
        const requestOptions = {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify()
                }
        fetch(endpoint, requestOptions)
            .then(response => {
                return response.json()
            })
            .then(data => {
                setData(data)
                setIsLoading(false)
            })
            .catch( error => {
                setError(error)
                setIsLoading(false)
            })
    }

    useEffect(() => {
        sendRequest()
    },[])

    return [data, isLoading, error]
}