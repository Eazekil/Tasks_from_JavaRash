package task2027;



import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));//, "same"

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result=new ArrayList<>();
        for(String w:words){
            Word addWord=new Word(w);
            result.add(addWord);
            char[] word=w.toCharArray();
            int lengthWord=word.length;
            int[][] first=new int[30][3];
            int nFirst=0;
            int[][] last=new int[30][3];
            int nLast=0;
            for(int i=0;i<first.length;i++){
                first[i][0]=-1000;
                first[i][1]=-1000;
                last[i][0]=-1000;
                last[i][1]=-1000;
            }
            for(int i=0;i<crossword.length;i++){
                for(int j=0;j<crossword[0].length;j++){
                    if(word[0]==crossword[i][j]){
                        first[nFirst][0]=i;
                        first[nFirst][1]=j;
                        nFirst++;
                    }
                    if(word[word.length-1]==crossword[i][j]){
                        last[nLast][0]=i;
                        last[nLast][1]=j;
                        nLast++;
                    }
                }
            }

            for(int i=0;i<first.length;i++){
                for(int j=0;j<last.length;j++){
                    int iF=first[i][0];
                    int jF=first[i][1];
                    int iL=last[j][0];
                    int jL=last[j][1];

                    if(Math.abs(iF-iL)==lengthWord-1 | Math.abs(jF-jL)==lengthWord-1){
                        //System.out.println("variant  "+iF+","+jF+"  "+iL+","+jL);
                        boolean add=false;
                        if(iF==iL){
                            if(jF>jL){
                                for(int x=1;x<lengthWord-1;x++){
                                    if(crossword[iF][jF-x] ==word[x]){add=true;} else add=false;
                                }
                            }else{
                                for(int x=1;x<lengthWord-1;x++){
                                    if(crossword[iF][jF+x] ==word[x]){add=true;} else add=false;
                                }
                            }
                        }
                        if(jF==jL){
                            if(iF>iL){
                                for(int x=1;x<lengthWord-1;x++){
                                    if(crossword[iF-x][jF] ==word[x]){add=true;} else add=false;
                                }
                            }else{
                                for(int x=1;x<lengthWord-1;x++){
                                    if(crossword[iF+x][jF] ==word[x]){add=true;} else add=false;
                                }
                            }
                        }
                        if(jL-jF==iF-iL & iF-iL>0){
                            for(int x=1;x<lengthWord-1;x++){
                                if(crossword[iF-x][jF+x] ==word[x]){add=true;} else add=false;
                            }
                        }
                        if(jL-jF==iL-iF & iL-iF>0){
                            for(int x=1;x<lengthWord-1;x++){
                                if(crossword[iF+x][jF+x] ==word[x]){add=true;} else add=false;
                            }
                        }
                        if(jL-jF==iL-iF & iL-iF<0){
                            for(int x=1;x<lengthWord-1;x++){
                                if(crossword[iF-x][jF-x] ==word[x]){add=true;} else add=false;
                            }
                        }
                        if(jL-jF==iF-iL & iF-iL<0){
                            for(int x=1;x<lengthWord-1;x++){
                                if(crossword[iF+x][jF-x] ==word[x]){add=true;} else add=false;
                            }
                        }

                        if(add){
                            addWord.setStartPoint(jF,iF);
                            addWord.setEndPoint(jL,iL);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
