package projekat.demo.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TermDto implements Serializable {

	private static final long serialVersionUID = 4949242372989023929L;

	private long id;
	private Date projectionDate;
	private Time projectionTime;
	private double price;
	private String arenaName;
	
	public TermDto(Date projectionDate, Time projectionTime, double price, String arenaName) {
		super();
		this.projectionDate = projectionDate;
		this.projectionTime = projectionTime;
		this.price = price;
		this.arenaName = arenaName;
	}

	public TermDto(long id, Date projectionDate, Time projectionTime, double price, String arenaName) {
		super();
		this.id = id;
		this.projectionDate = projectionDate;
		this.projectionTime = projectionTime;
		this.price = price;
		this.arenaName = arenaName;
	}

	public TermDto() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	public Time getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(Time projectionTime) {
		this.projectionTime = projectionTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getArenaName() {
		return arenaName;
	}

	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}
	
	

}
