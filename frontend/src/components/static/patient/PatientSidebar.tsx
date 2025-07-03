import {
    Sheet,
    SheetContent,
    SheetTrigger,
} from "@/components/ui/sheet"
import { Button } from "@/components/ui/button"
import {
    User,
    Stethoscope,
    FileText,
    Syringe,
    CalendarCheck,
    Receipt,
    Menu,
} from "lucide-react"
import { Link, useLocation } from "react-router"
import clsx from "clsx"
import { useState } from "react"

const links = [
    { name: "Profile", icon: <User className="w-5 h-5" />, path: "/patient/profile" },
    { name: "Doctors", icon: <Stethoscope className="w-5 h-5" />, path: "/patient/doctors" },
    { name: "Medical Records", icon: <FileText className="w-5 h-5" />, path: "/patient/medical-records" },
    { name: "Vaccination Records", icon: <Syringe className="w-5 h-5" />, path: "/patient/vaccinations" },
    { name: "Appointments", icon: <CalendarCheck className="w-5 h-5" />, path: "/patient/appointments" },
    { name: "Bills", icon: <Receipt className="w-5 h-5" />, path: "/patient/bills" },
]

const SidebarContent = ({ onLinkClick }: { onLinkClick?: () => void }) => {
    const location = useLocation()

    return (
        <div className="flex flex-col h-full p-4 border-r border-gray-200 dark:border-gray-800 bg-white dark:bg-gray-900 w-64">
            <h2 className="text-2xl font-bold text-teal-600 dark:text-teal-400 mb-6">
                Patient Panel
            </h2>
            <nav className="space-y-2 flex-1">
                {links.map((link) => (
                    <Link
                        key={link.name}
                        to={link.path}
                        onClick={onLinkClick}
                        className={clsx(
                            "flex items-center gap-3 px-3 py-2 rounded-md font-medium transition-colors",
                            location.pathname === link.path
                                ? "bg-teal-100 dark:bg-teal-800 text-teal-800 dark:text-white"
                                : "text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-800"
                        )}
                    >
                        {link.icon}
                        <span>{link.name}</span>
                    </Link>
                ))}
            </nav>
        </div>
    )
}

const PatientSidebar = () => {
    const [open, setOpen] = useState(false)

    return (
        <>
            {/* Mobile Sidebar Trigger */}
            <div className="md:hidden p-4">
                <Sheet open={open} onOpenChange={setOpen}>
                    <SheetTrigger asChild>
                        <Button variant="outline" size="icon">
                            <Menu className="w-5 h-5" />
                        </Button>
                    </SheetTrigger>
                    <SheetContent side="left" className="p-0 w-64">
                        <SidebarContent onLinkClick={() => setOpen(false)} />
                    </SheetContent>
                </Sheet>
            </div>

            {/* Desktop Sidebar */}
            <div className="hidden md:block">
                <SidebarContent />
            </div>
        </>
    )
}

export default PatientSidebar
