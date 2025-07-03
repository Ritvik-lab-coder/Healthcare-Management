import { create } from "zustand"
import { persist } from "zustand/middleware"

type PatientProfile = {
    id: string,
    name: string,
    email: string,
    gender: string,
    dateOfBirth: string,
    contact: string,
    address: string,
    role: string,
    age: string,
    emergencyContact: string,
    allergies: string,
    bloodGroup: string
}

type PatientStore = {
    patient: PatientProfile | null,
    setPatientProfile: (patient: PatientProfile) => void,
    clearPatientProfile: () => void
}

export const usePatientStore = create<PatientStore, [["zustand/persist", PatientStore]]>(
    persist(
        (set) => ({
            patient: null,
            setPatientProfile: (patient: PatientProfile) => set({ patient }),
            clearPatientProfile: () => set({ patient: null })
        }),
        {
            name: "curasphere-patient",
        }
    )
)
