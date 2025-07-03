import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from "@/components/ui/carousel"

const testimonials = [
  {
    quote: "CuraSphere reduced our paperwork by 70%. Our staff finally has time to focus on patients instead of admin tasks!",
    name: "Dr. Ananya Rao",
    role: "Chief Medical Officer, Aster Hospitals",
  },
  {
    quote: "The tele‑consult module let us expand into rural areas without opening new branches—game changer!",
    name: "Prakash Menon",
    role: "Director, Healing Touch Clinics",
  },
  {
    quote: "We achieved HIPAA compliance in weeks thanks to CuraSphere’s built‑in security & audit logs.",
    name: "Samantha Lee",
    role: "Healthcare IT Lead, Nova Health",
  },
]

const Testimonials = () => {
    return (
        <section className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
      <h2 className="text-3xl font-bold mb-8">Loved by professionals worldwide</h2>

      <Carousel className="w-full">
        <CarouselContent>
          {testimonials.map((t, i) => (
            <CarouselItem key={i} className="px-4">
              <figure className="p-8 bg-white dark:bg-gray-900 border border-gray-200 dark:border-gray-800 rounded-2xl shadow-sm">
                <blockquote className="text-lg italic leading-relaxed mb-4">“{t.quote}”</blockquote>
                <figcaption className="font-semibold">
                  {t.name}
                  <span className="block font-normal text-sm text-gray-600 dark:text-gray-400">{t.role}</span>
                </figcaption>
              </figure>
            </CarouselItem>
          ))}
        </CarouselContent>
        <CarouselPrevious />
        <CarouselNext />
      </Carousel>
    </section>
    )
}

export default Testimonials
