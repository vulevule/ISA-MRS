package projekat.demo.dto;

import projekat.demo.model.Projection;
import projekat.demo.model.Reservation;

public class ViewReservationDTO {
	private Projection p;
	private Reservation r;

	public ViewReservationDTO() {
	}

	public ViewReservationDTO(Projection p, Reservation r) {
		this();
		this.p = p;
		this.r = r;
	}

	public Projection getP() {
		return p;
	}

	public void setP(Projection p) {
		this.p = p;
	}

	public Reservation getR() {
		return r;
	}

	public void setR(Reservation r) {
		this.r = r;
	}

}
