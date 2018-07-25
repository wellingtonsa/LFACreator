package br.com.crateus.ufc.lfa.repository;

import java.util.HashMap;

import br.com.crateus.ufc.lfa.model.State;
import br.com.crateus.ufc.lfa.model.TransitionDFA;
import br.com.crateus.ufc.lfa.util.StateDraw;
import br.com.crateus.ufc.lfa.util.TransitionDraw;
import javafx.scene.shape.Circle;

public class AutomatoRepository {

	private static HashMap<TransitionDraw, TransitionDFA> transition;
	private static HashMap<Circle, State> state;

	public AutomatoRepository() {
		transition = new HashMap<>();
		state = new HashMap<>();

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

}
