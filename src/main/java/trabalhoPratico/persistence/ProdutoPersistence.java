package trabalhoPratico.persistence;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import trabalhoPratico.model.Produto;

public class ProdutoPersistence implements Persistence<Produto> {

    private static final String PATH = DIRECTORY + File.separator + "produtos.json";

    @Override
    public void save(List<Produto> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        
        File directory = new File(DIRECTORY);
        if(!directory.exists())
            directory.mkdirs();
        
        Archive.save(PATH, json);
    }

    @Override
    public List<Produto> read() {
        Gson gson = new Gson();
        
        String json = Archive.read(PATH);
        
        List<Produto> produtos = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Produto>>() {}.getType();
        
            produtos = gson.fromJson(json, tipoLista);
        
            if (produtos == null)
                produtos = new ArrayList<>();
        }

        return produtos;
    }

}
