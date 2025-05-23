import { useEffect, useState } from "react";

export function useAsync<T>(async_function: () => Promise<T>, setState: (element: T) => void) {

    const [loading, setLoading] = useState(false);
    useEffect(() => {
        let isMounted = true;
        (async() => {
            setLoading(true);
            const result = await async_function();
            setLoading(false);
            if(isMounted) {
               setState(result); 
            }
            
        })();

        return () => {
            isMounted = false;  
        }
    }, [])

    return loading;
}