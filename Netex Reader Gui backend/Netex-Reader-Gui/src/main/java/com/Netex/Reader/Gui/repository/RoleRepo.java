package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
