import dark from '../../assets/dark2.png'
import light from '../../assets/light2.png'
import './styles.module.css'

interface LogoProps {
    isLight: boolean;
}
export function Logo({isLight}: LogoProps) {
    return (
        <img src={isLight ? light : dark} alt='No Logo'/>
    )
}