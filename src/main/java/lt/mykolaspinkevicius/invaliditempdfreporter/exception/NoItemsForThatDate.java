package lt.mykolaspinkevicius.invaliditempdfreporter.exception;

public class NoItemsForThatDate extends RuntimeException{
    public NoItemsForThatDate(String message) {
        super(message);
    }
}
