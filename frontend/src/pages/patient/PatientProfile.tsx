import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Label } from "@/components/ui/label"
import { useUserStore } from "@/stores/userStore";
import { useEffect, useState } from "react";
import axios from "axios";
import { useAuthStore } from "@/stores/authStore";
import { usePatientStore } from "@/stores/patientStore";
import { toast } from "react-toastify";
import CompleteProfileModal from "@/components/static/patient/CompleteProfileModal";
import { getPatientProfile, registerPatientDetailsEndpoint } from "@/APIs/patientEndpoints";

const Field = ({ label, value }: { label: string; value?: string | number }) => (
    <div className="flex flex-col gap-1">
        <Label className="text-sm text-gray-600 dark:text-gray-300">{label}</Label>
        <p className="text-base font-medium text-gray-900 dark:text-white">{value || "—"}</p>
    </div>
)

const PatientProfile = () => {
    const user = useUserStore((state) => state.user)
    const token = useAuthStore((state) => state.token)
    const setPatientProflie = usePatientStore((state) => state.setPatientProfile)
    const patient = usePatientStore((state) => state.patient)

    const [isProfileComplete, setIsProfileComplete] = useState<boolean | undefined>(false)
    const [loading, setLoading] = useState<boolean>(false)

    useEffect(() => {
        setIsProfileComplete(user?.isProfileComplete)
    }, [user])

    useEffect(() => {
        const fetchPatientProfle = async () => {
            try {
                const response = await axios.get(getPatientProfile, {
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                })

                if (response.status === 200) {
                    setPatientProflie(response.data)
                }
            } catch (error) {
                // toast.error("Internal Server Error")
            }
        }
        fetchPatientProfle()
    }, [])

    const handleProfileSubmit = async (formData: {
        age: string
        bloodGroup: string
        emergencyContact: string
        allergies: string
    }) => {
        setLoading(true)
        try {
            console.log(formData)
            const response = await axios.post(registerPatientDetailsEndpoint, formData, {
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            })

            if (response.status === 201) {
                toast.success("Profile completed.")
            } else {
                toast.error("Something went wrong.")
            }
        } catch (error) {
            toast.error("Internal Server Error")
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="mt-8 my-auto max-w-4xl w-full mx-auto px-4 py-6">
            <Card className="shadow-md border border-gray-200 dark:border-gray-800 bg-white dark:bg-gray-900">
                <CardHeader>
                    <CardTitle className="text-2xl text-teal-600 dark:text-teal-400">
                        Patient Profile
                    </CardTitle>
                </CardHeader>
                <CardContent className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <Field label="Name" value={user?.name} />
                    <Field label="Email" value={user?.email} />
                    <Field label="Gender" value={user?.gender} />
                    <Field label="Date of Birth" value={user?.dateOfBirth} />
                    <Field label="Contact" value={user?.contact} />
                    <Field label="Address" value={user?.address} />

                    {
                        isProfileComplete ? (
                            <>
                                <Field label="Age" value={patient?.age} />
                                <Field label="Emergency Contact" value={patient?.emergencyContact} />
                                <Field label="Blood Group" value={patient?.bloodGroup} />
                                <Field label="Allergies" value={patient?.allergies} />
                            </>
                        ) : (
                            <>
                                <p className="text-sm text-gray-600 dark:text-gray-400">Please complete your profile.</p>
                                <CompleteProfileModal onSubmit={handleProfileSubmit} />
                            </>
                        )
                    }

                </CardContent>
            </Card>
        </div>
    )
}

export default PatientProfile
