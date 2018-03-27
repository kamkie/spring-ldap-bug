package com.example.springldapbug;

import org.springframework.data.ldap.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;
import java.util.List;

public interface LdapEmployeeRepository extends CrudRepository<LdapEmployee, Name> {

    @Query(base = "ou=users", value = "(objectClass=*)")
    List<LdapEmployee> findAllLdapEmployees();
}
