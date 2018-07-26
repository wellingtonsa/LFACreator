package br.com.crateus.ufc.lfa.model;

public class ConditionDFA {

	private String read;

	public ConditionDFA(String read) {
		super();
		this.read = read;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "ConditionDFA [read=" + read + "]";
	}

}
