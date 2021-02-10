package task2025;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Алгоритмы-числа
*/

public class Solution {


    public static long[] getNumbers(long N) {
        ArrayList<Long> resArr=new ArrayList<>();
        //resArr.add((long)0);
        long nn=1;
        while (nn<N & nn<10){
            resArr.add(nn);
            nn++;
        }
        long step[][]=new long[10][21];
        for (int i=0;i<10;i++){
            for (int j=0;j<21;j++){
                long s=1;
                for(int a=0;a<j;a++){
                    s=s*i;
                }
                step[i][j]=s;
                //step[i][j]=(long)Math.pow(i,j);
            }
        }
long cc=0;
long cc2=0;
        while (nn<N){
            ArrayList<Long> numb=new ArrayList<>();
            long m=nn;
            while (m>0){
                numb.add(m%10);
                m=m/10;
            }
            int size=numb.size();
            boolean tt=true;
            for(long i:numb){
                if(step[(int)i][size]>nn){tt=false;}
            }
            if(step[(int)(long)numb.get(0)][size]<nn & tt){
                long sum=0;
                for(long i:numb)sum+=step[(int)i][size];
                if(sum==nn)resArr.add(sum);
                nn+=1;
                cc++;
            }else{
                cc2++;
                nn+=10-numb.get(0);
            }

        }
        System.out.println(cc);
        System.out.println(cc2);
        System.out.println("итого: "+(cc+cc2));
        long[] result =new long[resArr.size()];
        for(int i=0;i<resArr.size();i++)result[i]=resArr.get(i);
        return result;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }

}
