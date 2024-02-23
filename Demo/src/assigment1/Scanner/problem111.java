package assigment1.Scanner;
import java.util.Scanner;

public class problem111 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long m = sc.nextLong();
        float T = sc.nextFloat();
        String s = sc.next();
        String str1="Y";
        String str2="N";
        int a;
        int b;
        int c;
        int i=0;
        while (m != 0) {
            m = m / 10;
            i = i + 1;
        }if (i == 8) {
            a=1;
        } else {
            a=0;
        }if (T>35.0&&T<45.0) {
            b=1;
        } else{
            b=0;
        }if (s.equals(str1) | s.equals(str2)){
            c=1;
        } else {
            c=0;
        }if (a==1){if(b==1){
                if(c==1){
                    System.out.println("Submit successfully");}
                else{
                    System.out.println("Error in Nucleic Acid PCR test");}
        } else{if(c==1){
                    System.out.println("Error in Temperature");
                }else{
                    System.out.println("Error in Temperature and Nucleic Acid PCR test");}
            }} else{
            if(b==1){if(c==1){
                    System.out.println("Error in Student ID");}
            else{System.out.println("Error in Student ID and Nucleic Acid PCR test");}
            } else{if(c==1){System.out.println("Error in Student ID and Temperature");
                }else {System.out.println("Error in Student ID and Temperature and Nucleic Acid PCR test");
            }}
        }
    }
}

