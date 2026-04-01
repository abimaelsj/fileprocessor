package br.com.btg.fileprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessor {
    public static void main(String[] args) {
        processor();
    }

    private static void processor() {
        final List<String> lines = new ArrayList<>();
        final int numeroThreads = 5;
        final int numeroIteracoes = 10;
        final Logger log = Logger.getLogger(FileProcessor.class.getName());

        try (ExecutorService executor = Executors.newFixedThreadPool(numeroThreads)) {
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
                    } catch (IOException e) {
                        executor.shutdown();
                        Thread.currentThread().interrupt();
                        log.log(Level.SEVERE, "Erro ao abrir o arquivo, Thread interrompida", e);
                    }
                });
            }
            log.log(Level.INFO, "Lines processed: " + lines.size());

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
}