/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

/**
 *
 * @author leopp
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import model.Funcionario;

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

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Funcionario> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

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
        List<Funcionario> funcionarios = findAll();
        funcionarios.add(funcionario);
        save(funcionarios); 
    }

    
    public void remove(String cpf) {
        List<Funcionario> funcionarios = findAll();
        funcionarios.removeIf(funcionario -> funcionario.getCpf().equals(cpf));
        save(funcionarios); 
    }
}

