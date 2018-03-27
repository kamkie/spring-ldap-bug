package com.example.springldapbug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(LdapConfigurationWorkaround.class)
public class SpringLdapBugApplicationTests {

    @Autowired
    private LdapEmployeeRepository ldapEmployeeRepository;

    @Test
    public void useLdapRepository() {
        assertThat(ldapEmployeeRepository.findAllLdapEmployees())
                .hasSize(6);
    }

}
