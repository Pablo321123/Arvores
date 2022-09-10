import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void pesquisaOrdenada(ArvoreBinaria ab, int valIni) {

        System.out.println(Cores.ANSI_CYAN + "\nÁrvore ORDENADA: \n" + Cores.ANSI_RESET
                + "---------------------------------------------");

        while (true) {
            if (valIni > 9000) {
                break;
            } else {
                Item it = new Item(valIni);
                ab.insere(it, ab.getRaiz());
                valIni += 1000;

                // Tempo gasto na pesquisa e numero de comparacao:
                ab.setNumComparacao(-1);
                long initTime = System.nanoTime();
                ab.pesquisa(new Item(5501), ab.getRaiz());
                long finishTime = System.nanoTime();
                System.out.println(Cores.ANSI_YELLOW + "Tempo de pesquisa: " + Cores.ANSI_GREEN
                        + (finishTime - initTime) + Cores.ANSI_RESET + " ns" // NanoSegundos (ns) = 10^-9 segs
                        + Cores.ANSI_YELLOW + "\nNumero de comparacoes: " + Cores.ANSI_GREEN + ab.getNumComparacao()
                        + Cores.ANSI_YELLOW + "\nQuantidade de elementos: " + Cores.ANSI_GREEN + ab.getNumElementos()
                        + Cores.ANSI_RESET + "\n---------------------------------------------");

            }
        }
    }

    public static void pesquisaAleatoria(ArvoreBinaria ab, int valIni) {

        System.out.println(Cores.ANSI_CYAN + "\nÁrvore ALEATÓRIA: \n" + Cores.ANSI_RESET
                + "---------------------------------------------");

        ArrayList<Integer> valores = new ArrayList<Integer>();

        for (int i = 9; i > 0; i--) {
            valores.add(valIni);
            valIni += 1000;
        }

        Random random = new Random(19700621);
        int tam = valores.size();

        for (int i = 0; i < tam; i++) {
            int indexAleatorio = random.nextInt(valores.size());
            ab.insere(new Item(valores.get(indexAleatorio)), ab.getRaiz());

            valores.remove(indexAleatorio);

            // Tempo gasto na pesquisa e numero de comparacao:
            ab.setNumComparacao(-1);
            long initTime = System.nanoTime();
            ab.pesquisa(new Item(5501), ab.getRaiz());
            long finishTime = System.nanoTime();
            System.out.println(Cores.ANSI_YELLOW + "Tempo de pesquisa: " + Cores.ANSI_GREEN + (finishTime - initTime)
                    + Cores.ANSI_RESET + " ns" // NanoSegundos (ns) = 10^-9 segs
                    + Cores.ANSI_YELLOW + "\nNumero de comparacoes: " + Cores.ANSI_GREEN + ab.getNumComparacao()
                    + Cores.ANSI_YELLOW + "\nQuantidade de elementos: " + Cores.ANSI_GREEN + ab.getNumElementos()
                    + Cores.ANSI_RESET + "\n---------------------------------------------");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor da raiz da arvore binaria: ");
        int valIni = sc.nextInt();

        ArvoreBinaria ab = new ArvoreBinaria(new Item(valIni));
        // valIni = 1000;
        pesquisaOrdenada(ab, 1000);

        ab = new ArvoreBinaria(new Item(valIni));
        pesquisaAleatoria(ab, 1000);
    }
}
