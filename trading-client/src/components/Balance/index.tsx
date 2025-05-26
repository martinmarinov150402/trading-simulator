import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { faDollarSign } from "@fortawesome/free-solid-svg-icons";
import { authService } from "../../services/auth";

import styles from './styles.module.css'

export function Balance() {
    return (
        <div className={styles.balance}>
            {authService.currentUser?.balance}

            <FontAwesomeIcon icon={faDollarSign}/>
            
        </div>
    )
}