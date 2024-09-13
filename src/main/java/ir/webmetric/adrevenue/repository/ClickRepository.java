package ir.webmetric.adrevenue.repository;

import ir.webmetric.adrevenue.entity.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<ClickEvent,Long> {

}
