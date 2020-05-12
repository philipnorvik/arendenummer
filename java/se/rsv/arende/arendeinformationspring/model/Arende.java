package se.rsv.arende.arendeinformationspring.model;

//import java.io.Serializable;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity implementation class for Entity: Arende
 *
 *
 *
 *Modellen för ett ärendenummer.
 *
 *här finns det som kommer att finnas i databasen. 
 */
@Entity
public class Arende /* implements Serializable */ {

	public Arende() {
	//	super();
	}
	
	
	 public Arende(String arendenummer, String myndighet, String datum) { //
	 this.arendenummer=arendenummer; this.myndighet=myndighet; this.datum=datum; }
	 
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 
	@Column(name="arendenummer")
	private String arendenummer;
	
	@Column(name="myndighet")
	private String myndighet;
	
	@Column(name="datum")
	private String datum;

	@Column(name="orgnummer")
	private String orgnummer;
	//@Transient
	//private static final long serialVersionUID = 1L;


	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getOrgnummer() {
		return orgnummer;
	}

	public void setOrgnummer(String orgnummer) {
		this.orgnummer = orgnummer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArendenummer() {
		return this.arendenummer;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.datum = formatter.format(datum);
	}

	public void setArendenummer(String arendenummer) {
		this.arendenummer = arendenummer;
	}   
	public String getMyndighet() {
		return this.myndighet;
	}

	public void setMyndighet(String myndighet) {
		this.myndighet = myndighet;
	}
	 @Override
	    public String toString() {
	        return String.format("Arende [id=%d, arendenummer='%s', myndighet='%s', datum='%s']",
	        		id, arendenummer, myndighet, datum);
	    }
}
