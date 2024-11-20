class MovieDetails {
    private String title;
    private String director;
    private String[] actors;
    private String timeSlot;
    private double ticketCost;

    public MovieDetails(String title, String director, String[] actors, String timeSlot, double ticketCost) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.timeSlot = timeSlot;
        this.ticketCost = ticketCost;
    }

    public void display(String screenName) {
        synchronized (System.out) { 
            System.out.println("Details for " + screenName + ":");
            System.out.println("Movie Title: " + title);
            System.out.println("Director: " + director);
            System.out.print("Actors: ");
            for (String actor : actors) {
                System.out.print(actor + " ");
            }
            System.out.println("\nTime Slot: " + timeSlot);
            System.out.println("Ticket Cost: $" + ticketCost);
            System.out.println("------------------------------------");
        }
    }
}
class ScreenThread extends Thread {
    private String screenName;
    private MovieDetails movie;

    public ScreenThread(String screenName, MovieDetails movie) {
        this.screenName = screenName;
        this.movie = movie;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            movie.display(screenName);
        } catch (InterruptedException e) {
            System.out.println(screenName + " interrupted.");
        }
    }
}

class CinemaApp {
    public static void main(String[] args) {
        MovieDetails movie1 = new MovieDetails("Avengers: Endgame", "Anthony Russo",
                new String[]{"Robert Downey Jr.", "Chris Evans", "Scarlett Johansson"}, "12:00 PM - 3:00 PM", 10.0);
        MovieDetails movie2 = new MovieDetails("Inception", "Christopher Nolan",
                new String[]{"Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"}, "3:30 PM - 6:30 PM", 12.0);
        MovieDetails movie3 = new MovieDetails("Interstellar", "Christopher Nolan",
                new String[]{"Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"}, "7:00 PM - 10:00 PM", 15.0);
        MovieDetails movie4 = new MovieDetails("Joker", "Todd Phillips",
                new String[]{"Joaquin Phoenix", "Robert De Niro", "Zazie Beetz"}, "10:30 PM - 1:00 AM", 8.0);
        Thread screen1 = new ScreenThread("Screen 1", movie1);
        Thread screen2 = new ScreenThread("Screen 2", movie2);
        Thread screen3 = new ScreenThread("Screen 3", movie3);
        Thread screen4 = new ScreenThread("Screen 4", movie4);
        screen1.start();
        screen2.start();
        screen3.start();
        screen4.start();
        try {
            screen1.join();
            screen2.join();
            screen3.join();
            screen4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}