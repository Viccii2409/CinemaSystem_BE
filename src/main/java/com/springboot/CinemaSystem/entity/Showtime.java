package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.SelectedSeatDto;
import com.springboot.CinemaSystem.dto.ShowtimeRoomDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"room", "movie", "ticketBought", "seatAvailability"})
public class Showtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "showtimeID")
	private long ID;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Time startTime;
	private Time endTime;
	@Column(nullable = false)
	private boolean status;
	private String action;

	@Formula("(SELECT COUNT(*) FROM selected_seat s " +
			"WHERE s.status = 'confirmed' AND s.showtimeid = showtimeID)")
	private int availableSeats;		// số ghế được đặt

	@Formula(" (SELECT bp.default_price + dow.surcharge + tf.surcharge " +
			" FROM base_price bp " +
			" JOIN showtime s ON s.base_priceid = bp.id " +
			" JOIN day_of_week dow ON s.day_of_weekid = dow.day_of_weekid " +
			" JOIN time_frame tf ON s.time_frameid = tf.time_frameid " +
			" WHERE s.showtimeid = showtimeID) ")

	private Float priceTicket;

	@ManyToOne
	@JoinColumn(name = "roomID", nullable = false)
	@JsonIgnoreProperties("showtime")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "movieID")
	private Movie movie;

	@OneToMany(mappedBy = "showtime")
	private List<Booking> booking;

	@OneToMany(mappedBy = "showtime", fetch = FetchType.LAZY)
	private List<SelectedSeat> selectedSeats;

	@ManyToOne
	@JoinColumn(name = "basePriceID")
	private BasePrice basePrice;

	@ManyToOne
	@JoinColumn(name = "dayOfWeekID")
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "timeFrameID")
	private TimeFrame timeFrame;

	public ShowtimeRoomDto toShowtimeRoomDto(){
		ShowtimeRoomDto showtimeRoomDto = new ShowtimeRoomDto(
				this.getID(),
				this.getDate(),
				this.getStartTime(),
				this.getEndTime(),
				this.isStatus(),
				this.getAction(),
				this.getPriceTicket(),
				this.getMovie().toMovieShowtimeDto(),
				this.getRoom().toRoomSeatDto(),
				this.getSelectedSeats().stream()
						.filter(entry -> entry.getStatus() != null && !"expired".equals(entry.getStatus()))
						.map(entry -> new SelectedSeatDto(
								entry.getID(),
								entry.getUser().getID(),
								entry.getSeat().getID(),
								entry.getStart(),
								entry.getEnd(),
								entry.getStatus()
						))
						.collect(Collectors.toList())
		);
		return showtimeRoomDto;
	}

	@Override
	public String toString() {
		return "Showtime{id=" + ID + ", date=" + date + ", startTime=" + startTime + ", status=" + status + "}";
	}
	public void setPriceTicket(Float priceTicket) {
		if (priceTicket == null) {
			this.priceTicket = 0.0f; // Giá trị mặc định nếu không có giá trị
		} else {
			this.priceTicket = priceTicket;
		}
	}



}