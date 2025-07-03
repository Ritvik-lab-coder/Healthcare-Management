import { create } from "zustand"
import { persist } from "zustand/middleware"

type AuthStore = {
    token: string | null
    setToken: (token: string) => void
    clearToken: () => void
    isAuthenticated: boolean
}

export const useAuthStore = create<AuthStore, [["zustand/persist", AuthStore]]>(
    persist(
        (set) => ({
            token: null,
            setToken: (token) => set({ token, isAuthenticated: true }),
            clearToken: () => set({ token: null, isAuthenticated: false }),
            isAuthenticated: false,
        }),
        {
            name: "curasphere-auth",
        }
    )
)