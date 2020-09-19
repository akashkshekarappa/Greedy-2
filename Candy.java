// Time Complexity : O(3n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

/**Initially distribute 1 candies for each 
 * Traverse first from left to right, if current rating is greater than previous rating, then we add 1 to previous rating.
 * Now, we Traverse from Right to Left, if current rating is greater than next rating, then we add 1 to next rating
 * The final resultant array will be having max of both cases
 * At the end we add all the values in resultant array
 * **/
import java.util.Arrays;

public class Candy {
	public int candy(int[] ratings) {
		if(ratings == null || ratings.length == 0)
			return 0;

		int[] result = new int[ratings.length];
		Arrays.fill(result, 1); //initially we distribute 1 candies for each

		//case1: Traverse from left to right
		for(int i=1; i<ratings.length; i++){
			if(ratings[i] > ratings[i-1]){  // if current rating is greater than previous rating, then we add 1 to previous rating
				result[i] = result[i-1] + 1;
			}
		}

		//case2: Traverse from Right to Left
		for(int i=ratings.length-2; i>=0; i--){
			if(ratings[i] > ratings[i+1]){  // if current rating is greater than next rating, then we add 1 to next rating
				// the final resultant array will be having max of both cases
				result[i] = Math.max(result[i], result[i+1] +1);
			}
		}

		//now add all the values in resultant array
		int sum = 0;
		for(int i=0; i<result.length; i++)
			sum+= result[i];

		return sum;
	}
}
