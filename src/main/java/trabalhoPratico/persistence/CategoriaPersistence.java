/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.persistence;

/**
 *
 * @author leopp
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import trabalhoPratico.model.Categoria;

public class CategoriaPersistence implements Persistence<Categoria> {
     private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "categorias.json";

    @Override
    public void save(List<Categoria> categorias) {
        Gson gson = new Gson();
        String json = gson.toJson(categorias);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Archive.save(PATH, json);
    }

    @Override
    public List<Categoria> read() {
        Gson gson = new Gson();
        String json = Archive.read(PATH);

        List<Categoria> categorias = new ArrayList<>();
        if (!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Categoria>>() {}.getType();
            categorias = gson.fromJson(json, tipoLista);

            if (categorias == null) {
                categorias = new ArrayList<>();
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

        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
            if (categoria.getNome().equals(categoriaAtualizada.getNome())) {
                categorias.set(i, categoriaAtualizada);
                break;
            }
        }

        save(categorias);
    }
}
