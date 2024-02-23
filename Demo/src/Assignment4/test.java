package Assignment4;
public class test {
    public static void main(String[] args) {
        int[] bits = {1,2,1,2,2,4};
        boolean positive = false;
        StringBuilder str = new StringBuilder();
        boolean flag = true;
        if (bits== null) {
            System.out.println("0");
        }
        else {
            for (int bit : bits) {
                if (bit == 0) {
                    if (flag) {
                        continue;
                    }
                    str.append((char) ('0' + bit));
                } else {
                    flag = false;
                    str.append((char) ('0' + bit));
                }
            }
            if (str.length() == 0) {
                System.out.println("0");
            }else{
                if (!positive) {
                    str.insert(0, '-');
                }
                String a = String.valueOf(str);
                String b = a.substring(1,a.length());
                System.out.println(b);
                System.out.println(a.substring(2,a.length()));
            }

        }
    }
}