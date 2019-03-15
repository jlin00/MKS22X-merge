import java.util.*;

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

      mergesortH(left, 0, left.length - 1); //merge sort left side
      mergesortH(right, 0, right.length - 1); //merge sort right side

      int l = 0; //keeps track of the left index
      int r = 0; //keeps track of the right index
      int i = 0; //keeps track of the merge index

      while (l < left.length && r < right.length){
        if (left[l] <= right[r]){ //if left side is smaller than right side, add to merged array
          data[i] = left[l];
          l++; //increase left index
        }
        else{ //if right side is smaller than left side, add to merged array
          data[i] = right[r];
          r++; //increase right index
        }
        i++; //increase merge index
      }

      while (l < left.length){ //if any elements not added from left side
        data[i] = left[l];
        i++; //increase merge index
        l++; //increase left index
      }

      while (r < right.length){ //if any elements not added from right side
        data[i] = right[r];
        i++; //increase merge index
        r++; //increase right index 
      }

    }


  }

  public static void main(String[] args){
    int[] data = new int[]{6,4,-5,1,0,2,3,3,3,3};
    mergesort(data);
    System.out.println(Arrays.toString(data));

    data = new int[]{3,0,1,5,3,2,1,9,4};
    mergesort(data);
    System.out.println(Arrays.toString(data));
  }
}
