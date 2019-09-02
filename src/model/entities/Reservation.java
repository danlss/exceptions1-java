package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

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
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException{
		Date now = new Date(); //auxiliar para teste de atualiza��o
		
		//se a data de checkin � antiga ou a data de checkout
		if (checkIn.before(now) || checkOut.before(now)) {
			//� lan�ada uma excess�o
			throw new DomainException( "Reservation dates for update must be future dates");
		}
		//se a data de checkout est� posterior a de checkin
		if (!checkOut.after(checkIn)) {
			throw new DomainException( "Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
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
