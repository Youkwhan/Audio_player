package player;

import java.util.Scanner;

/**
 * A class that prompts the user for commands to make a playlist and some
 * function.
 */
public class Player {

    /**
     * Main method that prompts the user with a set of commands to execute
     * certain programs/methods
     * @param args none
     */
    public static void main(String[] args){
        //empty SongLinkedList object
        SongLinkedList playlist = new SongLinkedList();
        Scanner input = new Scanner(System.in);

        boolean run = true;
        while (run){
            System.out.println("\n(A)  Add Song to Playlist\n" +
                    "(F)  Go to Next Song\n" +
                    "(B)  Go to Previous Song\n" +
                    "(R)  Remove Song from Playlist\n" +
                    "(L)  Play a Song\n" +
                    "(C)  Clear the Playlist\n" +
                    "(S)  Shuffle Playlist\n" +
                    "(Z)  Random Song\n" +
                    "(P)  Print Playlist\n" +
                    "(T)  Get the Total Amount of Songs in the Playlist\n" +
                    "(Q)  Exit the playlist\n");
            System.out.print("Enter an option: ");
            String command = input.next().toUpperCase();

            switch(command){

                case("A"):
                    input.nextLine();
                    System.out.print("Enter song title: ");
                    String song = input.nextLine().trim();
                    System.out.print("Enter artist(s) of the song: ");
                    String artist = input.nextLine().trim();
                    System.out.print("Enter album: ");
                    String album = input.nextLine().trim();
                    System.out.print("Enter length (in seconds): ");
                    String seconds = input.nextLine().trim();
                    Song newSong = new Song(song, artist, album,
                      Integer.parseInt(seconds));
                    playlist.insertAfterCursor(newSong);
                    System.out.println("\n'" + newSong.getName()+ "' by " +
                      newSong.getArtist() + " is added to your playlist.");
                    break;

                case("F"):
                    playlist.cursorForwards();
                    break;

                case("B"):
                    playlist.cursorBackwards();
                    break;

                case("R"):
                    Song removed = playlist.removeCursor();
                    System.out.println("'" + removed.getName() + "'" + " by " +
                      removed.getArtist() + " was removed from the playlist.");
                    break;

                case("L"):
                    System.out.print("Enter name of song to play: ");
                    input.nextLine();
                    String name = input.nextLine();
                    playlist.resetAudio();
                    playlist.play(name);
                    break;

                case("C"):
                    playlist.deleteAll();
                    System.out.println("Playlist cleared.");
                    break;

                case("S"):
                    playlist.shuffle();
                    System.out.println("Playlist shuffled.");
                    break;

                case("Z"):
                    System.out.println("Playing a random song...");
                    playlist.random();
                    break;

                case("P"):
                    System.out.println("\nPlaylist:");
                    playlist.printPlayerList();
                    break;

                case("T"):
                    int num = playlist.getSize();
                    if (num == 0){
                        System.out.println("Your playlist is empty.");
                    }else {
                        System.out.println("Your playlist contains " + num +
                          " songs.");
                    }
                    break;

                case("Q"):
                    run = false;
                    System.out.println("\nProgram terminated.");
                    break;

                default:
                    System.out.println("Selected option does not exist");
                    run = true;
            }
        }
        input.close();
    }
}
