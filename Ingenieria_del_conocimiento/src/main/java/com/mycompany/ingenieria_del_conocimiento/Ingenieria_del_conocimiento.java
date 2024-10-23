package com.mycompany.ingenieria_del_conocimiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ingenieria_del_conocimiento {

    // convertierte los d�gitos binarios intermedios a decimal
    public static int binarioIntermedioADecimal(String binario) {
        return Integer.parseInt(binario, 2);
    }

    // M�todo para transformar el n�mero binario con la nueva regla
    public static double transformarBinario(String binario) {
        if (binario.length() != 9) {
            System.out.println("El n�mero binario debe tener exactamente 9 d�gitos.");
            return 0; // Por si el n�mero no tiene 9 d�gitos
        }

        // el primer d�gito (para determinar el signo), los d�gitos intermedios y el �ltimo d�gito
        char primerDigito = binario.charAt(0); // Primer bit para el signo
        String binarioIntermedio = binario.substring(1, 8); // D�gitos 2 a 8
        char ultimoDigito = binario.charAt(8); // �ltimo bit para agregar 0.5

        // Convertir los d�gitos intermedios a decimal
        int decimalIntermedio = binarioIntermedioADecimal(binarioIntermedio);

        // Si el �ltimo d�gito es '1', sumar 0.5 al resultado
        double resultado = decimalIntermedio;
        if (ultimoDigito == '1') {
            resultado += 0.5;
        }

        // Si el primer d�gito es '1', hacer el decimal negativo
        if (primerDigito == '1') {
            resultado = -resultado;
        }

        return resultado;
    }

    // M�tdo para realizar la operaci�n: -((x^2)-3) + (6x) + 13
    public static double realizarOperacion(double x) {
        return -((x * x) - 3) + (6 * x) + 13;
    }

    // Metodo para generar un n�mero binario aleatorio de 9 d�gitos
    public static String generarBinarioAleatorio() {
        Random random = new Random();
        StringBuilder binario = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            binario.append(random.nextInt(2)); // Genera un 0 o un 1 aleatoriamente
        }

        return binario.toString();
    }

    // M�todo para transformar los binarios invertidos
    public static List<String> transformarBinariosInvertidos(List<String> binarios, int cantidad) {
        List<String> binariosTrans = new ArrayList<>(binarios);
        for (int i = 0; i < binariosTrans.size(); i++) {
            String binario = binariosTrans.get(i);
            StringBuilder sb = new StringBuilder(binario);
            // Invertir solo los primeros n d�gitos
            for (int j = 0; j < cantidad; j++) {
                sb.setCharAt(j, binario.charAt(j) == '0' ? '1' : '0');
            }
            binariosTrans.set(i, sb.toString());
        }
        return binariosTrans;
    }

    // M�todo para intercambiar d�gitos
    public static List<String> intercambiarDigitos(List<String> binarios, int cantidad) {
        List<String> binariosTrans = new ArrayList<>(binarios);

        // Lo de 1 al 2, 3 al 4 y 5 al 6
        for (int i = 0; i < binariosTrans.size() - 1; i += 2) {
            String binario1 = binariosTrans.get(i);
            String binario2 = binariosTrans.get(i + 1);

            // Nuevas cadenas para los n�meros modificados
            StringBuilder newBinario1 = new StringBuilder(binario1);
            StringBuilder newBinario2 = new StringBuilder(binario2);

            // Intercambiar los primeros n d�gitos entre los pares
            for (int j = 0; j < cantidad; j++) {
                char temp = binario1.charAt(j);
                newBinario1.setCharAt(j, binario2.charAt(j));
                newBinario2.setCharAt(j, temp);
            }

            // Actualizar la lista con los n�meros modificados
            binariosTrans.set(i, newBinario1.toString());
            binariosTrans.set(i + 1, newBinario2.toString());
        }

        return binariosTrans;
    }

    public static void main(String[] args) {
        List<Double> resultadosOperacion = new ArrayList<>(); // Lista para almacenar los resultados
        List<String> binariosList = new ArrayList<>(); // Lista para almacenar los n�meros binarios generados

        // Generar y procesar 6 n�meros binarios aleatorios
        for (int i = 0; i < 10; i++) {
            // Lo mismo pero de los 9 d�gitos de cada uno
            String binario = generarBinarioAleatorio();
            binariosList.add(binario);
            System.out.println("N�mero binario generado: " + binario);

            // Transformar y mostrar el resultado en decimal
            double resultadoDecimal = transformarBinario(binario);
            System.out.println("El n�mero binario " + binario + " en decimal es: " + resultadoDecimal);

            // hacer la operaci�n matem�tica con el decimal obtenido
            double resultadoOperacion = realizarOperacion(resultadoDecimal);
            System.out.println("El resultado de la operaci�n es: " + resultadoOperacion);

            // Agregar el resultado a la lista
            resultadosOperacion.add(resultadoOperacion);
        }

        // Ordenar la lista de resultados de mayor a menor
        Collections.sort(resultadosOperacion, Collections.reverseOrder());

        // Mostrar los resultados ordenados
        System.out.println("\nResultados de las operaciones ordenados de mayor a menor:");
        for (double resultado : resultadosOperacion) {
            System.out.println(resultado);
        }

        // Pedir al usuario que ingrese la n cantidad de d�gitos
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese la cantidad de d�gitos a cambiar (1-9): ");
        int cantidad = scanner.nextInt();

        // Primero invertir los d�gitos
        List<String> binariosTrans = transformarBinariosInvertidos(binariosList, cantidad);

        // Luego intercambiar los d�gitos entre pares
        binariosTrans = intercambiarDigitos(binariosTrans, cantidad);

        // Continuar con el c�lculo de resultados (si otra vez xddd)
        resultadosOperacion.clear();
        for (String binario : binariosTrans) {
            double resultadoDecimal = transformarBinario(binario);
            double resultadoOperacion = realizarOperacion(resultadoDecimal);
            resultadosOperacion.add(resultadoOperacion);
            System.out.println("N�mero binario transformado: " + binario);
            System.out.println("El n�mero binario " + binario + " en decimal es: " + resultadoDecimal);
            System.out.println("El resultado de la operaci�n es: " + resultadoOperacion);
        }

        // Ordenar la lista de resultados de mayor a menor
        Collections.sort(resultadosOperacion, Collections.reverseOrder());

        // Mostrar los nuevos resultados ordenados
        System.out.println("\nNuevos resultados de las operaciones ordenados de mayor a menor:");
        for (double resultado : resultadosOperacion) {
            System.out.println(resultado);
        }
    }
}