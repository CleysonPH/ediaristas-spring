package br.com.treinaweb.ediaristas.core.service;

import java.util.List;

public interface IService<T, ID> {

    List<T> buscarTodos();

    T buscarPorId(ID id);

    T cadastrar(T model);

    T editar(T model, ID id);

    void excluirPorId(ID id);

}
