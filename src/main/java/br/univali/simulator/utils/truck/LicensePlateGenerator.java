package br.univali.simulator.utils.truck;

import java.util.Random;

public class LicensePlateGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public static String generate() {
        StringBuilder plate = new StringBuilder(7);

        // Adiciona as três primeiras letras
        for (int i = 0; i < 3; i++) {
            plate.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }

        // Adiciona o primeiro dígito
        plate.append(RANDOM.nextInt(10));

        // Adiciona a quarta letra
        plate.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));

        // Adiciona os dois últimos dígitos
        for (int i = 0; i < 2; i++) {
            plate.append(RANDOM.nextInt(10));
        }

        return plate.toString();
    }
}
