package com.blx.vendas.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Utils {

    public String[] lerArquivoTxt(ClassPathResource path) {
        try {
            File file = path.getFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            return bufferedReader.lines().collect(Collectors.joining()).split(";");

//            return bufferedReader.lines()
//                    .map(line -> line.split(","))
//                    .toArray(String[][]::new);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
