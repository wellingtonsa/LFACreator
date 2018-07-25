package br.com.crateus.ufc.lfa.model;

public class TransitionDFA {

	private State origin;
	private State destination;
	private ConditionDFA condition;

	public TransitionDFA(State origin, State destination, ConditionDFA condition) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.condition = condition;
	}

	public State getOrigin() {
		return origin;
	}

	public void setOrigin(State origin) {
		this.origin = origin;
	}

	public State getDestination() {
		return destination;
	}

	public void setDestination(State destination) {
		this.destination = destination;
	}

	public ConditionDFA getCondition() {
		return condition;
	}

	public void setCondition(ConditionDFA condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "TransitionDFA [origin=" + origin + ", destination=" + destination + ", condition=" + condition + "]";
	}

}
