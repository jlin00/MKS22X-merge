public class Merge{
  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[] data){
    mergesortH(data, 0, data.length - 1);
  }

  //helper function for merge sort
  public static void mergesortH(int[] data, int start, int end){
    if (start < end){
      int mid = (end - start) / 2 + 1;
      int[] left = new int[mid]; //copy left side into new array
      //System.out.println("LEFT");
      for (int i = 0; i < left.length; i++){
        left[i] = data[i]; //copy array
        //System.out.println(left[i]);
      }
      //System.out.println("RIGHT");
      int[] right = new int[end - mid + 1]; //copy right side into new array
      for (int i = 0; i < right.length; i++){
        right[i] = data[i + mid];
        //System.out.println(right[i]);
      }

      //mergesortH(left, 0, left.length - 1); //merge sort left side
      //mergesortH(right, 0, right.length - 1); //merge sort right side
    }


  }

  public static void main(String[] args){
    int[] data = new int[]{0,1,2,3,4,5,6};
    mergesort(data);

    data = new int[]{3,0,1,5,3,2,1,9,4};
    mergesort(data);
  }
}
