import { useState } from "react"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { toast } from "react-toastify"
import axios from "axios"
import { Loader2 } from "lucide-react"
import { useNavigate } from "react-router"
import { loginUserEndpoint } from "@/APIs/authEndpoints"
import { useAuthStore } from "@/stores/authStore"
import { useUserStore } from "@/stores/userStore"

const Login = () => {
    const [loading, setLoading] = useState(false)
    const [credentials, setCredentials] = useState({
        email: "",
        password: ""
    })

    const navigate = useNavigate()

    const setToken = useAuthStore((state) => state.setToken)
    const setUserDetails = useUserStore((state) => state.setUserDetails)

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setCredentials((prev) => ({ ...prev, [name]: value }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)

        try {
            const response = await axios.post(loginUserEndpoint, credentials)

            if (response.data?.token) {
                setToken(response.data.token)
                setUserDetails(response.data.userResponseDTO)

                toast.success("Login successful!")

                if (response.data.userResponseDTO.role === "ROLE_PATIENT") {
                    navigate("/patient/dashboard")
                } else if (response.data.userResponseDTO.role === "ROLE_DOCTOR") {
                    navigate("/doctor/dashboard")
                } else {
                    navigate("/admin/dashboard")
                }
            } else {
                toast.error("Invalid email or password.")
            }
        } catch (err) {
            toast.error("Internal Server Error")
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="min-h-screen flex items-center justify-center px-4 py-16 bg-gray-50 dark:bg-gray-950">
            <form
                onSubmit={handleSubmit}
                className="w-full max-w-md bg-white dark:bg-gray-900 border border-gray-200 dark:border-gray-800 p-8 rounded-2xl shadow-md space-y-6"
            >
                <h2 className="text-3xl font-bold text-center text-teal-600 dark:text-teal-400">
                    Welcome Back
                </h2>

                <div className="space-y-4">
                    <div>
                        <Label htmlFor="email">Email</Label>
                        <Input
                            type="email"
                            name="email"
                            id="email"
                            placeholder="john@example.com"
                            value={credentials.email}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div>
                        <Label htmlFor="password">Password</Label>
                        <Input
                            type="password"
                            name="password"
                            id="password"
                            placeholder="••••••••"
                            value={credentials.password}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>

                <Button
                    type="submit"
                    disabled={loading}
                    className="w-full bg-teal-500 hover:bg-teal-600 text-white flex items-center justify-center gap-2"
                >
                    {loading ? (
                        <>
                            <Loader2 className="w-4 h-4 animate-spin" />
                            Please wait...
                        </>
                    ) : (
                        "Login"
                    )}
                </Button>
            </form>
        </div>
    )
}

export default Login
