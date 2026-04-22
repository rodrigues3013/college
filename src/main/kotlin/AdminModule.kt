object AdminModule {

    fun startAdminFlow() {
        while (true) {
            println("\n--- PAINEL DO ADMINISTRADOR ---")
            println("1. Visualizar Filmes e Estatísticas")
            println("2. Adicionar Novo Filme")
            println("3. Ajustar Preços (Fator de Preço)")
            println("4. Voltar ao Menu Principal")

            when (readLine()) {
                "1" -> viewFilmStats()
                "2" -> addNewFilm()
                "3" -> modifyTicketPricing()
                "4" -> break
                else -> println("Opção inválida.")
            }
        }
    }

    // Task 4: View Film and Screening Information
    private fun viewFilmStats() {
        println("\n--- RELATÓRIO DE VENDAS ---")
        if (CinemaSystem.films.isEmpty()) {
            println("Nenhum filme cadastrado.")
            return
        }

        CinemaSystem.films.forEach { film ->
            println("--------------------------------")
            println("Título: ${film.title} [Gênero: ${film.genre}]")
            println("Preço Base Atual: £${"%.2f".format(film.basePrice)}")
            println("Ingressos Vendidos: ${film.totalTicketsSold}")

            // Mostra as sessões deste filme e a arrecadação de cada uma
            val sessoes = CinemaSystem.getScreeningsForFilm(film.title)
            println("Sessões Disponíveis:")
            sessoes.forEach { s ->
                println("  - Sala ${s.hallNumber} | ${s.date} às ${s.startTime} | Takings: £${"%.2f".format(s.totalTakings)}")
            }
        }
    }

    // Task 5: Add New Film and Screening
    private fun addNewFilm() {
        println("\n--- CADASTRAR NOVO FILME ---")
        print("Título: ")
        val title = readLine() ?: ""
        print("Gênero: ")
        val genre = readLine() ?: ""
        print("Preço Base: ")
        val price = readLine()?.toDoubleOrNull() ?: 0.0

        val newFilm = Film(title, genre, price)
        CinemaSystem.films.add(newFilm)

        // Adiciona pelo menos uma sessão para o novo filme (Requisito Task 5)
        println("Agora cadastre uma sessão inicial:")
        print("Número da Sala: ")
        val hall = readLine()?.toIntOrNull() ?: 1
        print("Data (YYYY-MM-DD): ")
        val date = readLine() ?: "2026-01-01"
        print("Horário (HH:MM): ")
        val time = readLine() ?: "20:00"
        print("É dia de semana? (S/N): ")
        val isWeekday = readLine()?.toUpperCase() == "S"

        val newScreening = Screening(newFilm, hall, date, time, isWeekday)
        CinemaSystem.screenings.add(newScreening)

        println("Filme e sessão cadastrados com sucesso!")
    }

    // Task 6: Modify Ticket Pricing
    private fun modifyTicketPricing() {
        println("\n--- AJUSTE GLOBAL DE PREÇOS ---")
        println("Digite o fator de ajuste (Ex: 1.1 para aumentar 10% ou 0.9 para reduzir 10%):")
        val factor = readLine()?.toDoubleOrNull() ?: 1.0

        // Aplica o fator em todos os filmes (Task 6)
        CinemaSystem.films.forEach { film ->
            film.basePrice *= factor
        }

        println("Preços atualizados com sucesso em todos os filmes e sessões relevantes.")
    }
}
