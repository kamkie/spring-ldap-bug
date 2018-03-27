package com.example.springldapbug;

import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.util.StringUtils;

@TestConfiguration
@EnableConfigurationProperties({LdapProperties.class, EmbeddedLdapProperties.class})
public class LdapConfigurationWorkaround {

    @Bean
    @DependsOn("directoryServer")
    public ContextSource ldapContextSource(Environment environment,
                                           LdapProperties properties,
                                           EmbeddedLdapProperties embeddedProperties) {
        LdapContextSource source = new LdapContextSource();
        if (hasCredentials(embeddedProperties.getCredential())) {
            source.setUserDn(embeddedProperties.getCredential().getUsername());
            source.setPassword(embeddedProperties.getCredential().getPassword());
        }
        source.setUrls(properties.determineUrls(environment));
        source.setBase(embeddedProperties.getBaseDn().get(0));
        return source;
    }

    private boolean hasCredentials(EmbeddedLdapProperties.Credential credential) {
        return StringUtils.hasText(credential.getUsername())
                && StringUtils.hasText(credential.getPassword());
    }
}
