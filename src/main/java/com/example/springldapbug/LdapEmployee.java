package com.example.springldapbug;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(base = "ou=users,dc=company,dc=pl", objectClasses = "inetOrgPerson")
public class LdapEmployee {

    @Id
    private Name id;

    @Attribute(name = "givenName")
    private String name;

    @Attribute(name = "sn")
    private String surname;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "uidNumber")
    private String uidNumber;

    @Attribute(name = "uid")
    private String uid;
}
