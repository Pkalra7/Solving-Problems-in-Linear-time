package devskiller;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class HasPairWithSum {

	public static void main(String[] args) {
		
		/** Given a collection of numbers and a sum, find is a matching pair within
		 * the collection adds up to the sum. They are ordered in this collection.
		 * Ex: Collection = [1,2,3,9], Sum = 8...In this case there is no such pair
		 * Ex: Collection = [1,2,4,4], Sum = 8 ...Yes, sum does exist!
		 */
		
		/* Assumptions/ Questions to keep in mind: 
		 * How are these numbers given? In memory? In an array? etc.?
		 * Are they ordered?
		 * Repeating elements? Can't repeat the same element at the same index but you can 
		 * use the same number occuring at a separate index
		 * What kind of numbers? integers? floating points? negatives?
		 */
		
		/*Solution 1: Brute force with two for loops. comparing each combination 
		 * of pairs to test whether they result in sum. Obviously big O(n^2) in this case.
		 * Not optimal.Time consuming. 
		 * 
		 */
		
		/*Solution 2: Traverse the collection once, and at each iteration subtract
		 * it from the sum, and try to find the result in the remaining elements (excluding 
		 * previous indexes of the current iteration) by using binary search.
		 * Good Solution. n(log(n))...Still not the most optimal solution. 
		 * 
		 */
		
		//Implementation of solution 3 -> runs in linear time
		Scanner reader = new Scanner(System.in);
		ArrayList<Integer> col = new ArrayList<Integer>();
		System.out.println("Now enter the numbers you would like stored in this collection "
				+ "in ascending order. Type 'done' to end inputting: ");
		int num;
		while(reader.hasNextInt()) {
			num = reader.nextInt();
			col.add(num);
		}
		reader.next();
		System.out.println("Enter the sum you would like to check for: ");
		int sum = reader.nextInt();	
		int low = 0;
		int high = col.size()-1;
		
		while(!(low > high))
		{
			if(low == high){
				System.out.println("Sorry, no such pair exists");
				break;
			}
			int pairSum = col.get(low) + col.get(high);
			if(pairSum == sum){
				System.out.println("Yes, a pair of elements in this collection does add"
						+ "up to the given sum: they are " + col.get(low) + " and " + col.get(high));
				break;
			}else if(pairSum > sum) {
				high --;
			}else if(pairSum < sum) {
				low ++;
			}
		}
		
		
		/**
		 * 
		 * Now assume there is no order in the collection. They will be in random order
		 * 
		 */
		
		col.clear();

		
		System.out.println("Now enter the numbers you would like stored in this collection "
				+ "in any random order. Type 'done' to end inputting: ");
		
		while(reader.hasNextInt()) {
			num = reader.nextInt();
			col.add(num);
		}
		reader.next();
		System.out.println("Enter the sum you would like to check for: ");
		int sum2 = reader.nextInt();
		boolean exists= hasAPairWithSum(col, sum2);
		
		System.out.println("Result: " + exists);
	}
	
	/*Checks if there is a pair in an unordered collection. Used a Hashset to store compliments (sum - value) of
	 * each element in the array and attempts to find these compliments in the remaining collection. Essentially 
	 * builds a list of compliments till a value in the collection matches a value in the set of compliments
	 * Runs in linear time since a set has constant lookup time. Worse case memory use would be linear as well.
	 */
	public static boolean hasAPairWithSum(ArrayList<Integer> col, int sum) {
	
		/*Iterator iter1 = col.iterator();
		while (iter1.hasNext()) {
			System.out.println(iter1.next());
		}*/
		
		System.out.println("");
		HashSet<Integer> comp = new HashSet<Integer>();
		int i = 0;
		for(int value: col) {
			if(!comp.contains(value)) {
				comp.add(sum - value);
			}
			else {
				return true;
			}
		}
		return false;	
		
	}
			
}
