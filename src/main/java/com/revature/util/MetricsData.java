package com.revature.util;

public class MetricsData {
	private int codeCount;
	private int docuementCount;
	private int pptCount;
	private int numDiffModsCount;
	private double avgResources;
	private TimeGraphData timeGraphData;
	public MetricsData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MetricsData(int codeCount, int docuementCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData) {
		super();
		this.codeCount = codeCount;
		this.docuementCount = docuementCount;
		this.pptCount = pptCount;
		this.numDiffModsCount = numDiffModsCount;
		this.avgResources = avgResources;
		this.timeGraphData = timeGraphData;
	}
	public int getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}
	public int getDocuementCount() {
		return docuementCount;
	}
	public void setDocuementCount(int docuementCount) {
		this.docuementCount = docuementCount;
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
		return "MetricsData [codeCount=" + codeCount + ", docuementCount=" + docuementCount + ", pptCount=" + pptCount
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
		result = prime * result + docuementCount;
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
		if (docuementCount != other.docuementCount)
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
