import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Calendar } from "@/components/ui/calendar"
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import { format } from "date-fns"
import { CalendarIcon, Loader2 } from "lucide-react"
import { useState } from "react"
import { toast } from "react-toastify"
import axios from "axios"
import { registerUserEndpoint } from "@/APIs/authEndpoints"
import { useNavigate } from "react-router"

type UserData = {
    name: string,
    email: string,
    password: string,
    gender: string,
    dateOfBirth: string,
    contact: string,
    address: string,
    role: string
}

const Signup = () => {

    const [userData, setUserData] = useState<UserData>({
        name: "",
        email: "",
        password: "",
        gender: "",
        dateOfBirth: "",
        contact: "",
        address: "",
        role: ""
    })

    const [dob, setDob] = useState<Date | undefined>()

    const [loading, setLoading] = useState<boolean>(false);

    const navigate = useNavigate()

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target
        setUserData((prev) => ({ ...prev, [name]: value }))
    }

    const handleDateChange = (date?: Date) => {
        setDob(date)
        if (date) {
            const formatted = date.toISOString().split("T")[0]
            setUserData((prev) => ({ ...prev, dateOfBirth: formatted }))
        }
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()

        setLoading(true)
        try {
            const response = await axios.post(registerUserEndpoint, userData)

            if (response.data != null) {
                toast.success("Account created successfully. Please login.")

                setTimeout(() => {
                    navigate("/auth/login")
                }, 3000)
            } else {
                toast.error("Something went wrong")
            }
        } catch (error) {
            toast.error("Internal Server Error")
        } finally {
            setLoading(false)
        }

        console.log("Form submitted")
    }

    return (
        <div className="min-h-screen flex items-center justify-center px-4 py-16 bg-gray-50 dark:bg-gray-950">
            <form
                onSubmit={handleSubmit}
                className="w-full max-w-2xl bg-white dark:bg-gray-900 border border-gray-200 dark:border-gray-800 p-8 rounded-2xl shadow-md space-y-6"
            >
                <h2 className="text-3xl font-bold text-center text-teal-600 dark:text-teal-400">Create Account</h2>

                <div className="grid sm:grid-cols-2 gap-6">
                    <div>
                        <Label htmlFor="name">Full Name</Label>
                        <Input id="name" name="name" placeholder="John Doe" required value={userData.name} onChange={handleChange} />
                    </div>

                    <div>
                        <Label htmlFor="email">Email</Label>
                        <Input type="email" id="email" name="email" placeholder="john@example.com" required value={userData.email} onChange={handleChange} />
                    </div>

                    <div>
                        <Label htmlFor="password">Password</Label>
                        <Input type="password" id="password" name="password" placeholder="••••••••" required value={userData.password} onChange={handleChange} />
                    </div>

                    <div>
                        <Label htmlFor="contact">Contact</Label>
                        <Input type="tel" id="contact" name="contact" placeholder="+91 9876543210" required value={userData.contact} onChange={handleChange} />
                    </div>

                    <div>
                        <Label htmlFor="gender">Gender</Label>
                        <Select required onValueChange={(value) => {
                            setUserData((prev) => ({ ...prev, gender: value }))
                        }}>
                            <SelectTrigger id="gender">
                                <SelectValue placeholder="Select Gender" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="MALE">Male</SelectItem>
                                <SelectItem value="FEMALE">Female</SelectItem>
                            </SelectContent>
                        </Select>
                    </div>

                    <div>
                        <Label htmlFor="role">Role</Label>
                        <Select required onValueChange={(value) => {
                            setUserData((prev) => ({ ...prev, role: value }))
                        }}>
                            <SelectTrigger id="role">
                                <SelectValue placeholder="Select Role" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="ROLE_PATIENT">Patient</SelectItem>
                                <SelectItem value="ROLE_DOCTOR">Doctor</SelectItem>
                            </SelectContent>
                        </Select>
                    </div>

                    <div className="sm:col-span-2">
                        <Label htmlFor="address">Address</Label>
                        <Input id="address" name="address" placeholder="123 Main St, City, Country" required value={userData.address} onChange={handleChange} />
                    </div>

                    <div className="sm:col-span-2">
                        <Label>Date of Birth</Label>
                        <Popover>
                            <PopoverTrigger asChild>
                                <Button
                                    variant="outline"
                                    className="w-full justify-start text-left font-normal"
                                >
                                    <CalendarIcon className="mr-2 h-4 w-4" />
                                    {dob ? format(dob, "PPP") : <span>Pick a date</span>}
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0" align="start">
                                <Calendar mode="single" selected={dob} onSelect={handleDateChange} initialFocus />
                            </PopoverContent>
                        </Popover>
                    </div>
                </div>

                <Button type="submit" className="w-full bg-teal-500 hover:bg-teal-600 text-white" disabled={loading}>
                    {loading ? (
                        <><Loader2 /> Please Wait...</>
                    ) : (
                        <>Sign Up</>
                    )}
                </Button>
            </form>
        </div>
    )
}

export default Signup
