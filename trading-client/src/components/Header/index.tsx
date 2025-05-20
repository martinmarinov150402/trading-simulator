import { Button } from "../Button";
import { Logo } from "../Logo";

import styles from './styles.module.css'

export function Header() {

    return (
        <div className={styles.root}>
            <Logo isLight={true}/>
            <Button className={styles.button}>Get started</Button>
        </div>
    )
}