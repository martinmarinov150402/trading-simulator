import { useCallback, useEffect, useRef, useState } from "react";

export function useAsyncAction<Args extends any[], Result> (
    async_action: (...args: Args) => Promise<Result>
) {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<unknown>();
    const [data, setData] = useState<Result>();

    const actionRef = useRef(async_action);
    actionRef.current = async_action;

    const idRef = useRef(0);

    const perform = useCallback( async (...args: Args) => {
        setLoading(true);
        idRef.current += 1;
        const requestId = idRef.current;
        
        try {
            const result = await actionRef.current(...args);

            if(requestId === idRef.current) {
                setData(result);
                setError(undefined);
                setLoading(false);
            }
            return result;


        }
        catch (err) {
            if(requestId === idRef.current) {
                setData(undefined);
                setError(err);
                setLoading(false);
            }
        } 
    }, []);

    const trigger = useCallback(
        (...args: Args) => {
            perform(...args).catch(() => {

            })
        },
        [perform]
    );

    useEffect(() => {
        return () => {
            idRef.current += 1;
        }
    }, []);

    return {loading, error, data, perform, trigger};
}