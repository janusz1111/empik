public abstract class Book extends Product{
   private final String author;
    private final int pageCounter;

    public Book(long id, String author, int pageCounter) {
        author = author;
        this.pageCounter = pageCounter;
        this.author = "";
    }
}
