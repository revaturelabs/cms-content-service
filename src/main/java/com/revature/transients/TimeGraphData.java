package com.revature.transients;

import java.util.Set;

public class TimeGraphData 
{
	private Set<Long> returnedLongs;
	private int numContents;
	public TimeGraphData() {
		super();
	}
	public TimeGraphData(Set<Long> returnedLongs, int numContents) {
		super();
		this.returnedLongs = returnedLongs;
		this.numContents = numContents;
	}
	public Set<Long> getReturnedLongs() {
		return returnedLongs;
	}
	public void setReturnedLongs(Set<Long> returnedLongs) {
		this.returnedLongs = returnedLongs;
	}
	public int getNumContents() {
		return numContents;
	}
	public void setNumContents(int numContents) {
		this.numContents = numContents;
	}
	
	@Override
	public String toString() {
		return "TimeGraphData [returnedLongs=" + returnedLongs + ", numContents=" + numContents + "]";
	}
	
	
	
	
}
