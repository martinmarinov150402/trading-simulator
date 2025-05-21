import { Navigate, Outlet, useLocation } from "react-router";
import { User } from "../services/auth";

interface PublicOutletProps {
    user: User | undefined;
}

export function PublicOutlet({ user }: PublicOutletProps) {
const { pathname: from } = useLocation();
return !user ? <Outlet /> : <Navigate to="/" replace state={{ from }} />;
}