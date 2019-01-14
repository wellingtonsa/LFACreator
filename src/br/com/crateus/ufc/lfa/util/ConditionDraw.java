package br.com.crateus.ufc.lfa.util;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;

public class ConditionDraw extends Label {

	public double rotate;
	public float t;
	TransitionDraw td;
	Rotate rz;

	public ConditionDraw(TransitionDraw curve, String name, float t) {
		super();
		setText(name);
		this.td = curve;
		this.t = t;
		init();
	}

	public ConditionDraw(TransitionDraw curve, float t, String name, double... arg0) {
		super();
		setText(name);
		this.td = curve;
		this.t = t;
		init();
	}

	private void init() {

		rz = new Rotate();
		{
			rz.setAxis(Rotate.Z_AXIS);
		}
		getTransforms().addAll(rz);

		update();
	}

	public void update() {
		double size = Math.max(td.getBoundsInLocal().getWidth(), td.getBoundsInLocal().getHeight());
		double scale = size / 4d;

		Point2D ori = eval(td, t);
		Point2D tan = evalDt(td, t).normalize().multiply(scale);

		setTranslateX(ori.getX());
		setTranslateY(ori.getY());

		double angle = Math.atan2(tan.getY(), tan.getX());

		angle = Math.toDegrees(angle);

		double offset = 0;
		if (t > 0.5)
			offset = 0;

		rz.setAngle(angle + offset);

	}

	private Point2D eval(TransitionDraw c, float t) {
		Point2D p = new Point2D(
				Math.pow(1 - t, 3) * c.getStartX() + 3 * t * Math.pow(1 - t, 2) * c.getStartX()
						+ 3 * (1 - t) * t * t * c.getEndX() + Math.pow(t, 3) * c.getEndX(),
				Math.pow(1 - t, 3) * c.getStartY() + 3 * t * Math.pow(1 - t, 2) * c.getStartY()
						+ 3 * (1 - t) * t * t * c.getEndY() + Math.pow(t, 3) * c.getEndY());
		return p;
	}

	private Point2D evalDt(TransitionDraw c, float t) {
		Point2D p = new Point2D(
				-3 * Math.pow(1 - t, 2) * c.getStartX() + 3 * (Math.pow(1 - t, 2) - 2 * t * (1 - t)) * c.getStartX()
						+ 3 * ((1 - t) * 2 * t - t * t) * c.getEndX() + 3 * Math.pow(t, 2) * c.getEndX(),
				-3 * Math.pow(1 - t, 2) * c.getStartY() + 3 * (Math.pow(1 - t, 2) - 2 * t * (1 - t)) * c.getStartY()
						+ 3 * ((1 - t) * 2 * t - t * t) * c.getEndY() + 3 * Math.pow(t, 2) * c.getEndY());
		return p;
	}
}