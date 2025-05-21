import { LeftSide } from "../../components/LeftSide";
import { LoginForm } from "../../components/LoginForm";

import styles from './styles.module.css';

export function LoginPage() {
    return (
        <div className={styles.root}>
            <LeftSide/>
            <LoginForm/>
        </div>
    );
}