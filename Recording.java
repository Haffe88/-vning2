import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Recording {
	private final int year;
	private final String artist;
	private final String title;
	private final String type;
	private final Set<String> genre;

	public Recording(String title, String artist, int year, String type, Set<String> genre) {
		this.title = title;
		this.year = year;
		this.artist = artist;
		this.type = type;
		this.genre = genre;
	}

	public String getArtist() {
		return artist;
	}

	public Collection<String> getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public int getYear() {
		return year;
	}
	
	@Override
	public boolean equals(Object o ){
		if(this == o) {
			return true;
		}
		
		if(o instanceof Recording) {
			Recording r = (Recording) o;
			return this.title.equals(r.title) && this.artist.equals(r.artist) && this.year == r.year;
		}else {
			return false;
		}
		
	}
	
	//Overridar equals-metod från Object, typomvandlar sedan Object o (som är en instans av recording) till Recording r
	
	@Override
	public int hashCode() {
		return Objects.hash(title, artist, year);
	}
	//La in hashcode här så funkar med hashMap och hashTree, anropar Objects.hash och skickar in element som vill ha med i beräkningen av ett hashvärde (se föreläsning 5)
	//Ska ha override ifall equals-metoden har override

	
	@Override
	public String toString() {
		return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
	}
}
