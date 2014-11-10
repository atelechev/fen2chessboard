package fr.atelechev.chess.fen2cb.style;

import fr.atelechev.util.Objects;

public abstract class DiagramStyle {

	private final String name;
	
	private Point cellsOrigin;
	
	private Point overlayOrigin;
	
	public DiagramStyle(String name) {
		Objects.requireNonNullNonEmptyString(name, "name");
		this.name = name;
		this.cellsOrigin = new Point(0, 0);
		this.overlayOrigin = null;
	}

	public Point getCellsOrigin() {
		return this.cellsOrigin;
	}

	public void setCellsOrigin(Point cellsOrigin) {
		if (cellsOrigin == null) {
			cellsOrigin = new Point(0, 0);
		}
		this.cellsOrigin = cellsOrigin;
	}

	public Point getOverlayOrigin() {
		return this.overlayOrigin;
	}

	public void setOverlayOrigin(Point overlayOrigin) {
		this.overlayOrigin = overlayOrigin;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return this.name.toLowerCase().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof DiagramStyle)) {
			return false;
		}
		return this.name.equalsIgnoreCase(((DiagramStyle) obj).name);
	}
	
}
