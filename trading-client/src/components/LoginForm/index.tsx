import { useState } from "react";
import { Button } from "../Button";
import { Input } from "../Input";
import styles from './styles.module.css'
import { useAsyncAction } from "../../hooks/use-async-action";
import { useNavigate } from "react-router";
import { authService } from "../../services/auth";

export function LoginForm() {

    const [loginData, setLoginData] = useState<{[key: string]: string}>({
        username: "",
        password: "",
    })

    const navigate = useNavigate();

    const inputs = [
        {type: "text", id: "username", placeholder: "Username"},
        {type: "password", id: "password", placeholder: "Password"},
    ]

    const loginAction = useAsyncAction(async () => {
        await authService.login({username: loginData.username, password: loginData.password});
        navigate("/dashboard");
    })

    return (
        <form className={styles.root} onSubmit={(e) => {
            e.preventDefault();
            loginAction.trigger();

        }}>
            <h1>Login</h1>
            {inputs.map(inputProps => (<Input value={loginData[inputProps.id]} onChange={(e) => setLoginData(oldData => ({
                ...oldData,
                [inputProps.id]: e.target.value
            }))} key={inputProps.id} {...inputProps}/>))}

            <Button type="submit">Login</Button>

            {!!loginAction.error && (<h2>Invalid credentials!</h2>)}
        </form>
    );
}