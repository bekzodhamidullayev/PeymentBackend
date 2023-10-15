package uz.pdp.peyment.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.peyment.entity.BaseEntity;
import uz.pdp.peyment.exception.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseService <E extends BaseEntity, ID, REP extends JpaRepository<E, ID>, RES, CR> {
    protected final REP repository;
    protected final ModelMapper modelMapper;
    public RES create(CR createReq) {
        E e = mapCRToEntity(createReq);
        repository.save(e);
        return mapEntityRoRES(e);
    }
    public RES findById(ID id) {
        E e = repository.findById(id)
                .orElseThrow(
                        () -> new DataNotFoundException("resource with id: " + id + " not found"));
        return mapEntityRoRES(e);
    }

    public List<RES> getAll() {
        List<E> all = repository.findAll();
        List<RES> resList = new ArrayList<>();
        all.forEach(entity -> {
            resList.add(mapEntityRoRES(entity));
        });
        return resList;
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    protected abstract RES mapEntityRoRES(E entity);
    protected abstract E mapCRToEntity(CR createReq);
}
