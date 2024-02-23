package assignment3;
import java.util.ArrayList;
import java.util.Scanner;
public class RoomEscape {
    public static String getFence(String cipher) {
        StringBuilder plainText = new StringBuilder();
        int n = cipher.charAt(cipher.length() - 1) - '0';
        char[] c = new char[cipher.length() - 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < cipher.length() - 1; j += n) {
                c[j] = cipher.charAt(j);
                if (c[j] >= 'a' && c[j] <= 'z') {
                    c[j] -= 32;
                }
                plainText.append(c[j]);
            }
        }
        return plainText.toString();
    }
    public static String getCaesar(String cipher , int N) {
        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<String> objects = new ArrayList<>();
        StringBuilder result = new StringBuilder(cipher.length());
        StringBuilder result2 = new StringBuilder(cipher.length());
        for(int i = 0 ; i< cipher.length() ; i++){
            char a = cipher.charAt(i);
            if(a>=65&&a<=90|a>=97&&a<=122){
                result.append(a);
            }
            else {
                positions.add(i);
                objects.add(String.valueOf(a));
            }
        }
        String result1 = result.toString().toUpperCase();
        for(int i = 0 ; i< result1.length(); i++){
            char b = result1.charAt(i);
            char b1 = (char)((b+N-65)%26+65);
            result2.append(b1);
        }
        for(int i=0 ; i<positions.size() ; i++){
            result2.insert(positions.get(i),objects.get(i));
        }
        return String.valueOf(result2);
    }
    public static String getAnswer(String fenceCipher, String caseCipher, int caesarN, int M){
        StringBuilder cipher = new StringBuilder(caseCipher.length());
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();
        for ( int i = 0 ; i<caseCipher.length(); i++){
            char each = caseCipher.charAt(i);
            if(each>=65&&each<=90){
                cipher.append(each);
            }
            else if(each>=97&&each<=122) {
                cipher.append(each-32);
            }
            else{
                    positions.add(i);
                    objects.add(String.valueOf(each));
                }
        }
        int n = cipher.length();
        StringBuilder key;
        StringBuilder initialKey = new StringBuilder(n);
        String b; b = fenceCipher.substring(0,M);
        int number = n / M + 1;
        for( int i = 0; i<number;i++ ){
            initialKey.append(b);
        }
        key= initialKey.delete(n, number*M);
        StringBuilder result3 = new StringBuilder(n);
        char a3;
        for( int i =0 ; i< n;i++){
            char a1 =cipher.charAt(i);
            char a2 = key.charAt(i);
            int total; total=a1-65+a2-65;//位移数
            if(total<=25){
                a3 = (char) (65+total);
            }
            else{
                a3 = (char)(total%26+65);
            }
            result3.append(a3);
        }
        for(int i=0 ; i<positions.size() ; i++){
            result3.insert(positions.get(i),objects.get(i));
        }
        return String.valueOf(result3);
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String fCipher = input.nextLine();
        String cCipher = input.nextLine();
        int cN = input.nextInt();
        input.nextLine();
        int M = input.nextInt();
        String fCipher1 = getFence(fCipher);
        String cCipher1 = getCaesar(cCipher,cN);
        String finalResult = getAnswer(fCipher1,cCipher1,cN,M);
        System.out.println(finalResult);
    }
}
