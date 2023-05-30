import java.util.Scanner;
//import java.util.Arrays;

public class AutomataFinitoDeterminista {

    private int numEstados;
    private int estadoInicial;
    private int estadoFinal;
    private int[][] transiciones;
    private char[] simbolos;

    private int estadoActual;

    public AutomataFinitoDeterminista(int numEstados, int[][] transiciones, char[] simbolos) {
        this.numEstados = numEstados;
        this.estadoInicial = 0;
        this.estadoFinal = numEstados - 1;
        this.transiciones = transiciones;
        this.simbolos = simbolos;
        this.estadoActual = estadoInicial;
    }

    public int getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
    }


    private static void visualizarMatrizTransiciones(int[][] transiciones, char[] simbolos) {
        System.out.println("Matriz de Transiciones:");
        System.out.print("Estado/Símbolo\t");
        for (char simbolo : simbolos) {
            System.out.print(simbolo + "\t");
        }
        System.out.println();
        for (int i = 0; i < transiciones.length; i++) {
            System.out.print("q" + i + "\t\t");
            for (int j = 0; j < transiciones[i].length; j++) {
                System.out.print(transiciones[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de estados: ");
        int numEstados = scanner.nextInt();

        System.out.print("Ingrese la cantidad de símbolos: ");
        int numSimbolos = 2; // Solo 2 símbolos

        char[] simbolos = new char[numSimbolos];

        for (int i = 0; i < numSimbolos; i++) {
            System.out.print("Ingrese el símbolo " + (i + 1) + ": ");
            simbolos[i] = scanner.next().charAt(0);
        }

        int[][] transiciones = new int[numEstados][numSimbolos];

        for (int i = 0; i < numEstados; i++) {
            for (int j = 0; j < numSimbolos; j++) {
                System.out.print("Ingrese la transición para el estado " + i + " con el símbolo '" + simbolos[j] + "': ");
                transiciones[i][j] = scanner.nextInt();
            }
        }

        AutomataFinitoDeterminista automata = new AutomataFinitoDeterminista(numEstados, transiciones, simbolos);

        System.out.print("Ingrese una cadena: ");
        String cadena = scanner.next();

        for (char simbolo : cadena.toCharArray()) {
            //int simboloIndex = buscarSimbolo(simbolo, simbolos);

            if (simbolo == simbolos[0]) {
                automata.setEstadoActual(automata.transiciones[automata.getEstadoActual()][0]);
            } else if (simbolo == simbolos[1]) {
                automata.setEstadoActual(automata.transiciones[automata.getEstadoActual()][1]);
            } else {
                System.out.println("Cadena inválida. Contiene símbolos no reconocidos.");
                return;
            }
        }

        if (automata.getEstadoActual() == automata.estadoFinal) {
            System.out.println("Cadena válida. Llegó al estado final.");
        } else {
            System.out.println("Cadena inválida. No llegó al estado final.");
        }


        visualizarMatrizTransiciones(transiciones, simbolos);
    }

    /*private static int buscarSimbolo(char simbolo, char[] simbolos) {
        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i] == simbolo) {
                return i;
            }
        }
        return -1;
    }*/
}
