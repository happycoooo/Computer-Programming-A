package Assignment4;
public class testt {
    public static void main(String[] args){
        StringBuilder str = new StringBuilder();
        int[] validBits = {0,0,0,0};
        boolean Positive = false;
        for(int i = 0 ; i < validBits.length ; i++ ){
            str.append(validBits[i]);
        }
        if(String.valueOf(str).equals("0"))  System.out.println(str);
        else{
            if (!Positive) {
                str.insert(0,"-");
            }
            System.out.println(str);
    }
}
}
