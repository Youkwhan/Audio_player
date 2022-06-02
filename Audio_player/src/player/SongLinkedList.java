
package player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class implements a Doubly-Linked list that is essentially the playlist.
 * The class will contain SongNode references to the head, cursor, and tail.
 * Contained by the SongNode class which is wrapped by the Song class
 *
 */
public class SongLinkedList {

    private SongNode head;
    private SongNode tail;
    private SongNode cursor; // think of as the target
    private int size; // total number of songs in the playlist
    private int printing;

    //variables for audio playing
    Clip c;
    AudioInputStream audioInputStream;
    boolean playing = false;

    //Constructor
    /**
     * Constructor that create a new instance of SongLinkedList that contain
     * SongNode references to the head, cursor, and tail all set to null.
     */
    public SongLinkedList() {
        super();
        setHead(null);
        setTail(null);
        setCursor(null);
        setSize(0);
    }

    //Getter/Setter
    /**
     * This getter the head (beginning) of the current linked list
     * @return a SongNode that addresses the head
     */
    public SongNode getHead() {
        return head;
    }

    /**
     * This setter the head of the current linked list
     * @param head a SongNode representing the new head
     */
    public void setHead(SongNode head) {
        this.head = head;
    }

    /**
     * This getter the tail (end) of the current linked list
     * @return a SongNode that addresses the tail
     */
    public SongNode getTail() {
        return tail;
    }

    /**
     * This setter the tail of the current linked list
     * @param tail a SongNode representing the new tail
     */
    public void setTail(SongNode tail) {
        this.tail = tail;
    }

    /**
     * This getter the cursor(the pointer) of the current linked list
     * @return a SongNode that address the cursor
     */
    public SongNode getCursor() {
        return cursor;
    }

    /**
     * This setter the cursor of the current linked list
     * @param cursor a SongNode representing the new cursor
     */
    public void setCursor(SongNode cursor) {
        this.cursor = cursor;
    }

    /**
     * This setter the new size of the current linked list
     * @param size a integer representing the new size
     */
    public void setSize(int size) {
        this.size = size;
    }


    //Methods
    /**
     * This play method uses the parameter name which indicates which song to
     * play an audio file
     * @param name the name of the song
     * @throws IllegalArgumentException an error to check if the song does not
     * exist in the playlist or the song has null attributes
     */
    public void play(String name) throws IllegalArgumentException {
        boolean exist = false;
        if (name != null) {
            SongNode temp = cursor;
            cursor = head;
            while(cursor != null) {
                if (cursor.getData().getName().equals(name)) {
                    try {
                        //stop if new request comes in
                        //making an object of AudioInputStream converting
                        // audio file to stream
                        audioInputStream = AudioSystem.
                          getAudioInputStream(new File(name + ".wav"));
                        //clip reference
                        c = AudioSystem.getClip();
                        //open audio stream to clip
                        c.open(audioInputStream);
                        c.start();

                        System.out.println("'" + name + "' by " +
                          cursor.getData().getArtist() +" is now playing.");
                        playing = true;
                        exist = true;
                    } catch (Exception ex) {
                        System.out.println("'" + name + "' not found");

                    }
                }
                cursor = cursor.getNext();
            }
            cursor = temp;
        }
            if (!exist){

            System.out.println(name + " not found");
        }
    }

    /**
     * This is a helper method for the play method.
     * This is to stop the audio file only if the user initiates the play
     * method before the audio file finishes playing
     */
    public void resetAudio(){
        if (playing == true) {
            //stop song
            c.stop();
            c.close();
            playing = false;
        }
        // calls play to reopen audio for new audio

    }

    /**
     * This method moves the cursor to point to the next SongNode.
     * A method which the user uses to move around the Linked List
     */
    public void cursorForwards() {
        //precondition cursor is not empty/null
        if (cursor != tail) {
            cursor = cursor.getNext();
            System.out.println("Cursor moved to the next song.");
        } else {
            System.out.println("Already at the end of the playlist");
        }
    }

    /**
     * This method moves the cursor to point to the previous SongNode.
     * A method which the user uses to move around the Linked List
     */
    public void cursorBackwards() {
        if (cursor != head) {
            cursor = cursor.getPrev();
            System.out.println("Cursor moved to the previous song.");
        } else {
            System.out.println("Already at beginning of playlist");
        }
    }

    /**
     * This method inserts a Song object into the playlist after the cursor's
     * position and links the new nodes to the original linked list nodes
     * @param newSong an object of Song that the user wants to insert
     * @throws IllegalArgumentException an error that checks if the new song
     * attributes are null.
     */
    public void insertAfterCursor(Song newSong)
      throws IllegalArgumentException {
        //newSong is an Object of Song with name,album, etc..
        //so no head null and tail null sett up gettup
        if (newSong != null) {
            SongNode newNode = new SongNode();
            newNode.setData(newSong);

            if (head == null) {
                //all have separate serial number
                head = newNode;
                tail = newNode;
                cursor = newNode;

            } else {
                if (cursor.getNext() == null) {
                    //cursor and tail the same meaning they at end of list
                    tail.setNext(newNode);
                    newNode.setPrev(tail);
                    tail = newNode;
                } else {
                    //linking new node to the next node
                    newNode.setNext(cursor.getNext());
                    newNode.setPrev(cursor);
                    cursor.setNext(newNode);
                    //get the next node after newNode then set the previous
                    //of the nextNode to newNode
                    newNode.getNext().setPrev(newNode);
                    newNode.getPrev().setNext(newNode);
                }
            }

            setSize(getSize() + 1);
            cursor = newNode; //MOVE CURSORFORWARD???
        } else {
            throw new IllegalArgumentException("The newSong is null");
        }
    }

    /**
     * This method removes the song node reference on the cursor
     * @return the Song contained within the node that is removed
     */
    public Song removeCursor() {
        //condition : cursor not null, if cursor is head, tail, only one song
        // left remove everything cursor = null
        if (cursor != null) {
            Song remove = cursor.getData();
            if (cursor != head && cursor != tail) {
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
            } else if (cursor == head) {
                head = cursor.getNext();
                cursor = head;
            } else if (cursor == tail) {
                tail = cursor.getPrev();
                cursor = tail;
            } else if (cursor == head && cursor == tail) { //one song
                cursor = null;
                head = null;
                tail = null;
            }
            setSize(getSize() - 1);
            return remove;
        } else {
            System.out.println("No songs in the playlist to be removed");
            return null;
        }
    }

    /**
     * This method gets the size of the linked list
     * @return a integer representing the size
     */
    public int getSize() {
        return size;
    }

    /**
     * This is a helper method for random() which finds a random number/ node
     * in the linked list
     * @return a Song of a random node that was chosen
     */
    public Song findRandom() {
        //math random from size then loop though bcz of shuffle need order too
        SongNode randomSong = head;
        int randomNumber = (int)(Math.random() * getSize())+1;
        int i = 1;
        while(i<randomNumber) {
            randomSong = randomSong.getNext();
            i++;
        }
        return randomSong.getData();
    }

    /**
     * This method uses the findRandom() method to return a random Song and
     * play that song
     * @return the song which was randomly selected
     */
    public Song random() {
        resetAudio();
        play(findRandom().getName());
        return findRandom();
        //RETURNING FOR WHAT???  maybe the print statement above so i can use
        //Song to print the above statement in the main
    }

    /**
     * This method shuffles the playlist and reorders the songs in random order
     * while keeping the cursor on the orignal song
     */
    public void shuffle() {
        SongNode tempCursorSong = cursor;
        cursor = head;
        Song[] copy = new Song[getSize()];
        int arrayIndex = 0;

        while( arrayIndex < getSize()){
            copy[arrayIndex] = cursor.getData();
            cursor = cursor.getNext();
            arrayIndex++;
        }
        //,mshuffle
        for (int i = 0; i < copy.length; i++){
            Song temp = copy[i];
            int num = (int)Math.random()*copy.length;
            copy[i] = copy[num];
            copy[num] = temp;
        }
        head = null;
        tail = null;
        cursor = null;
        int tempSize = getSize();
        for (int i = 0; i < copy.length; i++) {
            insertAfterCursor(copy[i]);
        }
        //set cursor and size back
        setSize(tempSize);
        cursor = tempCursorSong;
        cursor.setData(tempCursorSong.getData());

    }

    /**
     * This method prints the song playlist in a table format using thetoString
     */
    public void printPlayerList() {
        //put cursor arrow on the song its playing
        SongNode temp = cursor;
        cursor = head;

        String pointer = "<-";
        System.out.println(String.format("%-25s %-25s %-25s %-12s", "Song",
          "| Artist", "| Album", "| Length (s)"));
        System.out.println("------------------------------------------------" +
          "------------------------------------------");
        for (int i = 0; i < getSize(); i++) {
            printing = i;
            // or toString below
            String stringFormat = String.format("%-27s %-25s %-25s %-12d",
              cursor.getData().getName(), cursor.getData().getArtist(),
              cursor.getData().getAlbum(), cursor.getData().getLength());
            if (temp.getData().getName().equals(cursor.getData().getName())) {
                //System.out.print(cursor.toString) Not overriding??
                System.out.print( stringFormat + pointer + "\n");
            } else {
                System.out.print(stringFormat + "\n");
            }
            cursor = cursor.getNext();
        }
        cursor = temp;
    }

    public void deleteAll() {
        head = null;
        tail = null;
        cursor = null;
        setSize(0);
    }

    /**
     * The string format that displays the song's attributes
     * @return string
     */
    @Override
    public String toString() {
        SongNode temp = cursor;
        cursor = head;
        String stringFormat = "";
        for (int i = 0; i < printing; i++) {
            if (printing == i) {
                stringFormat = String.format("%-25s %-25s %-25s %-12d",
                  cursor.getData().getName(), cursor.getData().getArtist(),
                  cursor.getData().getAlbum(), cursor.getData().getLength());
            }
            cursor = cursor.getNext();
        }
        cursor = temp;
        return stringFormat;
    }
}
