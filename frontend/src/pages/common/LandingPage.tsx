import Footer from "@/components/static/Footer"
import HeroSection from "@/components/static/HeroSection"
import InfoBanner from "@/components/static/InfoBanner"
import Navbar from "@/components/static/Navbar"
import Specialities from "@/components/static/Specialities"
import Testimonials from "@/components/static/Testimonials"

const LandingPage = () => {
    return (
        <div className="bg-gray-50 text-gray-900 dark:bg-gray-950 dark:text-gray-50">
            <Navbar />
            <main className="flex flex-col gap-24 pt-20">
                <HeroSection />
                <Specialities />
                <InfoBanner />
                <Testimonials />
            </main>
            <Footer />
        </div>
    )
}

export default LandingPage
