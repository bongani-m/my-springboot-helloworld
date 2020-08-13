package hello;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepo extends CrudRepository<Person, UUID> {

    List<Person> findByNameIgnoreCase(String name);

}