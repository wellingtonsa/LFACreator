package br.com.crateus.ufc.lfa.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;

import br.com.crateus.ufc.lfa.model.State;
import br.com.crateus.ufc.lfa.model.TransitionDFA;
import br.com.crateus.ufc.lfa.util.ArrowDraw;
import br.com.crateus.ufc.lfa.util.ConditionDraw;
import br.com.crateus.ufc.lfa.util.StateDraw;
import br.com.crateus.ufc.lfa.util.TransitionDraw;
import javafx.scene.shape.Circle;

public class AutomatoRepository {
	private static ArrayList<ArrowDraw> arrows;
	private static ArrayList<ConditionDraw> conditions;
	private static HashMap<TransitionDraw, TransitionDFA> transition;
	private static HashMap<Circle, State> state;

	public AutomatoRepository() {
		transition = new HashMap<>();
		state = new HashMap<>();
		arrows = new ArrayList<>();
		conditions = new ArrayList<>();
	}

	public HashMap<TransitionDraw, TransitionDFA> getAllTransition() {
		return transition;
	}

	public HashMap<Circle, State> getAllState() {
		return state;
	}

	public void addState(Circle c, State s) {
		state.put(c, s);
	}

	public void addCondition(ConditionDraw cd) {
		conditions.add(cd);
	}

	public void addArrow(ArrowDraw ad) {
		arrows.add(ad);
	}

	public void arrowsUpdate() {
		for (ArrowDraw ad : arrows) {
			ad.update();
		}
	}

	public void conditionUpdate() {
		for (ConditionDraw cd : conditions) {
			cd.update();
		}
	}

	public void addTransition(TransitionDraw td, TransitionDFA t) {
		transition.put(td, t);
	}

	public Circle getStateCircle(Double x, Double y) {
		for (Circle c : state.keySet()) {
			if (x >= c.getBoundsInLocal().getMinX() && x <= c.getBoundsInLocal().getMaxX()
					&& y >= c.getBoundsInLocal().getMinY() && y <= c.getBoundsInLocal().getMaxY())
				return c;
		}
		return null;

	}

	public State getStateModel(Circle ci) {
		for (Circle c : state.keySet()) {
			if (c.equals(ci))
				return state.get(c);
		}
		return null;

	}
	
	public void toStringAll() {
		System.out.println("STATES:");
		for (Circle c : state.keySet()) {
			System.out.println(state.get(c).toString());
		}
		
		System.out.println("TRANSITIONS:");
		for (TransitionDraw td : transition.keySet()) {
			System.out.println(transition.get(td).toString());
		}
		
		
	}

}
