import { Button } from "../Button";
import { Input } from "../Input";
import styles from './styles.module.css'

export function RegisterForm() {

    const inputs = [
        {type: "text", id: "username", placeholder: "Username"},
        {type: "password", id: "password", placeholder: "Password"},
        {type: "email", id: "email", placeholder: "Email"},
    ]
    return (
        <form className={styles.root}>
            {inputs.map(inputProps => (<Input key={inputProps.id} {...inputProps}/>))}

            <Button type="submit">Register</Button>
        </form>
    );
}