import styles from './styles.module.css'
import { LeftSide } from '../../components/LeftSide';
import { Button } from '../../components/Button';
import { useNavigate } from 'react-router';

export function HomePage() {
    const navigate = useNavigate();
    return (
        <div className={styles.root}>
            <LeftSide isHome/>
            <div>
                <h1 className={styles.title}>
                    Risk free trade simulator
                </h1>
                <h2 className={styles.additional}>
                    Take your journey in trading with my simulator.
                </h2>
                <h2 className={styles.additional}>
                    Create your account and recieve 10000$ demo money.
                </h2>
                <h2 className={styles.additional}>
                    Reset your balance any time.
                </h2>

                <Button className={styles.loginButton} onClick={() => {
                    navigate("/register")
                }}>Get started</Button>
            </div>
        </div>
    );
}