/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import trabalhoPratico.model.Categoria;

public class CategoriaPersistence implements Persistence<Categoria> {
    
     private static final String DIRECTORY = "data";
     private static final String PATH = DIRECTORY + File.separator + "categorias.json";

    @Override
    public void save(List<Categoria> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        
        File directory = new File(DIRECTORY);
        if(!directory.exists())
            directory.mkdirs();
        
        Archive.save(PATH, json);
    }

    @Override
    public List<Categoria> read() {
        Gson gson = new Gson();
        
        String json = Archive.read(PATH);
        
        List<Categoria> categorias = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Categoria>>() {}.getType();
        
            categorias = gson.fromJson(json, tipoLista);
        
            if (categorias == null)
                categorias = new ArrayList<>();
        }
        
        for (Categoria categoria : categorias) {
            if (categoria.getId() == null || categoria.getId().isEmpty()) {
                categoria.setId(UUID.randomUUID().toString());
        }
}

        return categorias;
    }
     public void add(Categoria categoria) {
        List<Categoria> categorias = read();
        categorias.add(categoria);
        save(categorias);
    }

    public void remove(String nome) {
        List<Categoria> categorias = read();
        categorias.removeIf(categoria -> categoria.getNome().equals(nome));
        save(categorias);
    }
    
    public void update(Categoria categoriaAtualizada) {
    List<Categoria> categorias = read();  
    
    boolean categoriaEncontrada = false;
    for (int i = 0; i < categorias.size(); i++) {
        Categoria categoria = categorias.get(i);
        if (categoria.getId().equals(categoriaAtualizada.getId())) {  
            categorias.set(i, categoriaAtualizada); 
            categoriaEncontrada = true;
            break;
        }
    }

    if (categoriaEncontrada) {
        save(categorias); 
    } else {
        System.out.println("Categoria não encontrada para atualização.");
    }
}



}