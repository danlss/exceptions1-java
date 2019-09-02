package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getcheckIn() {
		return checkIn;
	}


	public Date getcheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //converte de milisegundos para dias
		
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date(); //auxiliar para teste de atualização
		
		//se a data de checkin é antiga ou a data de checkout
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Reservation dates for update must be future dates";
		}
		//se a data de checkout está posterior a de checkin
		if (!checkOut.after(checkIn)) {
			return "Check-out date must be after check-in date";
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
	
}
