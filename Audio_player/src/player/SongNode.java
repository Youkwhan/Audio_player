
package player;

/**
 * A class that creates the song's node for the song data to be put it.
 * Used for linked list. wrapped by Song class
 */
public class SongNode{

    private SongNode prev;
    private SongNode next;
    private Song data = new Song();

    /**
     * Default Constructor that initiates/creates the Song node
     */
    public SongNode(){

    }

    /**
     * This method moves from the current song node to the previous song node
     * @return the previous song node address
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * This method uses the parameters SongNode prev to set the previous song
     * node to the one that got passed
     * @param prev an address of a Song Node representing the previous node
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**
     * This method moves the current song node to the next song node
     * @return the next song node address
     */
    public SongNode getNext() {
        return next;
    }

    /**
     * This method uses the parameters to set the next song node
     * @param next an address of a song node representing the next node
     */
    public void setNext(SongNode next){
        this.next =next;
    }

    /**
     * This method gets the data at the current Song Node
     * @return the song data that holds all the attributes
     */
    public Song getData() {
        return data;
    }

    /**
     * This method uses the parameters to set the current song node data
     * to the new data
     * @param data a song object containing certain attributes
     */
    public void setData(Song data) {
        //this.data = data;
        this.data.setName(data.getName());
        this.data.setArtist(data.getArtist());
        this.data.setAlbum(data.getAlbum());
        this.data.setLength(data.getLength());
    }
}
