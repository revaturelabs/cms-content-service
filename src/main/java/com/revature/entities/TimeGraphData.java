package com.revature.entities;

import java.util.Set;


public class TimeGraphData 
{
	private Set<Long> returnedLongs;
	private int numContents;
	public TimeGraphData() {
		super();
		// TODO Auto-generated constructor stub
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
