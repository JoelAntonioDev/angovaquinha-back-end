package com.projecto.angovaquinha.InterfaceService;

import com.projecto.angovaquinha.excecoes.ExcecaoP;

import java.util.List;
import java.util.Optional;

public interface InterfaceServico<T, Tipo> {

    List<T> listarTodos();

    Optional<T> buscarPorId(Tipo id);

    T adicionar(T entidade) throws ExcecaoP;

    T editar(Tipo id, T entidade) throws ExcecaoP;

    void eliminar(Tipo id);
}
