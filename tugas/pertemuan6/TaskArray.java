import java.util.Arrays;
class TaskJava{
	public static void main(String []args){
		
      	int a[] = {7,7,9,10,8,5,4,9,9,6};
      	int n = a.length;
      	System.out.println("Modus : "+modus(a,n));
      	System.out.println("Median : "+median(a));
		
	}	

		static double median(int []a){
		Arrays.sort(a);
		if(a.length % 2 == 0){
			return ((double)(a[a.length/2-1] + (double)a[a.length/2]))/2;
			} else {
			return (double)a[a.length/2]+1;
			}
		}
		

	  static int modus(int a[], int n) {
      int maxValue = 0, maxCount = 0;

      for (int i = 0; i < n; ++i) {
         int count = 0;
         for (int j = 0; j < n; ++j) {
            if (a[j] == a[i])
         3   ++count;
         }

         if (count > maxCount) {
            maxCount = count;
            maxValue = a[i];
         }
      }
      return maxValue;
   }
}
	