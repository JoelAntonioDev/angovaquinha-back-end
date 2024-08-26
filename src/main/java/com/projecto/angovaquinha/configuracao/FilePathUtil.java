package com.projecto.angovaquinha.configuracao;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathUtil {

    public static String getProjectRoot() {
        return Paths.get(System.getProperty("user.dir")).normalize().toString();
    }
    public static String getRelativePath(String absoluteFilePath) {
        // Obtém o caminho absoluto da raiz do projeto
        Path projectRootPath = Paths.get(getProjectRoot());
        // Converte o caminho absoluto do arquivo para um Path
        Path absolutePath = Paths.get(absoluteFilePath);
        // Obtém o caminho relativo a partir da raiz do projeto
        Path relativePath = projectRootPath.relativize(absolutePath);
        return relativePath.toString();
    }
}

