/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

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
