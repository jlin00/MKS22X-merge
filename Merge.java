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

  public static void insertionSort(int[] data, int start, int end){
    int length = end - start + 1;
    for (int i = 1; i < length; i++){
      int old_val = data[start + i]; //keeps track of the value that is being shifted
      int j;
      for (j = i; j > 0 && old_val < data[start + j - 1]; j--){ //loops through backwards to calculate new_ind
          data[start + j] = data[start + j - 1]; //shifts down value in that spot by one
        }
      data[start + j] = old_val; //performs the insertion
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
    /*
    if (start >= end){
      return;
    }
    */

    //insertion sort already deals with cases where start >= end
    if (end - start < 60){
      insertionSort(data,start,end);
      return;
    }

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

  public static void main(String[] args){
    //testing insertion insertionSort
    //int[] array = new int[]{2,3,1,0,3,4,1,8,7,9};
    //insertionSort(array, 8, 6);
    //System.out.println(Arrays.toString(array)); //should print {2,3,0,1,1,3,4,8,7,9}

    System.out.println("Size\t\tMax Value\tmerge /builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Merge.mergesort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
