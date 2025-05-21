import { Navigate, Outlet, useLocation } from "react-router";
import { User } from "../services/auth";

interface PrivateOutletProps {
    user: User | undefined;
}

export function PrivateOutlet({ user }: PrivateOutletProps) {
    const { pathname: from } = useLocation();
    return user ? <Outlet /> : <Navigate to="/login" replace state={{ from }} />;
}
