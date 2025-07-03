import { Button } from '@/components/ui/button'
import { useAuthStore } from '@/stores/authStore'
import { useUserStore } from '@/stores/userStore'
import { useNavigate } from 'react-router'
import { toast } from 'react-toastify'

const PatientNavbar = () => {

    const clearToken = useAuthStore((state) => state.clearToken)
    const clearUserDetails = useUserStore((state) => state.clearUserDetails)

    const navigate = useNavigate()

    const handleLogout = () => {
        try {
            clearToken()
            clearUserDetails()

            toast.success("Logged out.")
            navigate("/")
        } catch (error) {
            toast.error("Something went wrong")
        }
    }

    return (
        <div>
            <header className="fixed top-0 left-0 w-full z-50 bg-white/80 dark:bg-gray-900/80 backdrop-blur border-b border-gray-200 dark:border-gray-800">
                <div className="max-w-7xl mx-auto px-4 h-16 flex justify-between items-center">
                    <span className="font-bold text-2xl text-teal-600 dark:text-teal-400">Cura<span className="text-gray-900 dark:text-white">Sphere</span></span>
                    <div className="space-x-4">
                        <Button className="bg-teal-400 hover:bg-teal-600" onClick={handleLogout}>Logout</Button>
                    </div>
                </div>
            </header>
        </div>
    )
}

export default PatientNavbar
