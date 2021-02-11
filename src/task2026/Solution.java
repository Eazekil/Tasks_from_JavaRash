package task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 0, 1},
                {1, 0, 0, 0}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 1, 0}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть X");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть X");
    }

    public static boolean right(byte[][] a, int i, int j){
        if(j<a.length){
            return (a[i][j]==0);
        }else return true;
    }
    public static boolean bottom(byte[][] a, int i, int j){
        if(i<a.length){
            return a[i][j]==0;
        }else return true;
    }

    public static int getRectangleCount(byte[][] a) {
        int count=0;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                if(a[i][j]==1){
                    if(right(a,i,j+1) & bottom(a,i+1,j)){
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
