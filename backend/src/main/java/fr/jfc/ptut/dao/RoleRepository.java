package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    
}
