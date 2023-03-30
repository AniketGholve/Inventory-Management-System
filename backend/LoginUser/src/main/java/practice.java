
//public class practice {
////	String s="abc";]
////	StringBuilder sb=new StringBuilder();
////	for(int i=s.length();i>=0;i--) {
////		sb.append(s.charAt(i));
////	}
//
//}


import java.io.*;

class practice {
   
   
// Function to rotate array
static void Rotate(int arr[], int d, int n)
{
    // Storing rotated version of array
    int temp[] = new int[n];
 
    // Keeping track of the current index
    // of temp[]
    int k = 0;
 
    // Storing the n - d elements of
    // array arr[] to the front of temp[]
    for (int i = d; i < n; i++) {
        temp[k] = arr[i];
        k++;
    }
 
    // Storing the first d elements of array arr[]
    //  into temp
    for (int i = 0; i < d; i++) {
        temp[k] = arr[i];
        k++;
    }
 
    // Copying the elements of temp[] in arr[]
    // to get the final rotated array
    for (int i = 0; i < n; i++) {
        arr[i] = temp[i];
    }
}
 
// Function to print elements of array
static void PrintTheArray(int arr[], int n)
{
    for (int i = 0; i < n; i++) {
        System.out.print(arr[i]+" ");
    }
}
    public static void main (String[] args) {
        int arr[] = { 40,13,27,87,95,40,96,71,35,79,68,2,98,3,18,93,53,57,2,81,87,42,66,90,45,20,41,30,32,18,98,72,82,76,10,28,68,57,98,54,87,66,7,84,20,25,29,72,33,30,4,20,71,69,9,16,41,50,97,24,19,46,47,52,22,56,80,89,65,29,42,51,94,1,35,65,25 };
        int N = 77;
        int d = 69;
 
        // Function calling
        Rotate(arr, d, N);
        PrintTheArray(arr, N);
    }
}

  	 	