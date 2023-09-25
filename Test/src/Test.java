public class Test {
	public static int R = 3, C = 3;
    
    // Function that print matrix in reverse spiral form.
    public static void ReversespiralPrint(int m, int n,
                                             int a[][], int x, int y)
    {
        // Large array to initialize it
        // with elements of matrix
        long b[] = new long[100];
           
        /* k - starting row index
        l - starting column index*/
        int i, k = 0, l = 0;
           
        // Counter for single dimension array
        //in which elements will be stored
        int z = 0;
           
        // Total elements in matrix
        int size = m * n;
       
        while (k < m && l < n)
        {
            // Variable to store value of matrix.
            int val;
               
            /* Print the first row from the remaining 
            rows */
            for (i = l; i < n; ++i)
            {
                  
                val = a[k + x][i + y];
                b[z] = val;
                ++z;
            }
            k++;
       
            /* Print the last column from the remaining
            columns */
            for (i = k; i < m; ++i)
            {
                  
                val = a[i + x][n-1 + y];
                b[z] = val;
                ++z;
            }
            n--;
       
            /* Print the last row from the remaining 
            rows */
            if ( k < m)
            {
                for (i = n-1; i >= l; --i)
                {
                      
                    val = a[m-1 + x][i + y];
                    b[z] = val;
                    ++z;
                }
                m--;
            }
       
            /* Print the first column from the remaining 
            columns */
            if (l < n)
            {
                for (i = m-1; i >= k; --i)
                {
                      
                    val = a[i + x][l + y];
                    b[z] = val;
                    ++z;
                }
                l++;
            }
        }
          
        for (int xx = size-1 ; xx>=0 ; --xx)
        {
            System.out.print(b[xx]+" ");
        }
    }    
      
    /* Driver program to test above function */
    public static void main(String[] args) 
    {
        int a[][] = { {1, 2, 3, 4},
                      {5, 6, 7, 8},
                      {9, 10, 11, 12},
                      {13, 14, 15, 16}
                      };          
        ReversespiralPrint(R, C, a, 0, 0);
         
    }
}
