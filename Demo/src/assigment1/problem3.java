package assigment1;
import java.util.Scanner;
public class problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        String[] arr1 = str1.split(" ");
        String[] arr2 = str2.split(" ");
        int h1 = sc.nextInt();
        int m1 = sc.nextInt();
        int s1 = sc.nextInt();
        int h2 = sc.nextInt();
        int m2 = sc.nextInt();
        int s2 = sc.nextInt();
        int time1, time2, time3;
        int h3, m3, s3;
        time1 = 3600 * h1 + 60 * m1 + s1;
        time2 = 3600 * h2 + 60 * m2 + s2;
        time3 = time2 - time1;
        int u = 1;
        if (h1 < 0 | 24<=h1) {
            u = 0;
        }
        if (h2 < 0 | 24<=h2) {
            u = 0;
        }
        if (m1 < 0 | 60<=m1) {
            u = 0;
        }
        if (m2 < 0 | 60<=m2) {
            u = 0;
        }
        if (s1 < 0 | 60<=s1) {
            u = 0;
        }
        if (s2 < 0 | 60<=s2) {
            u = 0;
        }
        if (u != 1) {
            System.out.println("Not valid");
        } else {
            if (time3 < 0) {
                System.out.println("Not valid");
            } else {
                h3 = time3 / 3600;
                m3 = (time3 - h3 * 3600) / 60;
                s3 = time3 - 3600 * h3 - 60 * m3;
                if (h3 == 0) {
                    if (m3 == 0) {
                        System.out.println(s3 + "s");
                    } else {
                        if (s3 == 0) {
                            System.out.println(m3 + "m");
                        } else {
                            System.out.println(m3 + "m" + s3 + "s");
                        }
                    }
                } else if (m3 == 0) {
                    if (s3 == 0) {
                        System.out.println(h3 + "h");
                    } else {
                        System.out.println(h3 + "h" + s3 + "s");
                    }
                } else {
                    if (s3 == 0) {
                        System.out.println(h3 + "h" + m3 + "m");
                    } else {
                        System.out.println(h3 + "h" + m3 + "m" + s3 + "s");
                    }
                }
            }
        }
    }
}

