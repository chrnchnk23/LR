package discret.lab4;

public class Main {
    public static void main(String[] args) {
        final int V = 9;
        int[][] arrAdj = {{0, 1, 0, 0, 0, 0, 1, 0, 0},
                          {1, 0, 1, 1, 0, 0, 0, 0, 0},
                          {0, 1, 0, 0, 1, 0, 0, 0, 0},
                          {0, 1, 0, 0, 1, 1, 0, 0, 0},
                          {0, 0, 1, 1, 0, 1, 1, 1, 0},
                          {0, 0, 0, 1, 1, 0, 0, 1, 0},
                          {1, 0, 0, 0, 1, 0, 0, 0, 1},
                          {0, 0, 0, 0, 1, 1, 0, 0, 1},
                          {0, 0, 0, 0, 0, 0, 1, 1, 0}};
        int[] D = new int[V]; //Степени вершин
        int[] Ver = new int[V]; //Массив вершин
        int[] arrColor = new int[V]; //Массив окраски вершин
        for (int i = 0; i < V; i++) { //Иницилизация врешин
            Ver[i] = i;
            arrColor[i] = 0;
        }
        //Рассчёт степеней вершин
        for (int i = 0; i < V; i++) {
            int counter = 0;
            for (int j = 0; j < V; j++) {
                counter += arrAdj[i][j];
            }
            D[i] = counter;
        }
        //Сортировка вершин по убыванию степеней
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V - i - 1; j++) {
                if (D[j] < D[j + 1]) {
                    int temp = D[j];
                    D[j] = D[j + 1];
                    D[j + 1] = temp;
                    temp = Ver[j];
                    Ver[j] = Ver[j + 1];
                    Ver[j + 1] = temp;
                    temp = arrColor[j];
                    arrColor[j] = arrColor[j + 1];
                    arrColor[j + 1] = temp;
                }
            }
        }
        //Окраска вершин
        int counter = 1;
        int currColor = 1;
        arrColor[0] = currColor;
        do {
            for (int i = 0; i < V; i++) {
                if (arrColor[i] != 0) {
                    for (int j = 0; j < V; j++) {
                        if (arrColor[j] == 0) {

                            if (arrAdj[Ver[i]][Ver[j]] == 0 && (sameColorNotAdjacent(arrAdj,
                                    arrColor, currColor, Ver, j, V))) {
                                counter++;
                                arrColor[j] = currColor;
                            } else if (sameColorNotAdjacent(arrAdj, arrColor, currColor, Ver, j,
                                    V)) {
                                counter++;
                                arrColor[j] = currColor;
                            }
                        }
                    }
                }
            }
            currColor++;
        } while (counter < V);
        //Возвращаем очерёдность вершин, их цветов и порядков по возрастанию индекса
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V - i - 1; j++) {
                if (Ver[j] > Ver[j + 1]) {
                    int temp = D[j];
                    D[j] = D[j + 1];
                    D[j + 1] = temp;
                    temp = Ver[j];
                    Ver[j] = Ver[j + 1];
                    Ver[j + 1] = temp;
                    temp = arrColor[j];
                    arrColor[j] = arrColor[j + 1];
                    arrColor[j + 1] = temp;
                }
            }
        }
        System.out.print("Вершины:\t");
        outputVer(Ver, V);
        System.out.print("Цвета:\t\t");
        output(arrColor, V);
        System.out.println();
        System.out.print("Граф можно раскрасить не менее чем в " + (currColor - 1) + " цвета");
    }

    //Проверяем чтоб вершина не была смежна с вершиной того же цвета
    private static boolean sameColorNotAdjacent(int arrAdj[][], int arrColor[], int currColor,
                                                int Xd[], int i, int V) {
        for (int j = 0; j < V; j++) {
            if ((arrAdj[Xd[i]][Xd[j]] != 0) && arrColor[j] == currColor) {
                return false;
            }
        }
        return true;
    }

    private static void outputVer(int arr[], int V) {
        for (int i = 0; i < V; i++) {
            System.out.print("X" + arr[i] + "\t");
        }
        System.out.println();
    }

    private static void output(int arr[], int V) {
        for (int i = 0; i < V; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
