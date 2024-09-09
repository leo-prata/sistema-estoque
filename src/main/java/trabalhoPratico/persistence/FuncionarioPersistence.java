package trabalhoPratico.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import trabalhoPratico.model.Funcionario;
import java.lang.reflect.Type;

/**
 *
 * @author filip
 */
public class FuncionarioPersistence implements Persistence<Funcionario> {

    private static final String PATH = DIRECTORY + File.separator + "funcionarios.json";
    
    @Override
    public void save(List<Funcionario> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        
        File directory = new File(DIRECTORY);
        if(!directory.exists())
            directory.mkdirs();
        
        Archive.save(PATH, json);
    }

    @Override
    public List<Funcionario> read() {
        Gson gson = new Gson();
        
        String json = Archive.read(PATH);
        
        List<Funcionario> funcionarios = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Funcionario>>() {}.getType();
        
            funcionarios = gson.fromJson(json, tipoLista);
        
            if (funcionarios == null)
                funcionarios = new ArrayList<>();
        }

        return funcionarios;
    }
    
}
