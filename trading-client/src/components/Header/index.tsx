import { Link, useNavigate } from "react-router";
import { Button } from "../Button";
import { Logo } from "../Logo";

import styles from './styles.module.css'
import { authService } from "../../services/auth";
import { Balance } from "../Balance";
import { httpService } from "../../services/http-service";

export function Header() {

    const navigate = useNavigate();

    return (
        <div className={styles.root}>
            <Link to="/"><Logo isLight={false}/></Link>
            {authService.currentUser && (<Balance/>)}
            {authService.currentUser && (<Button onClick={async () => {
                // eslint-disable-next-line no-restricted-globals
                if(confirm("Are you sure that you want to reset your user account? All your holdings will be lost!!!")) {
                    await httpService.post("/api/user/reset", {confirm: true});
                    navigate("/");
                }

            }}>Reset</Button>)}
            <Button onClick={authService.currentUser ? () => {authService.logout(); navigate("/")} : () => navigate("/login")} className={styles.button}>{authService.currentUser ? "Logout" : "Login"}</Button>
        </div>

    )
}