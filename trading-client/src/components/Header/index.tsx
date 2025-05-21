import { Link, useNavigate } from "react-router";
import { Button } from "../Button";
import { Logo } from "../Logo";

import styles from './styles.module.css'

export function Header() {

    const navigate = useNavigate();

    return (
        <div className={styles.root}>
            <Link to="/"><Logo isLight={false}/></Link>
            <Button onClick={() => navigate("/login")} className={styles.button}>Login</Button>
        </div>
    )
}