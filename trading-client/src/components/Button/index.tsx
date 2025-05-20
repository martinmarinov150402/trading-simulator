import { ComponentProps } from "react";
import clsx from 'clsx';

import styles from './styles.module.css'

export function Button({
    className,
  ...buttonProps
}: ComponentProps<"button">) {

    return (
    <button
      className={clsx(styles.root, className)}
      {...buttonProps}
    />
  );
    
}