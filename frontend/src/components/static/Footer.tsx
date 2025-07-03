const Footer = () => {
    return (
        <footer className="mt-24 bg-gray-100 dark:bg-gray-900 py-8">
            <div className="max-w-7xl mx-auto px-4 flex flex-col sm:flex-row justify-between text-sm">
                <div>
                    <p className="font-bold text-teal-600">CuraSphere</p>
                    <p className="text-gray-600 dark:text-gray-400">© {new Date().getFullYear()} CuraSphere Inc.</p>
                </div>
                <div className="flex gap-4 mt-4 sm:mt-0">
                    <a href="/privacy" className="hover:underline">Privacy</a>
                    <a href="/terms" className="hover:underline">Terms</a>
                    <a href="mailto:support@curasphere.com" className="hover:underline">Contact</a>
                </div>
            </div>
        </footer>
    )
}

export default Footer
