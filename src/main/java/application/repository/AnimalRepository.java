package application.repository;

import application.model.Animal;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AnimalRepository extends CrudRepository<Animal, Long> {

   public List<Animal> findByType(String type);
}
