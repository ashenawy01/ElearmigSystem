package Models;

public interface IDatabase {
    void createDB (Object... obj);
    boolean appendObject(Object obj);
    void retrieveAll ();
    boolean removeObj (Object obj);
    <T> Object find (T id);
}
