package ent;

public class Job implements Comparable<Job>{

	public Job(int id,int arrivalTime, int burstTime){
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
	
	public int id;
	public int arrivalTime;
	public int burstTime;
	public int completionTime;
	
	public int turnAroundTime() { // burst time + waiting time;
		return completionTime - arrivalTime;
	}
	
	public int waitingTime() {
		return (completionTime - arrivalTime) - burstTime;
	}

	public int compareTo(Job anotherJob) {
		int res = this.arrivalTime - anotherJob.arrivalTime;
		if(res == 0)
			return this.burstTime - anotherJob.burstTime;
		
		return res;
	}
	
	@Override
	public String toString() {
		return "Job [id="+ id +", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime + ", completionTime=" + completionTime
				+ ", turnAroundTime=" + turnAroundTime() + ", waitingTime=" + waitingTime() + "]";
	}
	
	
}
