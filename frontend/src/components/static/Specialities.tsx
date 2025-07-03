import { Calendar, NotebookPen, Syringe, ShieldCheck, Stethoscope, HeartPulse } from "lucide-react"

const specialties = [
    { icon: Calendar, title: "Smart Scheduling", desc: "Auto-manage appointments & shifts." },
    { icon: NotebookPen, title: "Digital Records", desc: "All records in one secure place." },
    { icon: Syringe, title: "e‑Prescriptions", desc: "Prescribe digitally & connect to pharmacies." },
    { icon: HeartPulse, title: "Vitals Tracking", desc: "Real-time monitoring integration." },
    { icon: ShieldCheck, title: "HIPAA Security", desc: "256-bit encrypted with audit logs." },
    { icon: Stethoscope, title: "Tele‑Consult", desc: "Remote video & chat consultations." },
]

const Specialities = () => {
    return (
        <section className="max-w-7xl mx-auto px-4">
            <h2 className="text-3xl font-bold mb-12 text-center">Our Specialities</h2>
            <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-8">
                {specialties.map((s, i) => (
                    <div key={i} className="p-6 bg-white dark:bg-gray-900 border rounded-2xl shadow hover:shadow-lg">
                        <div className="mb-4 text-teal-600 dark:text-teal-400">
                            <s.icon className="w-8 h-8" />
                        </div>
                        <h3 className="font-semibold text-xl mb-1">{s.title}</h3>
                        <p className="text-sm text-gray-600 dark:text-gray-400">{s.desc}</p>
                    </div>
                ))}
            </div>
        </section>
    )
}

export default Specialities
