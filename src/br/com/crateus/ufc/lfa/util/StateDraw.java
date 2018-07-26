package br.com.crateus.ufc.lfa.util;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class StateDraw extends Circle {
	public StateDraw(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 30);
		setFill(Color.WHITESMOKE.deriveColor(1, 1, 1, 1));
		setStroke(color);
		setStrokeWidth(2);
		setStrokeType(StrokeType.OUTSIDE);

		x.bind(centerXProperty());
		y.bind(centerYProperty());
		enableDrag();

	}

	private void enableDrag() {
		final Delta dragDelta = new Delta();
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.SECONDARY) {
					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
				}
			}
		});
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.SECONDARY) {
					getScene().setCursor(Cursor.HAND);
				}
			}
		});
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.SECONDARY) {
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < getScene().getWidth()) {
						setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight()) {
						setCenterY(newY);
					}
				}
			}
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isSecondaryButtonDown()) {
					getScene().setCursor(Cursor.HAND);
				}
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isSecondaryButtonDown()) {
					getScene().setCursor(Cursor.DEFAULT);
				}
			}
		});
	}

	private class Delta {
		double x, y;
	}

}
