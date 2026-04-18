// Definição das Classes Básicas
data class Film(
    val title: String,
    val genre: String,
    var basePrice: Double,
    var totalSold: Int = 0
)

data class Screening(
    val film: Film,
    val hall: Int,
    val date: String,
    val startTime: String, // Ex: "10:30"
    val isWeekday: Boolean,
    val seats: MutableList<Boolean> = MutableList(10) { true }, // true = disponível
    var totalTakings: Double = 0.0
)

// Onde os dados ficam guardados (Acessível por todos os membros)
object CinemaSystem {
    val films = mutableListOf<Film>()
    val screenings = mutableListOf<Screening>()

    // Configurações de descontos (Membro C controla isso)
    var morningDiscountActive = true
    var groupDiscountActive = true

    fun seedData() {
        // Membros: Adicionem aqui os 4 filmes e 12 sessões iniciais
        val filme1 = Film("Batman", "Ação", 10.0)
        films.add(filme1)
        screenings.add(Screening(filme1, 1, "2026-04-20", "10:00", true))
    }
}
