import { create } from "zustand"
import { persist } from "zustand/middleware"

type UserDetails = {
    id: string,
    name: string,
    email: string,
    gender: string,
    dateOfBirth: string,
    contact: string,
    address: string,
    role: string,
    isProfileComplete: boolean
}

type UserStore = {
    user: UserDetails | null,
    setUserDetails: (user: UserDetails) => void,
    clearUserDetails: () => void
}

export const useUserStore = create<UserStore, [["zustand/persist", UserStore]]>(
    persist(
        (set) => ({
            user: null,
            setUserDetails: (user: UserDetails) => set({ user }),
            clearUserDetails: () => set({ user: null })
        }),
        {
            name: "curasphere-user",
        }
    )
)