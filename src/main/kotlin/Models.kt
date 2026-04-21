// 1. Representa o Filme
data class Film(
    val title: String,
    val genre: String,
    var basePrice: Double,
    var totalTicketsSold: Int = 0 // Começa em zero conforme o Task 4
)

// 2. Representa cada Assento (Task 3 e Regra de 10 assentos)
data class Seat(
    val number: Int,
    var isAvailable: Boolean = true
)

// 3. Representa a Sessão (Onde a mágica acontece)
data class Screening(
    val film: Film,
    val hallNumber: Int,
    val date: String,
    val startTime: String, // Ex: "10:30"
    val isWeekday: Boolean,
    // Cada sessão já nasce com 10 assentos (Requisito da Imagem 1)
    val seats: List<Seat> = List(10) { index -> Seat(index + 1) },
    var totalTakings: Double = 0.0
)

// 4. Representa o Usuário (Task 7 Membro C)
data class User(
    val username: String,
    val password: String,
    val role: String // "Admin" ou "Customer"
)
