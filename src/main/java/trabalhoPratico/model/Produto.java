package trabalhoPratico.model;

import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.exception.NegativeNumberException;

/**
 *
 * @author filip
 */
public class Produto {
    
    private String name;
    private String category;
    private String validade;
    private String price;
    private int quantity;
    private int lote;

    public Produto(){}

    public Produto(String name, String category, String validade, 
    String price, int quantity, int lote) throws EmptyStrException, NegativeNumberException
    {
        setName(name);
        setCategory(category);
        setValidade(validade);
        setPrice(price);
        setQuantity(quantity);
        setLote(lote);
    }
    
    public String getValidade() {
        return this.validade;
    }

    public void setValidade(String validade) throws EmptyStrException {
        if(validade.isBlank())
            throw new EmptyStrException();
        this.validade = validade;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) throws EmptyStrException {
        if(price.isBlank())
            throw new EmptyStrException();
        this.price = price;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) throws EmptyStrException {
        if(name.isBlank())
            throw new EmptyStrException();
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) throws EmptyStrException {
        if(category.isBlank())
            throw new EmptyStrException();
        this.category = category;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) throws NegativeNumberException {
        if(quantity < 0)
            throw new NegativeNumberException();
        this.quantity = quantity;
    }

    public int getLote() {
        return this.lote;
    }

    public void setLote(int lote) throws NegativeNumberException {
        if(lote < 0)
            throw new NegativeNumberException();
        this.lote = lote;
    }

    public Object[] toArray()
    {
        Object[] array = new Object[6];
        array[0] = Integer.toString(this.quantity);
        array[1] = this.name;
        array[2] = this.category;
        array[3] = this.price;
        array[4] = this.validade;
        array[5] = Integer.toString(lote);
        return array;
    }
}
