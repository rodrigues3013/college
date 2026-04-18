object CustomerModule {

    // Task 1: Busca de Filmes
    fun startCustomerFlow() {
        println("\n--- MENU DO CLIENTE ---")
        println("Como deseja buscar um filme?")
        println("1. Por Título")
        println("2. Por Gênero")
        println("3. Ver todos os filmes")

        val opcao = readLine()
        val resultados = when (opcao) {
            "1" -> {
                print("Digite o título: ")
                val busca = readLine() ?: ""
                CinemaSystem.films.filter { it.title.contains(busca, ignoreCase = true) }
            }
            "2" -> {
                print("Digite o gênero: ")
                val busca = readLine() ?: ""
                CinemaSystem.films.filter { it.genre.contains(busca, ignoreCase = true) }
            }
            else -> CinemaSystem.films
        }

        if (resultados.isEmpty()) {
            println("Nenhum filme encontrado.")
            return
        }

        println("\nFilmes encontrados:")
        resultados.forEachIndexed { index, film ->
            println("${index + 1}. ${film.title} (${film.genre}) - Preço Base: £${film.basePrice}")
        }

        print("\nSelecione o número do filme para ver as sessões: ")
        val escolha = readLine()?.toIntOrNull() ?: return
        val filmeSelecionado = resultados.getOrNull(escolha - 1) ?: return

        showScreenings(filmeSelecionado)
    }

    // Task 2: Seleção de Sessão
    private fun showScreenings(film: Film) {
        val sessoes = CinemaSystem.getScreeningsForFilm(film.title)

        if (sessoes.isEmpty()) {
            println("Não há sessões disponíveis para este filme.")
            return
        }

        println("\nSessões para ${film.title}:")
        sessoes.forEachIndexed { index, s ->
            println("${index + 1}. Data: ${s.date} | Hora: ${s.startTime} | Sala: ${s.hallNumber}")
        }

        print("Escolha a sessão: ")
        val escolha = readLine()?.toIntOrNull() ?: return
        val sessaoSelecionada = sessoes.getOrNull(escolha - 1) ?: return

        processBooking(sessaoSelecionada)
    }

    // Task 3: Seleção de Assento e Compra
    private fun processBooking(screening: Screening) {
        println("\n--- MAPA DE ASSENTOS ---")
        screening.seats.forEach { seat ->
            val status = if (seat.isAvailable) "[ ${seat.number} ]" else "[ X ]"
            print("$status ")
        }
        println("\n(X = Ocupado)")

        print("\nQuantos ingressos deseja comprar? ")
        val qtd = readLine()?.toIntOrNull() ?: return

        val assentosEscolhidos = mutableListOf<Int>()
        for (i in 1..qtd) {
            print("Escolha o número do assento para o ingresso $i: ")
            val num = readLine()?.toIntOrNull() ?: continue

            val assento = screening.seats.find { it.number == num && it.isAvailable }
            if (assento != null) {
                assentosEscolhidos.add(num)
                assento.isAvailable = false // Marca como ocupado
            } else {
                println("Assento $num indisponível ou inválido!")
            }
        }

        if (assentosEscolhidos.isNotEmpty()) {
            // Membro C: Aqui chamamos a sua função de cálculo de preço!
            val total = SpecialOfferManager.calculatePrice(screening, assentosEscolhidos.size)

            println("\nTotal a pagar: £$total")
            print("Digite o valor para pagamento virtual: ")
            val pagamento = readLine()?.toDoubleOrNull() ?: 0.0

            if (pagamento >= total) {
                // Atualiza estatísticas (Task 3 e 4)
                screening.film.totalTicketsSold += assentosEscolhidos.size
                screening.totalTakings += total

                printTicket(screening, assentosEscolhidos, total)
            } else {
                println("Pagamento insuficiente. Reserva cancelada.")
                // Estorno: libera os assentos
                assentosEscolhidos.forEach { num -> screening.seats[num-1].isAvailable = true }
            }
        }
    }

    // Task 3: Formatação do Ingresso (Conforme Imagem 4)
    private fun printTicket(s: Screening, seats: List<Int>, price: Double) {
        println("\n*********************************")
        println("CINEMA GEMINI")
        println("Filme: ${s.film.title}")
        println("Data: ${s.date}")
        println("Hora: ${s.startTime}")
        println("Assentos: ${seats.joinToString(", ")}")
        println("Preço: £${"%.2f".format(price)}")
        println("*********************************")
    }
}
