package us.jordan.week9.demo;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, String> {
}
