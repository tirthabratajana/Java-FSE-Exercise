// MusicConcert.java
public class MusicConcert {
    public static void main(String[] args) {
        // Instantiate Guitar and Piano classes
        Guitar guitar = new Guitar();
        Piano piano = new Piano();

        // Call the play method for each instrument
        System.out.print("Playing the guitar: ");
        guitar.play();

        System.out.print("Playing the piano: ");
        piano.play();

        // Demonstrating polymorphism with interfaces
        Playable instrument1 = new Guitar();
        Playable instrument2 = new Piano();

        System.out.print("\nAnother instrument (guitar): ");
        instrument1.play();

        System.out.print("Another instrument (piano): ");
        instrument2.play();
    }
}