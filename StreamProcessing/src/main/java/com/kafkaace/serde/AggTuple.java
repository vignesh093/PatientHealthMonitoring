package com.kafkaace.serde;

public class AggTuple {
	public int sum;
	public int count;

	public AggTuple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AggTuple(int sum, int count) {
		super();
		this.sum = sum;
		this.count = count;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void sum(int newvalue) {
		this.sum += newvalue;
		this.count++;
	}

	@Override
	public String toString() {
		return "AggTuple [sum=" + sum + ", count=" + count + "]";
	}
	
}
