package com.proyecto.core.repository;

import com.proyecto.core.model.Role;
import com.proyecto.core.model.User;
import com.proyecto.core.model.payment.ProcessorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

    @Query(value = "SELECT * FROM db_project.users WHERE name = ?1", nativeQuery = true)
    User findFirstByName(String name);
    @Transactional
    void deleteByName(String name);

    @Query(value = "SELECT role_id FROM db_project.users WHERE name = ?1", nativeQuery = true)
    int getId(String string);

    @Transactional
    @Query(value = "update users set users.role_id = ?1 where users.id = ?2",nativeQuery = true)
    void updateUser(int role_id, Integer id);

    User findFirstById(Integer id);

    @Query(value = "SELECT * FROM db_diseño.users WHERE name = ?1", nativeQuery = true)
    Optional<User> theresEqualUsername(String username);

    @Query(value = "SELECT * FROM db_diseño.users WHERE email = ?1", nativeQuery = true)
    Optional<User> theresEqualEmail(String email);

    @Query(value = "SELECT * FROM db_diseño.users WHERE name = ?1 AND password = ?2", nativeQuery = true)
    Optional<User> userExists(String name, String password);
}
