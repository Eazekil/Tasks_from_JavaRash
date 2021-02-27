package TaskYandex1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SolutionForTest {
    public static int n=0;
    public static int m=0;
    public static int startN=0;
    public static int startM=0;
    public static int finishN=0;
    public static int finishM=0;
    public static int[][] matrixLet = null;
    public static int[][] matrixNumber = null;

    public static void main(String[] args) {


        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            String[] sizeMatrix = reader.readLine().split(" ");
            n=Integer.parseInt(sizeMatrix[0]);
            m=Integer.parseInt(sizeMatrix[1]);

            matrixLet = new int[n][m];

            matrixNumber = new int[n][m];

            String[] points = reader.readLine().split(" ");
            startN = Integer.parseInt(points[0]);
            startM = Integer.parseInt(points[1]);
            finishN = Integer.parseInt(points[2]);
            finishM = Integer.parseInt(points[3]);

            for(int i=0;i<n;i++){
                String[] stringMatrixLet = reader.readLine().split(" ");
                for(int j=0;j<m;j++){
                    matrixLet[i][j]= Integer.parseInt(stringMatrixLet[j]);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        Graph graph = new Graph();
        graph.addVertex(startN+","+startM);

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i ==startN && j==startM){

                }else{
                    if(matrixLet[i][j] ==0){
                        graph.addVertex(i+","+j);
                    }
                }

            }
        }



        int numberForMatrix = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                matrixNumber[i][j] = numberForMatrix;
                numberForMatrix++;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                /*if(matrixLet[i][j] == 0){
                    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

                }*/
                //проверяем справа
                if(j+1 == m){
                    if(matrixLet[i][0] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i][0], 1);
                        //graph.addEdge(matrixNumber[i][0], matrixNumber[i][j], 1);
                    }
                }else {
                    if(matrixLet[i][j+1] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i][j+1], 1);
                        //graph.addEdge(matrixNumber[i][j+1], matrixNumber[i][j], 1);
                    }
                }

                //проверяем слева
                if(j-1 == -1){
                    if(matrixLet[i][m-1] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i][m-1], 1);
                        //graph.addEdge(matrixNumber[i][m-1], matrixNumber[i][j], 1);
                    }
                }else {
                    if(matrixLet[i][j-1] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i][j-1], 1);
                        //graph.addEdge(matrixNumber[i][j-1], matrixNumber[i][j], 1);
                    }
                }

                //проверяем снизу
                if(i+1 == n){
                    if(matrixLet[0][j] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[0][j], 1);
                        //graph.addEdge(matrixNumber[0][j], matrixNumber[i][j], 1);
                    }
                }else {
                    if(matrixLet[i+1][j] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i+1][j], 1);
                        //graph.addEdge(matrixNumber[i+1][j], matrixNumber[i][j], 1);
                    }
                }

                //проверяем сверху
                if(i-1 == -1){
                    if(matrixLet[n-1][j] == 0 ){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[n-1][j], 1);
                        //graph.addEdge(matrixNumber[n-1][j], matrixNumber[i][j], 1);
                        System.out.println(matrixNumber[n-1][j]+"*************");
                        System.out.println("i= "+i+"      j= "+j);
                    }
                }else {
                    if(matrixLet[i-1][j] == 0){
                        graph.addEdge(matrixNumber[i][j], matrixNumber[i-1][j], 1);
                        //raph.addEdge(matrixNumber[i-1][j], matrixNumber[i][j], 1);
                    }
                }

            }
        }


        System.out.println("Элементы имеют кратчайшие пути из переданной точки: ");
        graph.path();
        graph.clean();


    }
}


class Vertex {
    private String label;
    private boolean isInTree;

    public Vertex(String label) {
        this.label = label;
        this.isInTree = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}

class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины
    private int distance; // текущая дистанция от начальной вершины
    private List<Integer> parentVertices; // текущий родитель вершины

    public Path(int distance) {
        this.distance = distance;
        this.parentVertices = new ArrayList<>();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Integer> getParentVertices() {
        return parentVertices;
    }

    public void setParentVertices(List<Integer> parentVertices) {
        this.parentVertices = parentVertices;
    }
}

class Graph {
    private final int MAX_VERTS = SolutionForTest.n*SolutionForTest.m;// максимальное количество вершин
    private final int INFINITY = 100000000; // это число у нас будет служить в качестве "бесконечности"
    private Vertex vertexList[]; // список вершин
    private int relationMatrix[][]; // матрица связей вершин
    private int countOfVertices; // текущее количество вершин
    private int countOfVertexInTree; // количество рассмотренных вершин в дереве
    private List<Path> shortestPaths; // список данных кратчайших путей
    private int currentVertex; // текущая вершина
    private int startToCurrent; //расстояние до currentVertex

    public Graph() {
        vertexList = new Vertex[MAX_VERTS]; // матрица смежности
        relationMatrix = new int[MAX_VERTS][MAX_VERTS];
        countOfVertices = 0;
        countOfVertexInTree = 0;
        for (int i = 0; i < MAX_VERTS; i++) {// матрица смежности заполняется
            for (int k = 0; k < MAX_VERTS; k++) { // бесконечными расстояниями
                relationMatrix[i][k] = INFINITY; // задания значений по умолчанию
                shortestPaths = new ArrayList<>();// задается пустым
            }
        }
    }

    public void addVertex(String lab) {// задание новых вершин
        vertexList[countOfVertices++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        relationMatrix[start][end] = weight; // задание ребер между вершинами, с весом между ними
    }

    public void path() { // выбор кратчайшего пути
        //  задание данных для стартовой вершины
        int startTree = 0; // стартуем с вершины 0
        vertexList[startTree].setInTree(true); // включение в состав дерева первого элемента
        countOfVertexInTree = 1;

        // заполнение коротких путей для вершин смежных с стартовой
        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[startTree][i];
            Path path = new Path(tempDist);
            path.getParentVertices().add(0);// первым родительским элементом, будет всегда стартовая вершина
            shortestPaths.add(path);
        }
        // пока все вершины не окажутся в дереве
        while (countOfVertexInTree < countOfVertices) { // выполняем, пока количество вершин в дереве не сравняется с общим количеством вершин
            int indexMin = getMin();//получаем индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
            int minDist = shortestPaths.get(indexMin).getDistance();// минимальная дистанция вершины, из тек которые ещё не в дереве

            if (minDist == INFINITY) {
                System.out.println("В графе пристувствуют недостижимые вершины");
                break;// в случае если остались непройденными только недостижимые вершины, мы выходим из цикла
            } else {
                currentVertex = indexMin; // переводим указатель currentVert к текущей вершине
                startToCurrent = shortestPaths.get(indexMin).getDistance();// задаем дистанцию к текущей вершине
            }

            vertexList[currentVertex].setInTree(true);  //включение текущей вершины в дерево
            countOfVertexInTree++; // увеличиваем счетчик вершин в дереве
            updateShortestPaths(); // обновление списка кратчайших путей
        }

        displayPaths(); // выводим в консоль результаты
    }

    public void clean() { // очиска дерева
        countOfVertexInTree = 0;
        for (int i = 0; i < countOfVertices; i++) {
            vertexList[i].setInTree(false);
        }
    }

    private int getMin() {
        int minDist = INFINITY; // за точку старта взята "бесконечная" длина
        int indexMin = 0;
        for (int i = 1; i < countOfVertices; i++) {// для каждой вершины
            if (!vertexList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) { // если вершина ещё не ве дереве и её растояние меньше старого минимума
                minDist = shortestPaths.get(i).getDistance(); // задаётся новый минимум
                indexMin = i; // обновление индекса вершины содержащую минимаьную дистанцию
            }
        }
        return indexMin; //возвращает индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
    }

    private void updateShortestPaths() {
        int vertexIndex = 1; // стартовая вершина пропускается
        while (vertexIndex < countOfVertices) { // перебор столбцов

            if (vertexList[vertexIndex].isInTree()) { // если вершина column уже включена в дерево, она пропускается
                vertexIndex++;
                continue;
            }
            // вычисление расстояния для одного элемента sPath
            // получение ребра от currentVert к column
            int currentToFringe = relationMatrix[currentVertex][vertexIndex];
            // суммирование всех расстояний
            int startToFringe = startToCurrent + currentToFringe;
            // определение расстояния текущего элемента vertexIndex
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();

            // сравнение расстояния через currentVertex с текущим расстоянием в вершине с индексом vertexIndex
            if (startToFringe < shortPathDistance) {// если меньше, то у вершины под индексом vertexIndex будет задан новый кратчайший путь
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());//создаём копию списка родителей вершины currentVert
                newParents.add(currentVertex);// задаём в него и currentVertex как предыдущий
                shortestPaths.get(vertexIndex).setParentVertices(newParents); // соохраняем новый маршут
                shortestPaths.get(vertexIndex).setDistance(startToFringe); // соохраняем новую дистанцию
            }
            vertexIndex++;
        }
    }

    private void displayPaths() { // метод для вывода кратчайших путей на экран
        for (int i = 0; i < countOfVertices; i++) {
            System.out.print(vertexList[i].getLabel() + " = ");
            if (shortestPaths.get(i).getDistance() == INFINITY) {
                System.out.println("0");
            } else {
                String result = shortestPaths.get(i).getDistance() + " (";
                List<Integer> parents = shortestPaths.get(i).getParentVertices();
                for (int j = 0; j < parents.size(); j++) {
                    result += vertexList[parents.get(j)].getLabel() + " -> ";
                }
                System.out.println(result + vertexList[i].getLabel() + ")");
            }
        }
    }
}