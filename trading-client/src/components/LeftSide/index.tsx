import leftSide from '../../assets/leftSide.png'
import homeLeftSide from '../../assets/leftSideHome.png'

import styles from './styles.module.css'

export interface LeftSideProps {
    isHome?: boolean;
}
export function LeftSide({isHome} : LeftSideProps) {
    return (
        <img className={styles.root} src={isHome ? homeLeftSide : leftSide} alt="Trading person"/>
    );
}