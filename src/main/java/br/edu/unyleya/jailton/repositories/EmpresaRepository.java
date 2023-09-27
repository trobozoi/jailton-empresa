package br.edu.unyleya.jailton.repositories;

import br.edu.unyleya.jailton.models.Empresa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa> {

    public Empresa cadastrar(Empresa empresa) {
        empresa.persist();
        return empresa;
    }

    public List<Empresa> listarTodas()
    {
        return Empresa.listAll();
    }

    public Optional<Empresa> consultar(Long id) {
        return Empresa.findByIdOptional(id);
    }

    public Empresa alterar(Empresa empresa) {
        Empresa.update("nomeEmpresa = '" + empresa.getNomeEmpresa() + "' " +
                ", endereco = '" + empresa.getEndereco() + "' " +
                "where id = " + empresa.getId());
        return empresa;
    }

    public long deletar(Long id) {
        return Empresa.delete("id", id);
    }
}