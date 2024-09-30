/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.model;

import java.util.ArrayList;
import java.util.List;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.exception.NegativeNumberException;
import trabalhoPratico.exception.InvalidDataException;

/**
 *
 * @author filip
 */
public class Produto {
    
    private String name;
    private String category;
    private Double price;
    public List<String> validade;
    private List<Integer> quantity;
    private List<String> lote;
    private int quantidadeLotes;

    public Produto(){
        this.validade = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.lote = new ArrayList<>();
        this.quantidadeLotes = 0;
    }

    public void setValidade(String validade) throws EmptyStrException, InvalidDataException {
        if(validade.equals("  /  /    ") || validade == null)
            throw new EmptyStrException();
        if(!dataValida(validade))
            throw new InvalidDataException();
        this.validade.add(validade);
    }

    private Boolean dataValida(String data)
    {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6));

        if(dia>=1 && dia<=30 && mes>=1 && mes<=12 && ano>=0)
            return true;
        else
            return false;
    }

    public void setPrice(Double price) throws NegativeNumberException {
        if(price < 0 )
            throw new NegativeNumberException();
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
    }

    public void setLote(String lote) throws EmptyStrException {
        if(lote.isBlank())
            throw new EmptyStrException();

        this.lote.add(lote);
        quantidadeLotes++;
    }

    public Object[] toArray()
    {
        Object[] array = new Object[4];
        array[0] = Integer.toString(totalQuantity());
        array[1] = this.name;
        array[2] = this.category;
        array[3] = "R$ " + this.price;
        return array;
    }
    
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public int totalQuantity()
    {
        int total = 0;
        for(int qnt: quantity)
            total += qnt;
        return total;
    }

    public Object[] getProdutoProperties(int i)
    {
        Object[] array = new Object[3];
        array[0] = this.quantity.get(i);
        array[1] = this.lote.get(i);
        array[2] = this.validade.get(i);
        return array;
    }
    
    public int getQuantidaddeLotes()
    {
        return this.quantidadeLotes;
    }

    public List<String> getValidade() {
        return validade;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public List<String> getLote() {
        return lote;
    }

    public void removeLote(int index)
    {
        quantity.remove(index);
        lote.remove(index);
        validade.remove(index);
        quantidadeLotes--;
    }
}
