package com.blx.vendas.utils;

import com.blx.vendas.exceptions.ErroLeitura;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RelatorioUtils {

    public static List<String> lerArquivoTxt(ClassPathResource path) {
        try {
            File file = path.getFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            return bufferedReader
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new ErroLeitura("Erro ao ler arquivo Txt.", ex);
        }
    }
}
