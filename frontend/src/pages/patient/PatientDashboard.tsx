import { useUserStore } from "@/stores/userStore"
import { useEffect, useState } from "react"

const PatientDashboard = () => {
    const user = useUserStore((state) => state.user)
    const [name, setName] = useState("")

    useEffect(() => {
        if (user) {
            setName(user.name)
        }
    }, [user])

    return (
        <div className="w-full h-full flex flex-col items-center justify-center gap-4">
            <h1 className="text-3xl font-bold text-teal-600 dark:text-teal-400">
                Welcome{name ? `, ${name}` : ""}!
            </h1>
            <div className="w-3xl">
                <p className="text-gray-700 dark:text-gray-300 text-center">
                    This is your personalized patient dashboard. From here, you can view your medical records,
                    book appointments, access your vaccination history, and more.
                </p>
            </div>
        </div>
    )
}

export default PatientDashboard
