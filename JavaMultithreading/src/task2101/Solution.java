package task2101;

/* 
Определяем адрес сети
*/

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] bytes=new byte[4];
        for(int i=0;i<bytes.length;i++){
            bytes[i]= (byte)(ip[i] & mask[i]);
        }
        return bytes;
    }

    public static void print(byte[] bytes) {
        for(byte b:bytes){
            int res=0;
            if(b<0){
                res=(128+b)+128;
            }else{
                res=b;
            }
            String s = String.format("%08d", Integer.parseInt(Integer.toBinaryString(res)));
            System.out.print(s+" ");
        }
        System.out.println();
    }
}
