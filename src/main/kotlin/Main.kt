fun main() {
    // 1. Inicializa os dados (Filmes e Sessões)
    CinemaSystem.seedData()

    while (true) {
        println("\n==================================")
        println("       SISTEMA CINEMA MARCOS      ")
        println("==================================")
        println("1. Área do Cliente (Comprar Ingressos)")
        println("2. Área do Administrador (Gestão)")
        println("3. Sair")
        print("Escolha uma opção: ")

        when (readLine()) {
            "1" -> {
                // Chama o fluxo do Membro A
                CustomerModule.startCustomerFlow()
            }

            "2" -> {
                // Chama o login do Membro C
                val user = SpecialOfferManager.login()

                if (user != null && user.role == "Admin") {
                    // Se o login for admin, abre o menu de gestão
                    mostrarMenuAdmin()
                } else if (user != null) {
                    println("Acesso negado: Você não tem permissão de Administrador.")
                }
            }

            "3" -> {
                println("Saindo... Até logo!")
                break
            }

            else -> println("Opção inválida! Tente novamente.")
        }
    }
}

// Função auxiliar para organizar o menu do Admin (Mistura Membro B e C)
fun mostrarMenuAdmin() {
    while (true) {
        println("\n--- PAINEL DE CONTROLE ---")
        println("1. Gerenciar Filmes e Preços (Membro B)")
        println("2. Gerenciar Ofertas/Descontos (Membro C)")
        println("3. Voltar ao Menu Principal")
        print("Opção: ")

        when (readLine()) {
            "1" -> AdminModule.startAdminFlow() // Chama o módulo do Membro B
            "2" -> SpecialOfferManager.manageOffers() // Chama o módulo do Membro C
            "3" -> break
            else -> println("Opção inválida.")
        }
    }
}
