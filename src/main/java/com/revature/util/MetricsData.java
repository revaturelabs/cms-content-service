package com.revature.util;

public class MetricsData {
	private int codeCount;
	private int documentCount;
	private int pptCount;
	private int numDiffModsCount;
	private double avgResources;
	private TimeGraphData timeGraphData;
	public MetricsData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MetricsData(int codeCount, int documentCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData) {
		super();
		this.codeCount = codeCount;
		System.out.println("codeCount : " + codeCount);
		this.documentCount = documentCount;
		System.out.println("doc count : " + documentCount);
		this.pptCount = pptCount;
		System.out.println("pptCount: " + pptCount);
		this.numDiffModsCount = numDiffModsCount;
		System.out.println("num mods: " + numDiffModsCount);
		this.avgResources = avgResources;
		System.out.println(avgResources + "avgResources");
		this.timeGraphData = timeGraphData;
		System.out.println("timeGraphData: " + timeGraphData);
	}
	public int getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}
	public int getDocumentCount() {
		return documentCount;
	}
	public void setDocumentCount(int docuementCount) {
		this.documentCount = docuementCount;
	}
	public int getPptCount() {
		return pptCount;
	}
	public void setPptCount(int pptCount) {
		this.pptCount = pptCount;
	}
	public int getNumDiffModsCount() {
		return numDiffModsCount;
	}
	public void setNumDiffModsCount(int numDiffModsCount) {
		this.numDiffModsCount = numDiffModsCount;
	}
	public double getAvgResources() {
		return avgResources;
	}
	public void setAvgResources(double avgResources) {
		this.avgResources = avgResources;
	}
	public TimeGraphData getTimeGraphData() {
		return timeGraphData;
	}
	public void setTimeGraphData(TimeGraphData timeGraphData) {
		this.timeGraphData = timeGraphData;
	}
	@Override
	public String toString() {
		return "MetricsData [codeCount=" + codeCount + ", docuementCount=" + documentCount + ", pptCount=" + pptCount
				+ ", numDiffModsCount=" + numDiffModsCount + ", avgResources=" + avgResources + ", timeGraphData="
				+ timeGraphData + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avgResources);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + codeCount;
		result = prime * result + documentCount;
		result = prime * result + numDiffModsCount;
		result = prime * result + pptCount;
		result = prime * result + ((timeGraphData == null) ? 0 : timeGraphData.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricsData other = (MetricsData) obj;
		if (Double.doubleToLongBits(avgResources) != Double.doubleToLongBits(other.avgResources))
			return false;
		if (codeCount != other.codeCount)
			return false;
		if (documentCount != other.documentCount)
			return false;
		if (numDiffModsCount != other.numDiffModsCount)
			return false;
		if (pptCount != other.pptCount)
			return false;
		if (timeGraphData == null) {
			if (other.timeGraphData != null)
				return false;
		} else if (!timeGraphData.equals(other.timeGraphData))
			return false;
		return true;
	}
	
	
	
	
}
