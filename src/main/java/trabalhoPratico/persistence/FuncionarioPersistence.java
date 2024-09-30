/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
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

import trabalhoPratico.model.Funcionario;

import java.util.*;

public class FuncionarioPersistence implements Persistence<Funcionario> {

    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "funcionarios.json";

    @Override
    public void save(List<Funcionario> funcionarios) {
        Gson gson = new Gson();
        String json = gson.toJson(funcionarios);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Archive.save(PATH, json);
    }

    @Override
    public List<Funcionario> read() {
        Gson gson = new Gson();
        String json = Archive.read(PATH);

        List<Funcionario> funcionarios = new ArrayList<>();
        if (!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Funcionario>>() {}.getType();
            funcionarios = gson.fromJson(json, tipoLista);

            if (funcionarios == null) {
                funcionarios = new ArrayList<>();
            }
        }

        return funcionarios;
    }
    
     public void add(Funcionario funcionario) {
        List<Funcionario> funcionarios = read();
        funcionarios.add(funcionario);
        save(funcionarios); 
    }

    
    public void remove(String cpf) {
        List<Funcionario> funcionarios = read();
        funcionarios.removeIf(funcionario -> funcionario.getCpf().equals(cpf));
        save(funcionarios); 
    }
    
    public void update(Funcionario funcionarioAtualizado) {
        List<Funcionario> funcionarios = read();

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            if (funcionario.getCpf().equals(funcionarioAtualizado.getCpf())) {
                funcionarios.set(i, funcionarioAtualizado);
                break;
            }
        }

        save(funcionarios);
    }
}

