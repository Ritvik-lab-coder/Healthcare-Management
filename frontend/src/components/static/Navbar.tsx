import { useAuthStore } from '@/stores/authStore'
import { Button } from '../ui/button'
import { Link } from 'react-router'
import { useUserStore } from '@/stores/userStore'

const Navbar = () => {

    const token = useAuthStore((state) => state.token)
    const user = useUserStore((state) => state.user)

    let role;
    if (user?.role === "ROLE_PATIENT") {
        role = "patient"
    } else if (user?.role === "ROLE_DOCTOR") {
        role = "doctor"
    } else {
        role = "admin"
    }

    return (
        <div>
            <header className="fixed top-0 left-0 w-full z-50 bg-white/80 dark:bg-gray-900/80 backdrop-blur border-b border-gray-200 dark:border-gray-800">
                <div className="max-w-7xl mx-auto px-4 h-16 flex justify-between items-center">
                    <span className="font-bold text-2xl text-teal-600 dark:text-teal-400">Cura<span className="text-gray-900 dark:text-white">Sphere</span></span>
                    <div className="space-x-4">
                        {token ? (
                            <Link to={`${role}/dashboard`}>
                                <Button className="bg-teal-400 hover:bg-teal-600">Dashboard</Button>
                            </Link>
                        ) : (
                            <>
                                <Link to="/auth/signup">
                                    <Button variant="secondary" className="border-teal-600 border-2 text-teal-400 hover:bg-teal-600 hover:text-white">
                                        Sign Up
                                    </Button>
                                </Link>
                                <Link to="/auth/login">
                                    <Button className="bg-teal-400 hover:bg-teal-600">Login</Button>
                                </Link>
                            </>
                        )}
                    </div>
                </div>
            </header>
        </div>
    )
}

export default Navbar
