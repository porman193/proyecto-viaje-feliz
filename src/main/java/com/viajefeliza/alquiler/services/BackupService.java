package com.viajefeliza.alquiler.services;


import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class BackupService {
    public String backupDatabase(String user,String password, String bd_name,String path) throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "mysqldump",
                "--user=" + user,
                "--password=" + password,
                "--default-character-set=utf8",
                bd_name
        );
        File file = new File(path);
        processBuilder.redirectOutput(file);

        Process process = processBuilder.start();

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        InputStream errorStream = process.getErrorStream();

        InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
        BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader);
        String line;

        StringBuilder result = new StringBuilder();

        while ((line = br.readLine()) != null) {
            result.append(line).append("\n"); // Imprime la salida del comando
        }

        StringBuilder errorOutput = new StringBuilder();
        String errorLine;

        while ((errorLine = errorBufferedReader.readLine()) != null) {
            errorOutput.append(errorLine).append("\n");
        }

        // Esperar a que el proceso termine
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            // El comando terminó con un error
            return"Error al realizar el respaldo de la base de datos. \n"+errorOutput;
        }

        return "Respaldo de la base de datos realizado correctamente. \n"+result;
    }
}