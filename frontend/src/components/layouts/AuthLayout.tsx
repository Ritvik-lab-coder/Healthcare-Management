import { Outlet } from "react-router"

const AuthLayout = () => {
    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-950">
            <Outlet />
        </div>
    )
}

export default AuthLayout