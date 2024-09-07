package trabalhoPratico.persistence;

import java.util.List;

/**
 *
 * @author filip
 */
public interface Persistence<T> {
    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> read();
}
