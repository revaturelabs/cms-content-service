package com.revature.util;
import java.util.List;


/**
 * TimeGraphData - A JSON-convertible data storage object for the express
 * purpose of storing data needed for the Time Graph, as seen in the Reports page.
 * 
 * @author 
 * @version 1.0
 */
public class TimeGraphData 
{
	/** 
	 * returnedLongs - Represents a set of timestamps. It is designed in expectation that there will be no duplicate millisecond times,
	 * which, while possible, will not be accounted for. 
	 */
	private List<Long> returnedLongs;
	
	/**
	 * numContents - Represents the number of timestamped records that were created before the date that is being tracked from.
	 */
	private int numContents;
	
	/**
	 * Default Constructor.
	 */
	public TimeGraphData() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param returnedLongs A Set of longs representing timestamps.
	 * @param numContents An int representing a number of other records in the DB.
	 */
	public TimeGraphData(List<Long> returnedLongs, int numContents) {
		super();
		this.returnedLongs = returnedLongs;
		this.numContents = numContents;
	}
	
	/**
	 * @return returnedLongs, the internal Set of Longs.
	 */
	public List<Long> getReturnedLongs() {
		return returnedLongs;
	}
	
	/**
	 * Sets the internal returnedLongs value.
	 * @param returnedDates - the new value of returnedLongs
	 */
	public void setReturnedLongs(List<Long> returnedDates) {
		this.returnedLongs = returnedDates;
	}
	
	/**
	 * @return numContents, the internal int representing excluded records.
	 */
	public int getNumContents() {
		return numContents;
	}
	
	/**
	 * Sets the internal numContents value.
	 * @param numContents - the new value of numContents.
	 */
	public void setNumContents(int numContents) {
		this.numContents = numContents;
	}
	
	@Override
	public String toString() {
		return "TimeGraphData [returnedLongs=" + returnedLongs + ", numContents=" + numContents + "]";
	}
	
	
	
	
}
