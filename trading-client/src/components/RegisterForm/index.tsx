import { useState } from "react";
import { Button } from "../Button";
import { Input } from "../Input";
import styles from './styles.module.css'
import { useAsyncAction } from "../../hooks/use-async-action";
import { httpService } from "../../services/http-service";

export function RegisterForm() {

    const [registerData, setRegisterData] = useState<{[key: string]: string}>({
        username: "",
        password: "",
        email: "",
    })

    const registerAction = useAsyncAction(async () => {
        return httpService.post("/api/auth/register", registerData); 
    })

    const [submitted, setSubmitted] = useState(false);


    const inputs = [
        {type: "text", id: "username", placeholder: "Username"},
        {type: "password", id: "password", placeholder: "Password"},
        {type: "email", id: "email", placeholder: "Email"},
    ]
    return (
        <form className={styles.root} onSubmit={(e) => {
            e.preventDefault();
            setSubmitted(true);
            registerAction.trigger();
        }}>
            <h1>Register</h1>
            {inputs.map(inputProps => (<Input value={registerData[inputProps.id]} onChange = {(e) => {setRegisterData(oldData => ({
                ...oldData,
                [inputProps.id]: e.target.value,
            }))}} key={inputProps.id} {...inputProps}/>))}

            {registerAction.loading && "Loading..."}

            {submitted && !registerAction.loading && !!registerAction.error && (<h2>{`${registerAction.error}`}</h2>)}

            <Button type="submit">Register</Button>
        </form>
    );
}