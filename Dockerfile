# Usamos a imagem oficial do Kotlin
FROM zenika/kotlin:latest

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Comando padrão: Compila e roda um arquivo específico
# (Você pode alterar 'Exercicio.kt' pelo nome do arquivo que deseja testar)
ENTRYPOINT ["sh", "-c", "kotlinc src/main/kotlin/$FILE_NAME -include-runtime -d app.jar && java -jar app.jar"]
