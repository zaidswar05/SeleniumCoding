public class Array {
    public static void main(String[] args) {
        int[] arr = {2, 7, 9, 11, 15};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                if(arr[i]+arr[j]== arr[k]){
                    System.out.println("");
                }

                }
            }
        }
    }
}