object SpecialOfferManager {

    // Task 7: Usuários Hard-coded (Membro C)
    private val users = listOf(
        User("admin", "Admin123", "Admin"),
        User("cliente1", "senha123", "Customer")
    )

    // Task 8: O administrador deve poder habilitar/desabilitar as ofertas
    // Estas variáveis são consultadas pelo CinemaSystem ou diretamente aqui
    var isMorningDiscountEnabled = true
    var isGroupDiscountEnabled = true

    // Task 7: Mecanismo de Login
    fun login(): User? {
        print("Usuário: ")
        val username = readLine()
        print("Senha: ")
        val password = readLine()

        val user = users.find { it.username == username && it.password == password }
        if (user == null) {
            println("Erro: Usuário ou senha incorretos.")
        } else {
            println("Login bem-sucedido! Bem-vindo, ${user.username}.")
        }
        return user
    }

    // Task 8 e 9: Cálculo Automático de Ofertas
    fun calculatePrice(screening: Screening, numTickets: Int): Double {
        var pricePerTicket = screening.film.basePrice

        // 1. Morning Discount (25% off)
        // Regra: Antes das 12:00, apenas em dias de semana
        val hour = screening.startTime.split(":")[0].toInt()
        if (isMorningDiscountEnabled && screening.isWeekday && hour < 12) {
            pricePerTicket *= 0.75
            println("[Sistema] Desconto de Manhã aplicado: 25% de redução.")
        }

        // 2. Group Booking Discount
        // Regra: Primeiros 2 ingressos preço cheio, adicionais 30% off
        return if (isGroupDiscountEnabled && numTickets > 2) {
            val fullPriceTickets = 2 * pricePerTicket
            val discountedTickets = (numTickets - 2) * (pricePerTicket * 0.70)
            println("[Sistema] Desconto de Grupo aplicado nos ingressos excedentes.")
            fullPriceTickets + discountedTickets
        } else {
            pricePerTicket * numTickets
        }
    }

    // Função para o Admin gerenciar as ofertas (Task 8)
    fun manageOffers() {
        println("\n--- GERENCIAR OFERTAS ESPECIAIS ---")
        println("1. Morning Discount: ${if (isMorningDiscountEnabled) "ATIVO" else "INATIVO"}")
        println("2. Group Discount: ${if (isGroupDiscountEnabled) "ATIVO" else "INATIVO"}")
        println("3. Alternar Morning Discount")
        println("4. Alternar Group Discount")
        println("5. Voltar")

        when (readLine()) {
            "3" -> isMorningDiscountEnabled = !isMorningDiscountEnabled
            "4" -> isGroupDiscountEnabled = !isGroupDiscountEnabled
        }
    }
}
