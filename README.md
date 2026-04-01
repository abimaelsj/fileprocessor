# 🚀 Projeto Java para processar arquivos

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

## 📝 Descrição
Programa Java desktop em Java 21.Para demonstrar code review do código apresentado anteriormente

## 📋 Pré-requisitos
Antes de começar, você precisará ter instalado na sua máquina:
* [JDK 21](https://openjdk.org/projects/jdk/21/) ou superior
* IDE de sua preferência (IntelliJ, Eclipse, VS Code)

## 🛠️ Tecnologias Utilizadas
* **Java 21**

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/abimaelsj/fileprocessor.git]

## 🛠️ Revisão de Código:
´´´java
    private static void processor() {
        final List<String> lines = new ArrayList<>();
        final int numeroThreads = 5;
        final int numeroIteracoes = 10;
        final Logger log = Logger.getLogger(FileProcessor.class.getName());
      
      //- Adicionei o código dentro do try para encerramento automático do executor:
        try (ExecutorService executor = Executors.newFixedThreadPool(numeroThreads)) {
          //- Defini variável para o número de iterações:
            for (int i = 0; i < numeroIteracoes; i++) {
                executor.submit(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new
                                FileReader("data.txt"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            lines.add(line.toUpperCase());
                        }
                        br.close();
                   //-Tratei o erro com exception específica no lugar de uma genérica, finalizei o executor e interrompi a thread em execução logando o erro:     
                    } catch (IOException e) {
                        executor.shutdown();
                        Thread.currentThread().interrupt();
                        log.log(Level.SEVERE, "Erro ao abrir o arquivo, Thread interrompida", e);
                    }
                });
            }
            //- Removi o código de log de console e adicionei um log específico:
            log.log(Level.INFO, "Lines processed: " + lines.size());
            
            //Adicionei um bloco para garantir que as Threads sejam finalizadas, caso contrário, encerradas.
            try {
                // Espera no máximo 60 segundos para que todas as tarefas terminem
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    // Se o timeout ocorrer, força o desligamento imediato
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        log.log(Level.INFO, "Todas as tarefas foram concluídas ou o tempo limite foi atingido.");
    }
´´´
