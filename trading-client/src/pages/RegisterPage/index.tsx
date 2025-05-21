import { LeftSide } from "../../components/LeftSide";
import { RegisterForm } from "../../components/RegisterForm";

import styles from './styles.module.css'

export function RegisterPage() {
    return (
        <div className={styles.registerPage}>
            <LeftSide/>
            <RegisterForm/>
        </div>
    );
}