import java.util.*;

public class Merge{
  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[] data){
    int[] temp = new int[data.length]; //copy over into a temporary array for optimized merge sort
    for (int i = 0; i < data.length; i++){
      temp[i] = data[i];
    }
    //mergesortH(data, 0, data.length - 1); //unoptimized version
    mergesortO(data, temp, 0, data.length - 1); //optimized version
  }

  public static void insertionSort(int[] data){
   for (int i = 1; i < data.length; i++){
     int old_val = data[i]; //keeps track of the value that is being shifted
     int j;
     for (j = i; j > 0 && old_val < data[j - 1]; j--){ //loops through backwards to calculate new_ind
         data[j] = data[j - 1]; //shifts down value in that spot by one
       }
     data[j] = old_val; //performs the insertion
   }
   }

  //helper function for merge sort
  private static void mergesortH(int[] data, int start, int end){
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

  //optimized merge sort helper function
  private static void mergesortO(int[] data, int[] temp, int start, int end){
    if (start < end){
      int mid = (end - start) / 2 + start; //middle of the data

      mergesortO(temp, data, start, mid); //sort left side, flip temp and data
      mergesortO(temp, data, mid + 1, end); //sort right side, flip temp and data

      int l = start; //start of left side
      int r = mid + 1; //start of right side
      int i = start; //keeps track of merge index

      while (l <= mid && r <= end){ //loop through length of left and right sides
        if (temp[l] <= temp[r]){ //if left is smaller than right
          data[i] = temp[l]; //add to merged data
          l++; //increase left index
        }
        else{
          data[i] = temp[r]; //add to merged data
          r++; //increase right index
        }
        i++; //increase merge index
      }

      while (l <= mid){ //if there are still numbers left in left side
        data[i] = temp[l]; //add to merged data
        i++; //increase merge index
        l++; //increase left index
      }

      while (r <= end){ //if there are still numbers left in right side
        data[i] = temp[r]; //add to merged data
        i++; //increase merge index
        r++; //increase right index
      }
    }
  }

  public static void main(String[] args){
    int[] testmerge;
    int[] compare;

    //default values
    int testcases = 10;
    int max_size = 1000000;
    int max_el = 3000;

    if (args.length >= 3){
      testcases = Integer.parseInt(args[0]);
      max_size = Integer.parseInt(args[1]);
      max_el = Integer.parseInt(args[2]);
    }

    System.out.println("Number of testcases: " + testcases);
    System.out.println("Maximum size of array: " + max_size);
    System.out.println("Maximum value of elements in array: " + max_el);
    System.out.println();

    for (int i = 0; i < testcases; i++){
      int size = (int)(Math.abs(Math.random() * max_size));
      testmerge = new int[size];
      compare = new int[size];
      for (int j = 0; j < size; j++){
        int el = (int)(Math.random() * max_el);
        testmerge[j] = el;
        compare[j] = el;
      }

      mergesort(testmerge);
      Arrays.sort(compare);

      if (!Arrays.equals(testmerge, compare)){
        System.out.println("Failed tests.");
        System.exit(0);
      }

    }
    System.out.println("Passed all " + testcases + " test cases.");
  }
}
