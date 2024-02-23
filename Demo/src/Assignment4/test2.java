package Assignment4;
import java.util.Scanner;
public class test2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String b1 = input.next();
        input.nextLine();
        String b2 = input.next();
        int len1 = b1.length();
        int len2 = b2.length();
        String s1 = b1;
        String s2 = b2;
        StringBuilder sb1 = new StringBuilder();
        //先将位数较少的二进制高位补零
        if (len1 > len2) {
            for (int i = 0; i < (len1 - len2); i++) {
                sb1.append(0);
            }
            sb1.append(b2);
            s1 = b1;
            s2 = sb1.toString();
        } else if (len1 < len2) {
            for (int j = 0; j < (len2 - len1); j++) {
                sb1.append(0);
            }
            sb1.append(b1);
            s1 = sb1.toString();
            s2 = b2;
        }
        boolean compare = true ;
        for(int j = 0 ; j< s1.length(); j++) {
            if (s1.charAt(j) != s2.charAt(j)) {
                if (s1.charAt(j) < s2.charAt(j)) {
                    compare = false;
                }
                break;
            }
        }
        if(!compare){
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        //calculate minus :s1-s2,result is in sb2;
        int borrow = 0;
        StringBuilder sb2 = new StringBuilder();
        for (int z = s1.length() - 1; z >= 0; z--) {
            if ((s1.charAt(z) - 48) == 0 && (s2.charAt(z) - 48) == 0 ) {
                if(borrow==1){
                    sb2.append(1);
                }else{
                    sb2.append(0);
                }
                continue;
            }
            if (((s1.charAt(z) - 48) == 0 && (s2.charAt(z) - 48) == 1)){
                if(borrow == 1){
                    sb2.append(0);
                }else{
                    sb2.append(1);
                    borrow = 1;
                }
                continue;
            }
            if (((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 0 )) {
                if(borrow == 1){
                    sb2.append(0);
                    borrow = 0;
                }else{
                    sb2.append(1);
                }
                continue;
            }
            if ((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 1 ) {
                if(borrow == 0){
                    sb2.append(0);
                }else{
                    sb2.append(1);
                }
            }
        }
        //倒置
        sb2.reverse();
        if(!compare){
            System.out.println("-"+sb2);
        }else{
            System.out.println(sb2);
        }

        }
    }
