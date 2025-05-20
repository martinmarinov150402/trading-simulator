import { ComponentProps } from "react";
import styles from './styles.module.css';
import clsx from "clsx";

export function Input({className, ...props}: ComponentProps<"input">) {
    return (
        <input className={clsx(styles.root, className)} {...props}></input>
    )
}