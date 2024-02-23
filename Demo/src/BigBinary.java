public class BigBinary {
    private int[] Bits;
    private int[] validBits;
    private int[] reverseBits;
    private boolean Positive;

    public BigBinary(int[] bits, boolean positive) {
        this.Positive = positive;
        this.Bits = new int[bits.length];
        for (int i = 0; i < bits.length; i++) {
            Bits[i] = bits[i];
        }
        this.validBits = ValidBits(Bits);
        this.reverseBits = ReverseBits(this.validBits);
    }

    private static int[] ValidBits(int[] Bits) {
        int[] B;
        int i;
        for ( i = 0; i < Bits.length-1; i++) {
            if (Bits[i] > 0) break;
        }
        B = new int[Bits.length - i];
        for (int j = 0; j < B.length; j++) {
            B[j] = Bits[i+j];
        }
        return B;
    }

    private static int[] ReverseBits(int[] validBits) {
        int[] reverseBits = new int[validBits.length];
        for (int i = 0; i < validBits.length; i++) {
            reverseBits[i] = validBits[validBits.length - 1 - i];
        }
        return reverseBits;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < validBits.length ; i++ ){
            str.append(validBits[i]);
        }
        if(String.valueOf(str).equals("0"))  return String.valueOf(str);
        else{
            if (!Positive) {
                str.insert(0,"-");
            }
            return String.valueOf(str);
        }
    }

    private static boolean compare (BigBinary A, BigBinary B){ //return true if A is larger than B
        if( A.validBits.length > B.validBits.length )  return true;
        else if ( A.validBits.length == B.validBits.length ) {
            for( int i = 0 ; i < A.validBits.length ; i++ ){
                if( A.validBits[i] > B.validBits[i] ){
                    return true;
                }
            }
        }
        return false;
    }

    private static BigBinary addMethod (BigBinary A , BigBinary B ){ // A and B have same sign
        BigBinary finalResult;
        boolean isPositive = A.Positive;
        int[] b1, b2 ;
        // ensure b1.length is longer than b2.length
        if( A.validBits.length >= B.validBits.length ){
            b1 = A.reverseBits;
            b2 = B.reverseBits;
        }else{
            b1 = B.reverseBits;
            b2 = A.reverseBits;
        }
        // add 0 to the shorter one
        int[] b3 = new int[b1.length];
        for(int i = 0 ; i < b2.length ; i++){
            b3[i] = b2[i];
        }
        for( int i = b2.length ; i < b1.length ; i++){
            b3[i] = 0;
        }
        //calculate the result
        int[] result = new int[b1.length + b2.length];
        int carry = 0;
        for( int i = 0 ; i < b1.length ; i++ ){
            if ( b1[i] + b3[i] + carry == 2 ) {
                carry = 1 ;
                result[i] = 0 ;
            } else if( b1[i] + b3[i] + carry == 3 ) {
                carry = 1;
                result[i] = 1;
            } else if( b1[i] + b3[i] +carry == 1) {
                carry = 0;
                result[i] = 1;
            } else {  //b1[i] + b2[i] +carry == 1
                carry = 0;
                result[i] = 0;
            }
            if ( i == b1.length - 1 && carry == 1) {
                result [i+1] = 1;
            }
        }
        int[] resultBits = new int[result.length];
        for ( int i = 0; i < result.length; i++) { // reverse the result
            resultBits [i] =  result [result.length - 1 - i];
        }
        finalResult = new BigBinary( resultBits , isPositive);
        return finalResult ;
    }

    public static int[] reverse (int[] a){
        int[] b = new int[a.length];
        for( int i = 0 ; i < a.length ; i++ ){
            a[i] = b [ a.length-1-i ];
        }
        return a;
    }

    private static int[] addForIt( int[] a1 , int[] a2 ){
        int[] b1, b2 ;
        if( a1.length >= a2.length ){
            b1 = BigBinary.reverse(a1);
            b2 = BigBinary.reverse(a2);
        }else{
            b1 = BigBinary.reverse(a2);
            b2 = BigBinary.reverse(a1);
        }
        // add 0 to the shorter one
        int[] b3 = new int[b1.length];
        for(int i = 0 ; i < b2.length ; i++){
            b3[i] = b2[i];
        }
        for( int i = b2.length ; i < b1.length ; i++){
            b3[i] = 0;
        }
        //calculate the result
        int[] result = new int[b1.length + b2.length];
        int carry = 0;
        for( int i = 0 ; i < b1.length ; i++ ){
            if ( b1[i] + b3[i] + carry == 2 ) {
                carry = 1 ;
                result[i] = 0 ;
            } else if( b1[i] + b3[i] + carry == 3 ) {
                carry = 1;
                result[i] = 1;
            } else if( b1[i] + b3[i] +carry == 1) {
                carry = 0;
                result[i] = 1;
            } else {  //b1[i] + b2[i] +carry == 1
                carry = 0;
                result[i] = 0;
            }
            if ( i == b1.length - 1 && carry == 1) {
                result [i+1] = 1;
            }
        }
        int[] resultBits = new int[result.length];
        for ( int i = 0; i < result.length; i++) { // reverse the result
            resultBits [i] =  result [result.length - 1 - i];
        }
        return resultBits;
    }

    private static BigBinary minusMethod (BigBinary A , BigBinary B ) { //A and B have different sign
        BigBinary finalResult;
        boolean finalSign;
        int[] b1,b2;
        //ensure the bigger one is b1
        if(compare(A,B)){ //A is bigger
            b1 = A.reverseBits;
            b2 = B.reverseBits;
            finalSign = A.Positive;
        }else{ //B is bigger
            b1 = B.reverseBits ;
            b2 = A.reverseBits ;
            finalSign = B.Positive;
        }
        int[] value =  new int[b1.length];
        int t = 0;
        for(int i = 0; i < b1.length; i++) {
            int cur = b1[i] - t; // value of B1 minus carry bit
            if (i < b2.length)  cur -= b2[i];
            value[i] = ((cur+2)%2); // B1[i] may be smaller than B2[i], add cur with 2 if cur == -1
            if (cur < 0) t = 1;
            else t = 0;
        }
        int[] resultBits = ReverseBits(value);
        resultBits =  ValidBits(resultBits);
        finalResult = new BigBinary(resultBits, finalSign);
        return finalResult;

    }

    public static BigBinary add(BigBinary b1, BigBinary b2) {
        if (b1.Positive == b2.Positive)  return addMethod(b1,b2);
        else return minusMethod(b1,b2);
    }

    public static BigBinary minus(BigBinary b1, BigBinary b2) {
        BigBinary b3 = new BigBinary(b2.Bits , !b2.Positive );
        return add(b1,b3);
    }

    public BigBinary add(BigBinary bigbinary) {
        BigBinary original = new BigBinary ( this.Bits, this.Positive);
        BigBinary result = add(original, bigbinary);
        this.Bits = result.Bits;   // reset the original BigBinary
        this.validBits = ValidBits(this.Bits);
        this.reverseBits = ReverseBits(this.validBits);
        this.Positive = result.Positive;
        return result;
    }

    public BigBinary minus(BigBinary bigbinary) {
        BigBinary bigBinary2 = new BigBinary( bigbinary.Bits, !bigbinary.Positive); //change sign of bigBinary
        return add(bigBinary2);
    }

    public BigBinary multiply(BigBinary bigbinary){
        BigBinary original = new BigBinary(this.Bits, this.Positive);
        BigBinary result = multiply(original, bigbinary);
        // reset the original BigBinary
        this.Bits = result.Bits;
        this.validBits = ValidBits(this.Bits);
        this.reverseBits = ReverseBits(this.validBits);
        this.Positive = result.Positive;
        return result;
    }

    public static BigBinary multiply(BigBinary b1, BigBinary b2){
        /* BigBinary result3;
        boolean isPositive;
        // If we have the same sign, final sign is isPositive;
        if (b1.Positive == b2.Positive) {
            isPositive = true;
        }else {
            isPositive = false;
        }

        int[] B1, B2;
        //ensure that the longer one is up here
        if( b1.validBits.length >= b2.validBits.length ){
            B1 = b1.validBits;
            B2 = b2.validBits;
        }else{
            B1 = b2.validBits;
            B2 = b1.validBits;
        }

        int[] result =  new int[B1.length + B2.length];
        int[] result2 = new int[B1.length * B2.length];
        for( int i = 0 ; i < B2.length; i ++ ){
            if( B2[i]== 1){
                int j;
                for( j =0 ; j < B1.length ; j++ ){
                    result[j] = B1[j];
                }
                for(int k = 0 ; k < i ; k++ ){
                    result[k+j+1] = 0;
                }
                result2 = BigBinary.addForIt( result2 , result);
            }
        }
        result3 = new BigBinary(result2,isPositive);
        return result3;*/
        BigBinary res;
        boolean isPositive = false;
        if (b1.Positive == b2.Positive) isPositive = true;
        int[] B1 = b1.reverseBits;
        int[] B2 = b2.reverseBits;
        int[] value =  new int [ B1.length + B2.length ]; // default value is 0

        for (int i = 0; i < B1.length; i++) {
            for (int j = 0; j < B2.length; j++) {
                value[i+j] = value[i+j] + B1[i] * B2[j] ;
            }
        }
        int t = 0;
        for (int i = 0; i < value.length; i++) {
            t += value[i];
            value[i] = t % 2;
            t /= 2;
        }
        value = ValidBits(ReverseBits(value));
        res = new BigBinary(value, isPositive);
        return res;
    }
}