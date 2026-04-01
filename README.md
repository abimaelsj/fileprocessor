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
   git clone [https://github.com/abimaelsj/srv-consulta-cep.gt]

## 🛠️ Revisão de Código:



ExecutorService executor = Executors.newFixedThreadPool(5);
- Adicionei o código dentro do try para encerramento automático do executor:     
try (ExecutorService executor = Executors.newFixedThreadPool(numeroThreads)) {}

for (int i = 0; i < 10; i++)
 - Defini variável para o número de iterações:
  for (int i = 0; i < numeroIteracoes; i++)
  
catch (Exception e)
-Tratei o erro com exception específica no lugar de uma genérica, finalizei o executor e interrompi a thread em execução logando o erro:
 catch (IOException e) {
                        executor.shutdown();
                        Thread.currentThread().interrupt();
                        log.log(Level.SEVERE, "Erro ao abrir o arquivo, Thread interrompida", e);
                    }}

System.out.println("Lines processed: " + lines.size());
- Removi o códide de log de console e adicionei um log específico:
log.log(Level.INFO, "Lines processed: " + lines.size());

Adicionei um bloco para garantir que a Threads sejam finalizadas, caso contrário, encerradas.
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

   
