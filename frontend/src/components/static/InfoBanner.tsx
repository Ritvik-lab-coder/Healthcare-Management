const InfoBanner = () => {
    return (
        <div className="bg-teal-600 text-white py-6">
            <div className="max-w-7xl mx-auto grid sm:grid-cols-3 text-center gap-6 px-4">
                <div>
                    <p className="text-3xl font-bold">99.9%</p>
                    <p className="text-sm opacity-80">Uptime SLA</p>
                </div>
                <div>
                    <p className="text-3xl font-bold">256-bit</p>
                    <p className="text-sm opacity-80">Encryption</p>
                </div>
                <div>
                    <p className="text-3xl font-bold">24×7</p>
                    <p className="text-sm opacity-80">Support</p>
                </div>
            </div>
        </div>
    )
}

export default InfoBanner
