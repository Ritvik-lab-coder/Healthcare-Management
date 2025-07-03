import { Button } from "../ui/button"
import HeroImage from "../../assets/heroimage.png"
import { motion } from "framer-motion"

const HeroSection = () => {
    return (
        <section className="py-24 px-4 max-w-7xl mx-auto grid md:grid-cols-2 gap-10">
            <motion.div initial={{ opacity: 0, y: 30 }} animate={{ opacity: 1, y: 0 }} transition={{ duration: 0.6 }}>
                <h1 className="text-4xl sm:text-5xl font-bold mb-6 leading-tight">
                    Modernize your <span className="text-teal-600">Healthcare Workflow</span>
                </h1>
                <p className="text-lg text-gray-600 dark:text-gray-300 mb-6">
                    CuraSphere helps clinics & hospitals streamline appointments, records, billing & telemedicine.
                </p>
                <div className="flex gap-4">
                    <Button size="lg">Get Started</Button>
                    <Button size="lg" variant="secondary">Explore Features</Button>
                </div>
            </motion.div>
            <div>
                <motion.img src={HeroImage} alt="Dashboard" className="w-full rounded-xl shadow-xl" initial={{ opacity: 0, scale: 0.9 }}
                    animate={{ opacity: 1, scale: 1 }}
                    transition={{ duration: 0.6, delay: 0.2 }} />
            </div>
        </section>
    )
}

export default HeroSection
