import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.SortedMap;


public class Searcher implements SearchOperations{

	private Collection<Recording> recordings;

	public Searcher(Collection<Recording> data) {
		this.recordings = data;
	}

		/**
	 * Returnerar antalet unika artister
	 * <p>
	 * Exempel:
	 * numberOfArtists() => 0
	 * numberOfArtists() => 100
	 *
	 * @return antalet unika artister (long)
	 */
	
	public long numberOfArtists(){

		Set<String> unikaArtister = new HashSet<>();
		for (Recording r: recordings){
				unikaArtister.add(r.getArtist());
			}
		long antal = unikaArtister.size();

		return antal;
	}


	/**
	 * Returnerar antalet genrer
	 * <p>
	 * Exempel:
	 * numberOfGenres() => 25
	 *
	 * @return antalet genrer (long)
	 */
	public long numberOfGenres() {
		
		Set<String> unikaGenrer = new HashSet<>();
		
		for (Recording r: recordings) {
			for (String g: r.getGenre()) {
				unikaGenrer.add(g);
			}
		}
		
		long antalGenrer = unikaGenrer.size();
		return antalGenrer;
	}

	/**
	 * Returnerar antalet unika titlar
	 * <p>
	 * Exempel:
	 * numberOfTitles => 500
	 *
	 * @return antalet unika titlar (long)
	 */

	public long numberOfTitles(){
		
		Set<String> unikaTitlar = new HashSet<>();
		
		for (Recording r: recordings){
			unikaTitlar.add(r.getTitle());
		}
		
		long antalTitlar = unikaTitlar.size();
		
		return antalTitlar;

	}


	/**
	 * Kontrollerar om det finns en artist med det sökta namnet.
	 * <p>
	 * Exempel:
	 * doesArtistExist("Phoebe Bridgers") => false
	 * doesArtistExist("Miles Davis") => true
	 *
	 * @param name Artistens namn
	 * @return true om artisten finns, false om inte
	 */
	public boolean doesArtistExist(String name){
		
		for (Recording r: recordings) {
			if(r.getArtist().equals(name)) {
				return true;
			}
		} 
		return false;

	}
	
	//La inte till någon hashset här - tyckte inte det behövdes för att lösa uppgiften?

	/**
	 * Ger en omodifierbar samling med genrer.
	 * <p>
	 * Exempel:
	 * getGenres() => en samling med 19 genrer.
	 *
	 * @return en omodifierbar samling med genrer
	 */
	public Collection<String> getGenres(){
		
		Collection<String> unikaGenrer = new HashSet<>();
		
		for (Recording r: recordings) {
			for (String g: r.getGenre()) {
				unikaGenrer.add(g);
			}
		}
		
		return Collections.unmodifiableCollection(unikaGenrer);

	}

	/**
	 * Hämtar inspelning med den sökta titeln.
	 * <p>
	 * Exempel:
	 * getRecordingByName("Punisher") => null
	 * getRecordingByName("Giant Steps") => referens till Recording-objekt gär getTitle().equals("Giant Steps")
	 *
	 * @param title Inspelningens titel
	 * @return ett Recodring-objekt med den sökta inspelningen om den hittades, null annars
	 */
	 public Recording getRecordingByName(String title){
		 for (Recording r: recordings) {
			 if(r.getTitle().equals(title))
				 return r;
		 }
		 return null;
	}

	/**
	 * Hämtar en omodifierbar samling med inspelningar från och med det angivna året.
	 * <p>
	 * Exempel:
	 * getRecordingsAfter(2010) => samling med ett antal (kan vara 0) objekt
	 *
	 * @param year året som sökningen startar från (och inkluderar)
	 * @return en omodifierbar samling med inspelningar
	 */

	public Collection<Recording> getRecordingsAfter(int year){

		TreeMap <Integer, Set<Recording>> map  = new TreeMap<>();

		//bygger upp mapen utifrån år där andra delen av mapen blir ett set med Recordings på året.

		for (Recording r: recordings){
			int y = r.getYear();

			map.putIfAbsent(y, new HashSet<>());
			map.get(y).add(r);
		}

		// En metod map har för att sortera ut specifika årtal över ett visst årtal.

		SortedMap<Integer,Set<Recording>> tail = map.tailMap(year);

		// Resultatet vill inte ha en map så jag lägger över objekten, utan år, i en hashet.

		Collection<Recording> result = new HashSet<>();
		for (Set <Recording> set : tail.values()) {
			result.addAll(set);
		}
	return Collections.unmodifiableCollection(result);
	}

	/**
	 * Hämtar en omodifierbar samling med inspelningar av artisten
	 * sorterade i stigande ordning på år.
	 * <p>
	 * Exempel:
	 * getRecordingsByArtistOrderedByYearAsc("John Coltrane") => samling med ett antal (kan vara 0)  objekt
	 *
	 * @param artist den sökta artisten
	 * @return ett omodifierbar samling med inspelningar
	 */
	public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist){
		
		SortedSet<Recording> recordingsByArtistAsc = new TreeSet<>(Comparator.comparingInt(Recording::getYear).thenComparing(Recording::getTitle));
		
		for(Recording r : recordings) {
			if(r.getArtist().equals(artist)) {
				recordingsByArtistAsc.add(r);
			}
		}
		return Collections.unmodifiableSortedSet(recordingsByArtistAsc);

	}
	//TreeSet behöver Comparator för att kunna jämföra Recording-objekten (då TreeSet och TreeMap inte använder equals-metoden i Recording). 

	/**
	 * Hämtar en omodifierbar samling med inspelningar i genren.
	 * <p>
	 * Exempel:
	 * getRecordingsByGenre("Jazz") => samling med ett antal (kan vara 0) objekt
	 *
	 * @param genre den sökta genren
	 * @return ett omodifierbar samling med inspelningar
	 */
	// public Collection<Recording> getRecordingsByGenre(String genre){

	// }

	/**
	 * Hämtar en omodifierbar samling med inspelningar i genren gjorda
	 * mellan de angivna åren
	 * <p>
	 * Exempel:
	 * getRecordingsByGenreAndYear("Jazz", 1950, 1960) => samling med ett antal (kan vara 0) objekt
	 *
	 * @param genre    den efterfrågade genren
	 * @param yearFrom första året i intervallet
	 * @param yearTo   sista året i intervallet
	 * @return en omodifierbar samling
	 */
	// public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo){

	// }

	/**
	 * Tar emot en samling och returnerar en ny samling
	 * som bara innehåller de Recordings som inte redan fanns i databasen.
	 *
	 * @param offered En samling med inspelningar
	 * @return en omodifierbar samling med de inspelningar som inte redan finns
	 */
	// public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered){

	// }

 }

 