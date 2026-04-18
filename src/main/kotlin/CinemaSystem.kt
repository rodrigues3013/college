object CinemaSystem {
    // Listas que armazenarão os dados em memória
    val films = mutableListOf<Film>()
    val screenings = mutableListOf<Screening>()

    // Configurações globais de ofertas (Membro C pode alterar isso)
    var morningDiscountActive = true
    var groupDiscountActive = true

    // Função para carregar os dados iniciais (Hard-coded)
    fun seedData() {
        // 1. Criando os Filmes (Task 4 do Membro B)
        val f1 = Film("Batman", "Ação", 10.0)
        val f2 = Film("Duna", "Sci-Fi", 12.0)
        val f3 = Film("Shrek", "Animação", 8.0)
        val f4 = Film("O Poderoso Chefão", "Drama", 15.0)

        films.addAll(listOf(f1, f2, f3, f4))

        // 2. Criando as Sessões (Task 4 - Mínimo 12 no total)
        // Screening(filme, sala, data, hora, éDiaDeSemana)

        // Filme 1
        screenings.add(Screening(f1, 1, "2026-04-20", "10:00", true)) // Manhã/Semana (Promoção!)
        screenings.add(Screening(f1, 1, "2026-04-20", "15:00", true))
        screenings.add(Screening(f1, 2, "2026-04-21", "20:00", true))

        // Filme 2
        screenings.add(Screening(f2, 1, "2026-04-20", "13:00", true))
        screenings.add(Screening(f2, 2, "2026-04-21", "18:00", true))
        screenings.add(Screening(f2, 1, "2026-04-22", "21:30", true))

        // Filme 3
        screenings.add(Screening(f3, 3, "2026-04-20", "09:30", true)) // Manhã/Semana (Promoção!)
        screenings.add(Screening(f3, 3, "2026-04-20", "14:00", true))
        screenings.add(Screening(f3, 3, "2026-04-25", "11:00", false)) // Sábado (Sem promo manhã)

        // Filme 4
        screenings.add(Screening(f4, 2, "2026-04-22", "19:00", true))
        screenings.add(Screening(f4, 1, "2026-04-23", "20:00", true))
        screenings.add(Screening(f4, 2, "2026-04-26", "21:00", false)) // Domingo
    }

    // Função auxiliar para encontrar sessões de um filme específico
    fun getScreeningsForFilm(filmTitle: String): List<Screening> {
        return screenings.filter { it.film.title.equals(filmTitle, ignoreCase = true) }
    }
}
