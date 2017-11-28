package xyz.ajanicorp.playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ajanicorp.playground.domain.Link;

/**
 * Created by Ajani on 17/10/2017.
 */
public interface LinkRepository extends JpaRepository<Link,Integer> {
    public Link findByCode(String code);
}
