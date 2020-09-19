import java.util.HashMap;

//Time Complexity : O(n), length of tasks
//Space Complexity : O(1)
//Did this code successfully run on Leetcode : yes
//Any problem you faced while coding this : No

/**
 * 	consider, tasks =  3A, 2B, 1C and cooling period n = 2
	A _ _ A _ _ A -> 3As with 2 slots in between(since n = 2), maxFrequency = 3
	partitions => maxFreq - 1 =>  3 - 1 => 2
	empty slots => n * partitions => 2 * 2 => 4 
	pending => tasks.length - maxFreq => 6 - 3 => 3 
 	idle => empty slots - pending => 4 - 3 => 1

	total slots needed => idle slots + tasks.length => 1 + 6 => 7

 * 	consider, tasks =  3A, 2B, 1C, 1D, 1E and cooling period n = 2
	A _ _ A _ _ A -> 3As with 2 slots in between(since n = 2), maxFrequency = 3
	partitions => maxFreq - 1 =>  3 - 1 => 2
	empty slots => n * partitions => 2 * 2 => 4 
	pending => tasks.length - maxFreq => 8 - 3 => 5
 	idle => Math.max(0, empty slots - pending) => 0

	total slots needed => idle slots + tasks.length => 0 + 8 => 8

 * 	consider, tasks =  3A, 3B, 1C and cooling period n = 2
	A B _ A B _ A B -> 3As and 3Bs with 2 slots in between(since n = 2), maxFreq = 3
	here AB should always be grouped together because of same frequency, thus maxCount = 2 i.e 2 tasks must be together
	partitions => maxFreq - 1 =>  3 - 1 => 2
	empty slots => partitions * (n - maxCount + 1) => 2 * (2- 3) => -2
	pending => tasks.length - (maxFreq * maxCount) => 7 - (3 * 2) => 1
 	idle => Math.max(0, empty slots - pending) => 0

	total slots needed => idle slots + tasks.length => 0 + 7 => 8
 * ***/

public class Task_Scheduler {
	public int leastInterval(char[] tasks, int n) {
		//edge case
		if(tasks == null || tasks.length == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<>();
		int maxCount = 0;
		int maxFreq = 0;
		// map with count of each task and keeping track of max frequency of task
		for(char task: tasks){
			int count = map.getOrDefault(task, 0) + 1;
			maxFreq = Math.max(maxFreq, count);
			map.put(task, count);
		}
		//iterate over map and if task is equal to maxFreq, then increment maxCount
		for(char c: map.keySet()){
			if(map.get(c) == maxFreq)
				maxCount++;
		}

		int partitions = maxFreq - 1;
		int empty = partitions * (n - maxCount + 1);
		int pending = tasks.length - (maxFreq * maxCount);
		int idle = Math.max(0, empty - pending);

		return idle + tasks.length;
	}
}
