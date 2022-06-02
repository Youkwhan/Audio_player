
package player;

/**
 * Class Song which represents the individual object songs in the playlist.
 * Song has the attributes name(String), artist(String), album(String),
 * and a length(int)
 */
public class Song {

    private String name; //name of song; unique name only one exist of its kind
    private String artist;
    private String album;
    private int length; //length of song in secs

    /**
     * Default Constructor, creates an empty Song object
     */
    public Song(){

    }

    /**
     * Constructor for whenever instance of Song class is initiated
     * @param name a string representing name of song
     * @param artist a string representing name of artist of song
     * @param album a string representing name of album of song
     * @param length a integer representing the length of the song
     */
    public Song(String name, String artist, String album, int length){
        setName(name);
        setArtist(artist);
        setAlbum(album);
        setLength(length);
    }

    /**
     * This getter method gets the song's name
     * @return string of song's name
     */
    public String getName() {
        return name;
    }

    /**
     * This setter method sets the song's name
     * @param name a string of song's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This getter method gets the song's artist
     * @return string of song's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * This setter method sets the song's art ist
     * @param artist a string of song's artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * This getter method gets the song's album
     * @return string of song's album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * This setter method sets the song's album
     * @param album a string of song's album
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * This getter method gets the song's length
     * @return a string of song's length
     */
    public int getLength() {
        return length;
    }

    /**
     * This setter method sets the song's length
     * @param length a string of song's length
     */
    public void setLength(int length) {
        this.length = length;
    }
}
