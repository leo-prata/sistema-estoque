package trabalhoPratico.model;

import java.util.ArrayList;
import java.util.List;

import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.exception.NegativeNumberException;

/**
 *
 * @author filip
 */
public class Produto {
    
    private String name;
    private String category;
    private String price;
    private int totalQuantity;
    public List<String> validade;
    private List<Integer> quantity;
    private List<String> lote;

    public Produto(){
        this.validade = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.lote = new ArrayList<>();
    }

    public Produto(String name, String category, String price)
     throws EmptyStrException, NegativeNumberException
    {
        setName(name);
        setCategory(category);
        setPrice(price);
        this.totalQuantity = 0;

        this.validade = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.lote = new ArrayList<>();
    }

    public Produto(String name, String category, String price, 
    String validade, String lote, int quantity) throws EmptyStrException, NegativeNumberException
    {
        setName(name);
        setCategory(category);
        setPrice(price);
        this.totalQuantity = quantity;

        this.validade = new ArrayList<>();
        this.lote = new ArrayList<>();
        this.quantity = new ArrayList<>();

        setValidade(validade);
        setLote(lote);
        setQuantity(quantity);
        
    }

    public void setValidade(String validade) throws EmptyStrException {
        if(validade.isBlank())
            throw new EmptyStrException();
        this.validade.add(validade);
    }

    public void setPrice(String price) throws EmptyStrException {
        if(price.isBlank())
            throw new EmptyStrException();
        this.price = price;
    }
    
    public void setName(String name) throws EmptyStrException {
        if(name.isBlank())
            throw new EmptyStrException();
        this.name = name;
    }

    public void setCategory(String category) throws EmptyStrException {
        if(category.isBlank())
            throw new EmptyStrException();
        this.category = category;
    }

    public void setQuantity(int quantity) throws NegativeNumberException {
        if(quantity < 0)
            throw new NegativeNumberException();
        this.quantity.add(quantity);
        totalQuantity += quantity;
    }

    public void setLote(String lote) throws NegativeNumberException {
        if(Integer.parseInt(lote.strip()) < 0)
            throw new NegativeNumberException();
        this.lote.add(lote);
    }

    public Object[] toArray()
    {
        Object[] array = new Object[4];
        array[0] = Integer.toString(this.totalQuantity);
        array[1] = this.name;
        array[2] = this.category;
        array[3] = this.price;
        return array;
    }

    public Object[] getProdutoProperties()
    {
        Object[] array = new Object[3];
        array[0] = this.quantity;
        array[1] = this.lote;
        array[2] = this.validade;
        return array;
    }
}
