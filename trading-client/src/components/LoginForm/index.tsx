import { Button } from "../Button";
import { Input } from "../Input";
import styles from './styles.module.css'

export function LoginForm() {

    const inputs = [
        {type: "text", id: "username", placeholder: "Username"},
        {type: "password", id: "password", placeholder: "Password"},
    ]
    return (
        <form className={styles.root}>
            <h1>Login</h1>
            {inputs.map(inputProps => (<Input key={inputProps.id} {...inputProps}/>))}

            <Button type="submit">Login</Button>
        </form>
    );
}