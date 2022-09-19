public class HeapSort {

    public static int numComparacao = 0;

    public static void heapsort(Item v[], int n) {
        // Usa a classe FPHeapMax da transparência 47
        Heap fpHeap = new Heap(v);
        int dir = n;
        fpHeap.constroi(); // constroi o heap
        while (dir > 1) { // ordena o vetor
            numComparacao++;
            Item x = v[1];
            v[1] = v[dir];
            v[dir] = x;
            dir--;
            fpHeap.refaz(1, dir);
        }
    }
}
