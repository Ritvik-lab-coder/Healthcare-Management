import { Outlet } from "react-router"
import PatientNavbar from "../static/patient/PatientNavbar"
import PatientSidebar from "../static/patient/PatientSidebar"

const PatientLayout = () => {
    return (
        <div className="min-h-screen flex flex-col bg-gray-50 dark:bg-gray-950">
            {/* Top Navbar */}
            <header className="sticky top-0 z-50 w-full">
                <PatientNavbar />
            </header>

            {/* Main Content Area */}
            <div className="flex flex-1">
                {/* Sidebar on the left */}
                <aside className="hidden md:block">
                    <PatientSidebar />
                </aside>

                {/* Page Content */}
                <main className="flex-1 p-4 overflow-y-auto">
                    <Outlet />
                </main>
            </div>
        </div>
    )
}

export default PatientLayout